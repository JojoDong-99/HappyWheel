package ictgradschool.project.project.action;

import ictgradschool.project.project.DAO.UserDAO;
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

@WebServlet(name = "editUsernameServlet", urlPatterns = { "/editUsernameServlet" })
public class EditUserServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties"))
        {
            List<String> allUsername = UserDAO.getAllUsername(connection);
            User currentUser = (User)request.getSession().getAttribute("user");

            request.setAttribute("usernameSet", allUsername);
            request.setAttribute("user", currentUser);
            request.getRequestDispatcher("./WEB-INF/jsp/editAccount.jsp").forward(request, response);
        }
        catch (SQLException e)
        {
            throw new ServletException();
        }
    }
}