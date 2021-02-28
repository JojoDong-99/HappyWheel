/**
 * author: Jojo
 */
package ictgradschool.project.project.action;

import ictgradschool.project.project.DAO.ArticleDAO;
import ictgradschool.project.project.util.DBConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "deleteCommentServlet", urlPatterns = { "/deleteComment" })
public class DeleteCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")){
          int articleId = Integer.parseInt(req.getParameter("articleId"));

           int id = Integer.parseInt(req.getParameter("id"));
           ArticleDAO.deleteComment(id, conn);

           req.setAttribute("article", ArticleDAO.getArticleById(articleId, conn));
           req.getRequestDispatcher("./WEB-INF/jsp/article.jsp").forward(req, resp);

       } catch (SQLException e){
           e.printStackTrace();
       }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
