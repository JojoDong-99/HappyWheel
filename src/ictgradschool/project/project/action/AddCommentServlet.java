/**
 * author: jojo
 */
package ictgradschool.project.project.action;

import ictgradschool.project.project.DAO.ArticleDAO;
import ictgradschool.project.project.model.Comment;
import ictgradschool.project.project.model.User;
import ictgradschool.project.project.util.DBConnectionUtils;
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

@WebServlet(name = "AddCommentServlet", urlPatterns = { "/AddComment" })
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")){
           String content = req.getParameter("content");
           int articleId = Integer.parseInt(req.getParameter("article"));
           if (content.length() != 0)
           {
               String parentString = req.getParameter("parent");
               int parent = parentString.isEmpty()? 0:Integer.parseInt(parentString);
               int userId = Integer.parseInt(req.getParameter("userId"));
               int level = Integer.parseInt(req.getParameter("level"));


               Comment comment = new Comment(userId, content, new Timestamp(new Date().getTime()), articleId, parent,
                       level);
               ArticleDAO.insertComment(comment, conn);
           }

            User currentUser = (User)req.getSession().getAttribute("user");
            req.setAttribute("user", currentUser);

           req.setAttribute("article", ArticleDAO.getArticleById(articleId, conn));
           req.getRequestDispatcher("./WEB-INF/jsp/article.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
