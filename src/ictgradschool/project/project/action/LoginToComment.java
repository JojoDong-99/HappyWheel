package ictgradschool.project.project.action;
import ictgradschool.project.project.DAO.ArticleDAO;
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

@WebServlet(name = "loginToComment", urlPatterns = {"/loginToComment"})
public class LoginToComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            String id = req.getParameter("articleId");
                req.setAttribute("articleId", id);
                req.getRequestDispatcher("./login.jsp").forward(req, resp);

    }
}
