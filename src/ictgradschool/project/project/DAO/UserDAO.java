package ictgradschool.project.project.DAO;

import ictgradschool.project.project.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static List<String> getAllUsername(Connection connection) throws SQLException {
        List<String> usernames = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            try (ResultSet result = statement.executeQuery("SELECT username FROM user")) {
                while (result.next()) {
                    usernames.add(result.getString(1));
                }
            }
        }

        return usernames;
    }

    public static boolean createUser(User user, Connection connection) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO user " +
                "(username,hashedPsw,salt,iteration,firstName,lastName,birthday,brief,avatar,imageName)  " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getHashedPsw());
            stmt.setString(3, user.getSalt());
            stmt.setInt(4, user.getIteration()); //fixed iteration is 10000
            stmt.setString(5, user.getFirstName());
            stmt.setString(6, user.getLastName());
            stmt.setDate(7, user.getBirthday());
            stmt.setString(8, user.getBrief());
            stmt.setString(9, user.getAvatarName());
            stmt.setString(10, user.getImageFileName());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) return false;

            try (ResultSet result = stmt.getGeneratedKeys()) {
                result.next();
                user.setId(result.getInt(1));
                return true;
            }
        }
    }

    public static User getUserByUsername(String username, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("select * from user where username = ?")) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) return null;
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        null, //should be hidden
                        null, //should be hidden
                        0, //should be hidden
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getDate("birthday"),
                        rs.getString("brief"),
                        rs.getString("avatar"),
                        rs.getString("imageName")
                );
            }
        }
    }

    public static User getUserById(int id, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("select * from user where id = ?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) return null;
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        null, //should be hidden
                        null, //should be hidden
                        0, //should be hidden
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getDate("birthday"),
                        rs.getString("brief"),
                        rs.getString("avatar"),
                        rs.getString("imageName")
                );
            }
        }
    }

    public static boolean deleteUserById(int id, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("delete from user where id= ?")){
            stmt.setInt(1,id);
            return stmt.executeUpdate() == 1;
        }
    }

    public static boolean updateUserInfo(User user, Connection conn) throws SQLException
    {
        String updateSQL = "UPDATE user " + "SET username = ?, hashedPsw = ?, salt = ?, iteration = ?, firstName = ?, lastName = ?, birthday = ?, brief = ?, avatar = ?, imageName = ? " + "WHERE id = ?";




        try(PreparedStatement stmt = conn.prepareStatement(updateSQL))
        {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getHashedPsw());
            stmt.setString(3, user.getSalt());
            stmt.setInt(4, user.getIteration());
            stmt.setString(5, user.getFirstName());
            stmt.setString(6, user.getLastName());
            stmt.setDate(7, user.getBirthday());
            stmt.setString(8, user.getBrief());
            stmt.setString(9, user.getAvatarName());
            stmt.setString(10, user.getImageFileName());
            stmt.setInt(11, user.getId());


//            stmt.setInt(1,user.getId());
//            stmt.setString(2,user.getUsername());
//            stmt.setString(3,user.getHashedPsw());
//            stmt.setString(4,user.getSalt());
//            stmt.setInt(5,user.getIteration());
//            stmt.setString(6,user.getFirstName());
//            stmt.setString(7,user.getLastName());
//            stmt.setDate(8,user.getBirthday());
//            stmt.setString(9,user.getBrief());
//            stmt.setString(10,user.getAvatarName());
//            stmt.setString(11, user.getImageFileName());


            return stmt.executeUpdate() == 1;
        }
    }

}