
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<%-- <title>Create New Article</title> --%>


<%@include file="/html/header.jsp"%>
<%-- <link rel="stylesheet" href="./css/JerryCreateArticle.css"> --%>
<script type="text/javascript" src="./javascript/JerryTestCreateArticle.js"></script>



<%@include file="/WEB-INF/jsp/commonPart/navBar.jsp" %>


<div class="description">
    <div class="container pt-3">
        <div class="shadow-lg p-4 mb-4 #ffeead">



<div class="row" id="mainBody">
<div class="card" id="articleBody">
    <img class="card-img-top" src="./html/patrick-tomasso-Oaqk7qqNh_c-unsplash.jpg" >
    <div class="card-img-overlay">
        <h2 class="text-warning"><strong>Create New Article</strong></h2>
    <div id="createArticleBody">
        <form action="./saveArticle" method="get">
            <input type="hidden" name="articleAuthor" value="${sessionScope.user.username}">
            <label class="text-warning" for="newTitle">
                <b><strong>Title</strong></b>
            </label><br>
            <div id="createTitle">
                <input class="form-control" type="text" id="newTitle" name="newTitle" placeholder="Enter New Article Title" required>
            </div>
            <br>
            <label class="text-warning"><b><strong>Content</strong></b></label><br>
            <div id="createContent">
                <textarea class="form-control" rows="3" id="newContent" cols="50" placeholder="Enter New Article Content"
                          name="newContent" required></textarea>
            </div>
            <p class="text-light bg-dark" id="showLastEditTime"></p>
            <input type="hidden" id="articleID" name="articleID" value="${articleID}">
            <br><br>
            <div id="submitCreate">
                <button class="btn btn-danger btn-block" type="submit" id="createNewArticleButton">Create</button>
            </div>
        </form>
    </div>
</div>
</div>
</div>

        </div>
    </div>
</div>


<%@include file="/html/footer.jsp"%>