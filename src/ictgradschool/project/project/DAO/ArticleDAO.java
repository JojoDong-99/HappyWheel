/**
 * author: jojo
 */
package ictgradschool.project.project.DAO;

import ictgradschool.project.project.model.Article;
import ictgradschool.project.project.model.Comment;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {
    //this method will get all articles from the database and after iterating
    // through result set and add a new Article to the given articles list
    // for each row in the result set.
    public static List<Article> getAllArticles(Connection conn) throws SQLException {

        List<Article> articleList = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM article INNER JOIN user ON article.author = user.id;")) {
                while (rs.next()) {
                    Article article = new Article(
                            rs.getInt("article.id"),
                            rs.getString("title"),
                            rs.getTimestamp("time"),
                            rs.getString("content"),
                            rs.getInt("author"),
                            rs.getString("username")
                    );
                    articleList.add(article);
                }
            }
        }
        return articleList;
    }


    public static List<Article> getArticlesByAuthor(int author, Connection conn) throws SQLException {
        List<Article> articles = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM article INNER JOIN user ON article.author = user.id WHERE author = ? ")) {
            stmt.setInt(1, author);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int articleId = rs.getInt("article.id");
                    articles.add(new Article(
                            articleId,
                            rs.getString("title"),
                            rs.getTimestamp("time"),
                            rs.getString("content"),
                            rs.getInt("author"),
                            rs.getString("username"),
                            getCommentsByArticleId(articleId, conn)
                    ));
                }
            }
        }
        return articles;
    }

    public static List<Comment> getCommentsByArticleId(int id, Connection conn) throws SQLException {
        List<Comment> comments = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM comment INNER JOIN user on comment.author = user.id WHERE articleId = ? AND level = 0"
        )) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int commentId = rs.getInt("comment.id");
                    comments.add(new Comment(
                            commentId,
                            rs.getInt("author"),
                            rs.getString("username"),
                            rs.getString("content"),
                            rs.getTimestamp("time"),
                            rs.getInt("articleId"),
                            rs.getInt("parent"),
                            rs.getInt("level"),
                            getCommentsByParentId(commentId, 1, conn),
                            rs.getString("avatar"),
                            rs.getString("imageName")
                    ));
                }
            }
        }

        return comments;
    }


    public static List<Comment> getCommentsByParentId(int id, int level, Connection conn) throws SQLException {
        List<Comment> comments = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM comment INNER JOIN user on comment.author = user.id WHERE parent = ? AND level = ?"
        )) {
            stmt.setInt(1, id);
            stmt.setInt(2, level);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int commentId = rs.getInt("comment.id");
                    List<Comment> childrenComments = level >= 2 ? null : getCommentsByParentId(commentId,
                            level + 1, conn);
                    comments.add(new Comment(
                            commentId,
                            rs.getInt("author"),
                            rs.getString("username"),
                            rs.getString("content"),
                            rs.getTimestamp("time"),
                            rs.getInt("articleId"),
                            rs.getInt("parent"),
                            level,
                            childrenComments,
                            rs.getString("avatar"),
                            rs.getString("imageName")
                    ));
                }
            }
        }
        return comments;
    }


    //get articles by searching title key word
    public static List<Article> getArticlesMatching(String search, Connection conn) throws SQLException {
        List<Article> articles = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM article INNER JOIN user ON article.author = user.id WHERE title LIKE ? ")) {
            stmt.setString(1, "%" + search + "%");  // So we can use it in an SQL LIKE clause.
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int articleId = rs.getInt("article.id");
                    articles.add(new Article(
                            articleId,
                            rs.getString("title"),
                            rs.getTimestamp("time"),
                            rs.getString("content"),
                            rs.getInt("author"),
                            rs.getString("username"),
                            getCommentsByArticleId(articleId, conn)
                    ));
                }
            }
        }
        return articles;
    }


    //inserting article:
    public static boolean insertArticle(Article article, Connection conn) throws SQLException {

        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO article (author, title, time, content) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, article.getAuthorId());
            stmt.setString(2, article.getTitle());
            stmt.setString(3, article.getTime());
            stmt.setString(4, article.getContent());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                return false;
            }
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                keys.next(); // Move to the fist row.
                int id = keys.getInt(1);
                article.setId(id);
                return true;
            }
        }
    }

    //deleting article:
    public static boolean deleteArticle(int id, Connection conn) throws SQLException {

        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM article WHERE id = ?")) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() == 1;
        }
    }

    //editing Article:
    public static boolean editArticle(Article article, Connection conn) throws SQLException {

        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE article SET title = ?, content = ?, time = ?  WHERE id = ?")) {

            stmt.setString(1, article.getTitle());
            stmt.setString(2, article.getContent());
            stmt.setString(3, article.getTime());
            stmt.setInt(4, article.getId());

            int rowsAffected = stmt.executeUpdate();
            return (rowsAffected == 1);
        }
    }

    public static Article getArticleById(int id, Connection conn) throws IOException, SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM article INNER JOIN user ON article.author = user.id " +
                "WHERE article.id = ?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    return new Article(
                            rs.getInt("article.id"),
                            rs.getString("title"),
                            rs.getTimestamp("time"),
                            rs.getString("content"),
                            rs.getInt("author"),
                            rs.getString("username"),
                            getCommentsByArticleId(id, conn)
                    );
                }
            }
        }
        return null;
    }

    public static String getUsernameByUserId(int id, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE id = ?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("username");
                }
            }
        }
        return null;
    }

    public static boolean insertComment(Comment comment, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT comment (author, content, time, articleId, " +
                "parent, level) VALUES (?,?,?,?,?,?)")) {
            stmt.setInt(1, comment.getAuthor());
            stmt.setString(2, comment.getContent());
            stmt.setString(3, comment.getTime());
            stmt.setInt(4, comment.getArticle());
            if (comment.getParent() == 0) stmt.setNull(5, Types.NULL);
            else stmt.setInt(5, comment.getParent());
            stmt.setInt(6, comment.getLevel());

            return stmt.executeUpdate() == 1;
        }
    }

    public static boolean deleteComment(int id, Connection conn) throws SQLException {

        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM comment WHERE id = ?")) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() == 1;
        }
    }
}