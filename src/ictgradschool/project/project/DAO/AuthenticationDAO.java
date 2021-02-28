package ictgradschool.project.project.DAO;

import ictgradschool.project.project.model.User;
import ictgradschool.project.project.util.PasswordUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationDAO
{
    public static boolean checkPassword(String username, String password, Connection conn) throws SQLException
    {
        try (PreparedStatement stmt = conn.prepareStatement("select * from user where username = ?"))
        {
            System.out.println("password test1");

            stmt.setString(1, username);
            ResultSet r = stmt.executeQuery();
            System.out.println("password test2");
            if (!r.next()) return false;

            System.out.println("password test3");
            String _hashedPsw = r.getString("hashedPsw");
            System.out.println("hashedPsw " + _hashedPsw);
            String salt = r.getString("salt");
            System.out.println("salt " + salt);
            int iteration = r.getInt("iteration");
            System.out.println("iteration " + iteration);
            System.out.println("p: " + password);

            String hashedPsw = PasswordUtils.base64Encode(PasswordUtils.hash(
                    password.toCharArray(),
                    PasswordUtils.base64Decode(salt),
                    iteration
            ));
            System.out.println("final " + hashedPsw);

            System.out.println("password test4");
            System.out.println(_hashedPsw.equals(hashedPsw));
            return _hashedPsw.equals(hashedPsw);
        }
    }

    public static User loggedInUser(HttpServletRequest req){
        return (User)req.getSession().getAttribute("user");
    }


}