package ictgradschool.project.project.action;

import ictgradschool.project.project.DAO.ArticleDAO;
import ictgradschool.project.project.model.Article;
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

@WebServlet(name = "article", urlPatterns = {"/article"})
public class ArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            User currentUser = (User)req.getSession().getAttribute("user");
            req.setAttribute("user", currentUser);

            int articleID = Integer.parseInt(req.getParameter("id"));

            Article article = ArticleDAO.getArticleById(articleID, conn);
            req.setAttribute("article", article);

            req.setAttribute("articleID", articleID);

            req.getRequestDispatcher("./WEB-INF/jsp/article.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}