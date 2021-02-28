package ictgradschool.project.project.action;

import ictgradschool.project.project.DAO.AuthenticationDAO;
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

@WebServlet(name="deleteUser", urlPatterns ={"/deleteUser"})
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")){
            User loggedInUser = AuthenticationDAO.loggedInUser(req);
            UserDAO.deleteUserById(loggedInUser.getId(), conn);
            req.getSession().invalidate();

            resp.sendRedirect("./index");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
