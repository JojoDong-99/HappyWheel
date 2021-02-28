package ictgradschool.project.project.action;

import ictgradschool.project.project.DAO.ArticleDAO;
import ictgradschool.project.project.DAO.AuthenticationDAO;
import ictgradschool.project.project.DAO.UserDAO;
import ictgradschool.project.project.model.Article;
import ictgradschool.project.project.model.User;
import ictgradschool.project.project.util.DBConnectionUtils;
import org.apache.logging.log4j.core.appender.rolling.action.IfNot;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * TODO This is a test servlet, and should be deleted from the project.
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String strID = req.getParameter("articleId");

            if (AuthenticationDAO.checkPassword(username, password, conn)) {
                User user = UserDAO.getUserByUsername(username, conn);
                req.getSession(true).setAttribute("user", user);

                if (strID.isEmpty()){
                    req.getRequestDispatcher("./personalHome?id=" + user.getId()).forward(req, resp);
                } else {
                    int id = Integer.parseInt(strID);
                    req.getRequestDispatcher("/article?id=" + id).forward(req, resp);
                }

            } else {
                req.setAttribute("wrongMessage", "Invalid username or password, please try again!");
                req.getRequestDispatcher("./login.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}