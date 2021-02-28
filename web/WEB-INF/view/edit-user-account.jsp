<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: jerry
  Date: 2020/6/8
  Time: 下午 05:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Account</title>
    <link rel="stylesheet" href="./css/CreateAccount.css">
    <script type="text/javascript" src="./javascript/EditAccount.js"></script>
</head>


<body>

<h2 id="pageTitle">Create New Account</h2>

<div class="mainBody">
    <form action="./updateAccountServlet" method="post" enctype="multipart/form-data">
        <div class="accountImage">
            <a href="javascript:popUpAvatar()">
                <img alt="Avatar" class="avatar" id="avatarImg">
            </a>
        </div>

        <div class="container">
            <div class="content-file" id="checkFile">
                <label for="myFile">Select an image: </label>
                <input type="file" id="myFile" name="myFile">
            </div>
            <br><br>
            <div class="content-file-choice">
                <input type="radio" name="fileChoice" id="useFile" required checked>
                <label for="useFile">Using own image</label>
                <input type="radio" name="fileChoice" id="notUseFile">
                <label for="notUseFile">Using predefined image</label>
            </div>
            <br>
            <div class="content-username">
                <label for="username"><b>Username</b></label><br>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="content-password">
                <label for="password"><b>Password</b></label><br>
                <input type="password" placeholder="Enter password" id="password" name="password" required><br>
                <input type="password" placeholder="Re-enter password" id="passwordCheck" name="repassword" reqired>
            </div>
            <div class="content-firstname">
                <label><b>First Name</b></label><br>
                <input type="text" id="firstname" name="firstname" required>
            </div>
            <div class="content-lastname">
                <label><b>Last Name</b></label><br>
                <input type="text" id="lastname" name="lastname" required>
            </div>
            <div class="content-birthday">
                <label><b>Date of Birth</b></label><br>
                <input type="date" id="birthday" name="birthday" required>
            </div>
            <div class="content-description">
                <label><b>Brief Description of Yourself</b></label><br>
                <textarea id="briefDescription" rows="4" cols="50" name="description"></textarea>
            </div>
            <div class="store-avatar">
                <input type="text" id="storeAvatar" name="avatarName" value="none">
            </div>
            <div class="store-userId">
                <input type="text" id="storeUserID" name="userID" value="none">
            </div>
            <div class="content-final-ensure">
                <input type="checkbox" name="infoEnsure" id="infoEnsure" required unchecked>
                <label for="infoEnsure">I've complete the account information</label>
            </div>
            <div class="content-button">
                <button type="submit" id="createButton">Edit</button>
            </div>
        </div>
    </form>
</div>



<div id="changeImgBlock" class="modal">
    <form class="modal-content animate">
        <div class="closingImg">
            <span onclick="document.getElementById('changeImgBlock').style.display='none'" class="close" title="Close">&times;</span>
        </div>

        <h3>Choose From Pre-defined Set</h3>

        <div class="imagesSet">
            <input type="checkbox" name="avatar1" id="avatar1" value="image-avatar-1.png"/><label for="avatar1"></label>
            <input type="checkbox" name="avatar2" id="avatar2" value="image-avatar-2.png"/><label for="avatar2"></label>
            <input type="checkbox" name="avatar3" id="avatar3" value="image-avatar-3.png"/><label for="avatar3"></label>
            <input type="checkbox" name="avatar4" id="avatar4" value="image-avatar-4.png"/><label for="avatar4"></label>
        </div>

        <div class="changingSubmit">
            <button id="changing">Change</button>
        </div>
    </form>
</div>

<c:forEach items="${usernameSet}" var="currentUsername">
    <span class="userName">${currentUsername}</span>
</c:forEach>

<c:forEach items="${currentExistUsername}" var="username">
    <span class="userName" id="testUsername">${username}</span>
</c:forEach>


<span class="userAccount">${userName}</span>
<span class="userAccount">${firstName}</span>
<span class="userAccount">${lastName}</span>
<span class="userAccount">${birthday}</span>
<span class="userAccount">${description}</span>
<span class="userAccount">${imageFileName}</span>
<span class="userAccount">${avatarName}</span>
<span class="userAccount">${userID}</span>


</body>


</html>