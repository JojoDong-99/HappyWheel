package ictgradschool.project.project.action;

import ictgradschool.project.project.DAO.AuthenticationDAO;
import ictgradschool.project.project.DAO.UserDAO;

import ictgradschool.project.project.DAO.AuthenticationDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "createArticleServlet", urlPatterns = { "/createArticleServlet" })
public class ArticleCreateServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("user", AuthenticationDAO.loggedInUser(request));
        request.getRequestDispatcher("./WEB-INF/jsp/createArticle.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}