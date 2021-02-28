/**
 * author: Subha
 */
package ictgradschool.project.project.action;

import ictgradschool.project.project.DAO.ArticleDAO;
import ictgradschool.project.project.DAO.AuthenticationDAO;
import ictgradschool.project.project.DAO.UserDAO;
import ictgradschool.project.project.model.User;
import ictgradschool.project.project.util.DBConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "personalHome", urlPatterns = { "/personalHome" })
public class PersonalHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try(Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties"))
        {
            int id = Integer.parseInt(req.getParameter("id"));
            User loginUser = (User)req.getSession().getAttribute("user");

            Cookie[] cookies = req.getCookies();
            for (Cookie cok : cookies)
            {
                if (loginUser != null && (cok.getName()).compareTo("avatarCookie") == 0)
                {
                    if (loginUser.getAvatarName().equals("none"))
                        cok.setValue(loginUser.getImageFileName());
                    else
                        cok.setValue(loginUser.getAvatarName());

                    resp.addCookie(cok);
                }
            }

            req.setAttribute("loginUser", loginUser);
            req.setAttribute("user", UserDAO.getUserById(id,conn));
            req.setAttribute("articleSet", ArticleDAO.getArticlesByAuthor(id, conn));

            req.getRequestDispatcher("./WEB-INF/jsp/personalHome.jsp").forward(req,resp);
        }catch (IOException  | SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doGet(req,resp);
    }
}