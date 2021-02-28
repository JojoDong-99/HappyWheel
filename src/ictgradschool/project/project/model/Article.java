package ictgradschool.project.project.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class Article implements Serializable{

    private Integer id;//
    private String title;//
    private Timestamp time;
    private String content;//
    private String briefContent;
    private Integer authorId;//
    private String username;//
    private List<Comment> comments;

    public Article(){}

    public Article(Integer id, String title, Timestamp time, String content, Integer authorId,
                   String username, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.content = content;

        this.authorId = authorId;
        this.username = username;
        this.comments = comments;

        if (content.length() > 199)
            this.briefContent = content.substring(0,199);
        else
            this.briefContent = content.substring(0, (content.length()));
    }

    public Article(Integer id, String title, Timestamp time, String content, Integer authorId, String username) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.content = content;

        this.authorId = authorId;
        this.username = username;

        if (content.length() > 199)
            this.briefContent = content.substring(0,199);
        else
            this.briefContent = content.substring(0, (content.length()));
    }

    public Article(String title, Timestamp time, String content, Integer authorId, String username) {
        this.title = title;
        this.time = time;
        this.content = content;
        this.authorId = authorId;
        this.username = username;

        if (content.length() > 199)
            this.briefContent = content.substring(0,199);
        else
            this.briefContent = content.substring(0, (content.length()));
    }

    public Article(int id, String title, Timestamp time, String content, Integer authorId) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.content = content;

        this.authorId = authorId;

        if (content.length() > 199)
            this.briefContent = content.substring(0,199);
        else
            this.briefContent = content.substring(0, (content.length()));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime()
    {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(time);
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getBriefContent() {
        return briefContent;
    }

    public void setBriefContent(String briefContent) {
        this.briefContent = briefContent;
    }
}