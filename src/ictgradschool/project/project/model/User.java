package ictgradschool.project.project.model;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {
    private int id;
    private String username;
    private String hashedPsw;
    private String salt;
    private int iteration;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String brief;
    private String avatarName;

    private String imageFileName;

    public User() {
    }

    public User(int id, String username, String hashedPsw, String salt,
                int iteration, String firstName, String lastName,
                Date birthday, String brief, String avatarName) {
        this.id = id;
        this.username = username;
        this.hashedPsw = hashedPsw;
        this.salt = salt;
        this.iteration = iteration;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.brief = brief;
        this.avatarName = avatarName;
    }

    public User(String username, String hashedPsw, String salt, int iteration, String firstName, String lastName, Date birthday, String brief, String avatarName) {
        this.username = username;
        this.hashedPsw = hashedPsw;
        this.salt = salt;
        this.iteration = iteration;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.brief = brief;
        this.avatarName = avatarName;
    }

    public User(int id, String username, String hashedPsw, String salt, int iteration, String firstName, String lastName, Date birthday, String brief, String avatarName, String imageFileName) {
        this.id = id;
        this.username = username;
        this.hashedPsw = hashedPsw;
        this.salt = salt;
        this.iteration = iteration;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.brief = brief;
        this.avatarName = avatarName;
        this.imageFileName = imageFileName;
    }

    //    public static void main(String[] args) {
//        try(Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")){
//            User user = UserDAO.getUserByUsername("abc",conn);
//            System.out.println(user.getId());
//        } catch (IOException | SQLException e){
//
//        }
//
//    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPsw() {
        return hashedPsw;
    }

    public void setHashedPsw(String hashedPsw) {
        this.hashedPsw = hashedPsw;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", hashedPsw='" + hashedPsw + '\'' +
                ", salt='" + salt + '\'' +
                ", iteration=" + iteration +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", brief='" + brief + '\'' +
                ", avatar='" + avatarName + '\'' +
                ", imageFileName='" + imageFileName + '\'' +
                '}';
    }
}