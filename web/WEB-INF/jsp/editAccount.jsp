<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: jerry
  Date: 2020/6/8
  Time: 下午 05:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <%-- <title>Edit Account</title> --%>
 <link rel="stylesheet" href="./html/CreateAccount.css">
<script type="text/javascript" src="./javascript/EditAccount.js"></script>


<%@include file="/html/header.jsp"%>
<%@include file="./commonPart/navBar.jsp"%>




<h2 id="pageTitle">Edit ${user.username} Account</h2>

<div class="mainBody">
<form action="./saveUserInfo" method="post" enctype="multipart/form-data">
    <div class="accountImage">
        <a href="javascript:popUpAvatar()">

            <c:if test="${user.avatarName eq 'none'}">
                <img alt="Avatar" class="avatar" id="avatarImg" src="./images/${user.imageFileName}">
            </c:if>

            <c:if test="${user.imageFileName eq 'none'}">
                <img alt="Avatar" class="avatar" id="avatarImg" src="./images/${user.avatarName}">
            </c:if>

        </a>
        <p style="text-align:center;">click on the avatar to use predefined images</p>
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
            <input type="text" id="username" name="username" value="${user.username}" required>
        </div>
        <div class="content-password">
            <label for="password"><b>Password</b></label><br>
            <input type="password" placeholder="Enter password" id="password" name="password" required><br>
            <input type="password" placeholder="Re-enter password" id="passwordCheck" name="repassword" reqired>
        </div>
        <div class="content-firstname">
            <label><b>First Name</b></label><br>
            <input type="text" id="firstname" name="firstname" value="${user.firstName}" required>
        </div>
        <div class="content-lastname">
            <label><b>Last Name</b></label><br>
            <input type="text" id="lastname" name="lastname" value="${user.lastName}" required>
        </div>
        <div class="content-birthday">
            <label><b>Date of Birth</b></label><br>
            <input type="date" id="birthday" name="birthday" value="${user.birthday}" required>
        </div>
        <div class="content-description">
            <label><b>Brief Description of Yourself</b></label><br>
            <textarea id="briefDescription" rows="4" cols="50" name="description">${user.brief}</textarea>
        </div>
        <div class="store-avatar">
            <input type="text" id="storeAvatar" name="avatarName" value="none">
        </div>
        <div class="store-userId">
            <input type="hidden" id="storeUserID" name="userID" value="${user.id}">
        </div>
        <div class="content-final-ensure">
            <input type="checkbox" name="infoEnsure" id="infoEnsure" required unchecked>
            <label for="infoEnsure">I've complete the account information</label>
        </div>
        <div class="content-button">
            <button class="btn btn-danger" type="submit" id="createButton">Edit</button>
        </div>
    </div>
</form>

<div id="deleteAccountDiv">
    <a href="./deleteUser">
        <button class="btn btn-danger" id="deleteAccountButton">Delete Account</button>
    </a>
</div>
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

<span id="currentUsername">${user.username}</span>

<c:forEach items="${usernameSet}" var="currentUsername">
<span class="userName">${currentUsername}</span>
<%--    <input type="hidden" class="userName" value="${currentUsername}">--%>
</c:forEach>

<c:if test="${user.avatarName eq 'none'}">
    <span id="currentAvatar">${user.imageFileName}</span>
</c:if>

<c:if test="${user.imageFileName eq 'none'}">
    <span id="currentAvatar">${user.avatarName}</span>
</c:if>





<%@include file="/html/footer.jsp"%>