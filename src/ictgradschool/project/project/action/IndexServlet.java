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
import java.util.List;

@WebServlet(name = "index", urlPatterns = { "/index" })
public class IndexServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties"))
        {
            List<Article> articleSet = ArticleDAO.getAllArticles(conn);

            req.setAttribute("articleSet", articleSet);
            req.getRequestDispatcher("./WEB-INF/jsp/index.jsp").forward(req, resp);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doPost(req,resp);
    }
}