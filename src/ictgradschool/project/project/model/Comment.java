package ictgradschool.project.project.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class Comment implements Serializable {
    private int id;
    private int author;
    private String username;
    private String content;
    private Timestamp time;
    private int article;
    private int parent;
    private int level;
    private List<Comment> comments;
    private String avatar;

    private String imageFileName;

    public Comment(int id, int author, String username,
                   String content, Timestamp time, int article, int parent,
                   int level, List<Comment> comments, String avatar) {
        this.id = id;
        this.author = author;
        this.username = username;
        this.content = content;
        this.time = time;
        this.article = article;
        this.parent = parent;
        this.level = level;
        this.comments = comments;
        this.avatar = avatar;
    }

    public Comment(int author, String content, Timestamp time, int article, int parent, int level, String avatar) {
        this.author = author;
        this.content = content;
        this.time = time;
        this.article = article;
        this.parent = parent;
        this.level = level;
        this.avatar = avatar;
    }

    public Comment(int author, String content, Timestamp time, int article, int parent, int level) {
        this.author = author;
        this.content = content;
        this.time = time;
        this.article = article;
        this.parent = parent;
        this.level = level;
    }


    public Comment(int id, int author, String username, String content, Timestamp time, int article, int parent, int level, List<Comment> comments, String avatar, String imageFileName) {
        this.id = id;
        this.author = author;
        this.username = username;
        this.content = content;
        this.time = time;
        this.article = article;
        this.parent = parent;
        this.level = level;
        this.comments = comments;
        this.avatar = avatar;
        this.imageFileName = imageFileName;
    }

    public Comment(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(time);
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
}

