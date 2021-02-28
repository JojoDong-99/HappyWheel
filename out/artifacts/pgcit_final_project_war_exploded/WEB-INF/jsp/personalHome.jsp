
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style type="text/css">
    .articleIDStore
    {
        visibility: hidden;
    }
</style>

    <script type="text/javascript">
        window.addEventListener("load", function () {
            let avatarImage = (document.getElementById("currentImageName").src).split("/");
            console.log("img: " + avatarImage[avatarImage.length - 1]);
        });

        function setEditButton()
        {
            let avatarImage = (document.getElementById("currentImageName").src).split("/");
            document.cookie = "avatarCookie=" + JSON.stringify(avatarImage[avatarImage.length - 1]);
        }
    </script>



<%@include file="/html/header.jsp"%>
<%@include file="./commonPart/navBar.jsp"%>

<title>${user.username}'s Blog</title>


<div class="container-fluid">
<div class="row" id="mainBody">

    <!--left is information of the current author -->
    <div class="col-lg-3 col-md-3 col-sm-12">
    <div class="card">
        <h2  class="mx-auto currentTitle">${user.username}'s Info</h2>

        <c:if test="${user.avatarName eq 'none'}">
            <img src="./images/${user.imageFileName}" id="currentImageName"  style="width: 300px" class="img-fluid img-circle img-thumbnail mx-auto" alt="Responsive image">
        </c:if>
        <c:if test="${user.imageFileName eq 'none'}">
            <img src="./images/${user.avatarName}" id="currentImageName" style="width: 300px" style="width: 200px" class="img-fluid img-circle img-thumbnail mx-auto"  alt="Responsive image">
        </c:if>

        <div class="card-body">
        <h3 id="currentUsername">${user.username}</h3>
        <h5>First name: ${user.firstName}</h5>
        <h5>Last name: ${user.lastName}</h5>
        <h5>Birthday: ${user.birthday}</h5>
        <h5 id="currentDescription">${user.brief}</h5>
        <c:if test="${sessionScope.user.id == user.id}">
        <div id="editUserButtonPart">
            <div id="editUserButton">
               <span><a class="btn btn-danger btn-sm" href="./editUsernameServlet" onclick="setEditButton()">Edit Account</a></span>
            </div>
        </div>
        </c:if>
        </div>
    </div>
    </div>



        <div class="col-lg-6 col-md-6 col-sm-12">
        <div class="card" id="articleBody">
            <div class="card-body">
            <!-- middle is to show all article intros of the current author -->
            <c:forEach items="${articleSet}" var="currentArticle" varStatus="status">
                <div class="leftcolumn" id="article${status.index}">
                    <div class="titleBar">
                        <h2><a href="./article?id=${currentArticle.id}">${currentArticle.title}</a></h2>
                    </div>
                    <h4 class="articleAuthor">Author:${currentArticle.username}</h4>
                    <p><small>${currentArticle.time}</small></p>
                    <p>${currentArticle.briefContent}</p>
                    <div class="btn-toolbar btn-block">
                    <c:if test="${sessionScope.user.username eq user.username}">
                        <form action="./editArticle" method="get">
                            <input type="hidden" name="articleID" value="${currentArticle.id}">
                            <button class="btn btn-danger btn-sm" type="submit" id="editButton">Edit</button>
                        </form>
                        <form action="./deleteArticle" method="get">
                            <input type="hidden" name="articleID" value="${currentArticle.id}">
                            <button class="btn btn-danger btn-sm" type="submit" id="deleteButton">Delete</button>
                        </form>
                    </c:if>
                    </div>

                </div>
            </c:forEach>
            </div>
        </div>
    </div>


    <div class="col-lg-3 col-md-3 col-sm-12">
    <!-- right is titles of all articles of the current author -->


    <div class="card classOverFlow" id="rightPart">
        <h2 class="currentTitle mx-auto">${user.username}'s Article</h2>
        <div class="card-body">
        <div id="currentArticleList">
            <c:forEach items="${articleSet}" var="article" varStatus="status">
                <p class="currentArticleList overFlow" id="articleTitle${status.index}"><a href="./article?id=${article.id}">
                        ${article.title}</a></p>
                <span class="articleIDStore" id="articleId${status.index}">${article.id}</span>
            </c:forEach>
        </div>
        <c:if test="${sessionScope.user.id == user.id}">
        <div id="createButtonPart">
            <div id="createButton">
                <span><a class="btn btn-danger btn-sm" href="./createArticleServlet">Create New Article</a></span>
            </div>
        </div>
        </c:if>

    </div>
    </div>

</div>
</div>



<%@include file="/html/footer.jsp"%>