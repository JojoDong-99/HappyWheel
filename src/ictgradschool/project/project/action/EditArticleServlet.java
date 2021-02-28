/**
 * author: subha
 */
package ictgradschool.project.project.action;


import ictgradschool.project.project.DAO.ArticleDAO;
import ictgradschool.project.project.DAO.AuthenticationDAO;
import ictgradschool.project.project.util.DBConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "editArticle", urlPatterns = {"/editArticle"})
public class EditArticleServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties"))
        {
            int id = Integer.parseInt(req.getParameter("articleID"));
            String author = req.getParameter("articleAuthor");

            req.setAttribute("article", ArticleDAO.getArticleById(id, conn));
//            int currentAuthor = AuthenticationDAO.loggedInUser(req).getId();

            req.setAttribute("articleID", id);
            req.getRequestDispatcher("./WEB-INF/jsp/editArticle.jsp").forward(req, resp);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}