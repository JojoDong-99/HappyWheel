/**
 * author: Subha
 */
package ictgradschool.project.project.action;

import ictgradschool.project.project.DAO.ArticleDAO;
import ictgradschool.project.project.DAO.AuthenticationDAO;
import ictgradschool.project.project.model.Article;
import ictgradschool.project.project.model.User;
import ictgradschool.project.project.util.DBConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "deleteArticle", urlPatterns = { "/deleteArticle" })
public class DeleteArticleServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties"))
        {
            int id = Integer.parseInt(req.getParameter("articleID"));

            ArticleDAO.deleteArticle(id, conn);

            User author = AuthenticationDAO.loggedInUser(req);
            List<Article> articleSet = ArticleDAO.getArticlesByAuthor(author.getId(), conn);
            req.setAttribute("articleSet", articleSet);
            req.getRequestDispatcher("./WEB-INF/jsp/personalHome.jsp").forward(req, resp);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}