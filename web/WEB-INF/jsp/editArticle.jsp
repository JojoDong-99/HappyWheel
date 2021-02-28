<%--
  Created by IntelliJ IDEA.
  User: jerry
  Date: 2020/6/16
  Time: 下午 09:01
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <title>Edit ${article.title}</title> --%>
 <script type="text/javascript" src="./javascript/JerryTestCreateArticle.js"></script>




<%@include file="/html/header.jsp"%>
<%@include file="commonPart/navBar.jsp"%>


<div class="description">
    <div class="container pt-3">
        <div class="shadow-lg p-4 mb-4 #ffeead">

<h1>Edit ${article.title}</h1>

<div class="row" id="mainBody">
 <div class="card" id="articleBody">
     <img class="card-img-top" src="./html/patrick-tomasso-Oaqk7qqNh_c-unsplash.jpg" >
     <div class="card-img-overlay">
     <h2 class="text-warning">Edit ${article.title}</h2>
     <div id="createArticleBody">
         <form action="./saveArticle" method="post">
             <input type="hidden" name="articleAuthor" value="${article.username}">
             <label class="text-warning" for="newTitle">
                 <b><strong>Title</strong></b>
             </label><br>
             <div id="createTitle">
                 <input class="form-control" type="text" id="newTitle" name="newTitle" value="${article.title}" required>
             </div>
             <br>
             <label class="text-warning"><b><strong>Content</strong></b></label><br>
             <div id="createContent">
                 <textarea class="form-control" rows="3" id="newContent" cols="93" name="newContent" required>${article.content}</textarea>
             </div>
             <p class="text-light bg-dark" id="showLastEditTime"></p>
             <input type="hidden" id="articleID" name="articleID" value="${articleID}">
             <br><br>
             <div id="submitCreate">
                 <button class="btn btn-danger btn-block" type="submit" id="createNewArticleButton">save</button>
             </div>
         </form>
     </div>
 </div>
</div>
        </div>
    </div>
</div>



<%@include file="/html/footer.jsp"%>
