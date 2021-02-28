/**
 * author jerry, jojo
 */
package ictgradschool.project.project.action;

import ictgradschool.project.project.DAO.ArticleDAO;
import ictgradschool.project.project.DAO.AuthenticationDAO;
import ictgradschool.project.project.model.Article;
import ictgradschool.project.project.util.DBConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

@WebServlet(name = "saveArticle", urlPatterns = { "/saveArticle" })
public class SaveArticleServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String title = req.getParameter("newTitle");
        String content = req.getParameter("newContent");
        int authorId = AuthenticationDAO.loggedInUser(req).getId();
        Timestamp time = new Timestamp(new Date().getTime());
        //must have a hidden input to get article id for this servlet
        //if the case is to create a new article, id would be empty, otherwise id is an integer
        String strId = req.getParameter("articleID");
        String author = req.getParameter("articleAuthor");

        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties"))
        {
            Article article;

            if(strId.isEmpty())
            {
                article = new Article(title, time, content, authorId, author);
                ArticleDAO.insertArticle(article, conn);
            }
            else
            {
                int id = Integer.parseInt(strId);
                article = new Article(id, title, time, content, authorId, author);
                ArticleDAO.editArticle(article,conn);
            }

            req.setAttribute("article",article);
            req.getRequestDispatcher("./WEB-INF/jsp/article.jsp").forward(req, resp);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}