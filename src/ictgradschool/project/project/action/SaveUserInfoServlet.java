package ictgradschool.project.project.action;

import ictgradschool.project.project.DAO.AuthenticationDAO;
import ictgradschool.project.project.DAO.UserDAO;
import ictgradschool.project.project.model.User;
import ictgradschool.project.project.util.DBConnectionUtils;
import ictgradschool.project.project.util.PasswordUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "saveUserInfo", urlPatterns = {"/saveUserInfo"})
public class SaveUserInfoServlet extends HttpServlet {
    private File uploadsFolder; // The folder where article images should be uploaded
    private File tempFolder; // The temp folder required by the file-upload logic

    @Override
    public void init() throws ServletException {
        super.init();

        // Get the upload folder, ensure it exists.
        this.uploadsFolder = new File(getServletContext().getRealPath("/images"));
        if (!uploadsFolder.exists())
            uploadsFolder.mkdirs();

        // Create the temporary folder that the file-upload mechanism needs.
        this.tempFolder = new File(getServletContext().getRealPath("/WEB-INF/temp"));
        if (!tempFolder.exists())
            tempFolder.mkdirs();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set up file upload mechanism
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(tempFolder);
        ServletFileUpload upload = new ServletFileUpload(factory);

        User user = new User();
        String strId = "";
        // Parse the form (works differently since we're expecting a file, amongst other form fields).
        List<FileItem> fileItems = null;
        try {
            fileItems = upload.parseRequest(req);

        for (FileItem fi : fileItems) {
            switch (fi.getFieldName()) {
                case "username":
                    user.setUsername(fi.getString());
                    break;
                case "password":
                    //calculate hashed and salted password
                    String password = fi.getString();
                    String salt = PasswordUtils.base64Encode(PasswordUtils.getNextSalt());
                    String hashedPassword = PasswordUtils.base64Encode(
                            PasswordUtils.hash(password.toCharArray(), PasswordUtils.base64Decode(salt), 10000));
                    user.setHashedPsw(hashedPassword);
                    user.setSalt(salt);
                    user.setIteration(10000);
                    break;
                case "firstname":
                    user.setFirstName(fi.getString());
                    break;
                case "lastname":
                    user.setLastName(fi.getString());
                    break;
                case "birthday":
                    user.setBirthday(Date.valueOf(fi.getString()));
                    break;

                case "description":
                    user.setBrief(fi.getString());
                    break;

                case "avatarName":
                    String avatarName = fi.getString();
                    user.setAvatarName(avatarName);
                    if (!(avatarName.equals("none")))
                        user.setImageFileName("none");
                    break;

                case "userID":
                    strId = fi.getString();

                case "myFile":
                    File imageFile = new File(this.uploadsFolder, fi.getName());
                    user.setImageFileName(fi.getName());
                    fi.write(imageFile);
                    break;
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Save the article to the DB.
        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            if (strId.isEmpty()) {
                //username,hashedPsw,salt,iteration,firstName,lastName,birthday,brief,avatar
                UserDAO.createUser(user, conn);
                resp.sendRedirect("./login.jsp");
            } else {
                int loggedInUserId = Integer.parseInt(strId);
                user.setId(loggedInUserId);

                UserDAO.updateUserInfo(user,conn);

                User newUser = UserDAO.getUserByUsername(user.getUsername(), conn);
                req.getSession(true).setAttribute("user", newUser);

                resp.sendRedirect("./personalHome?id=" + loggedInUserId);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}