<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<%@include file="/html/header.jsp"%>
<%@include file="./commonPart/navBar.jsp"%>

<style type="text/css">
    .overFlow
    {
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        width: 100%;
    }
</style>


<div class="description">
    <div class="container pt-3">
        <div class="container-index"></div>
        <div class="shadow-lg p-4 mb-4 #ffeead">
            <div class="row">
                <div class ="col-sm-1"></div>
                <div class ="col-sm-10">
            <div class="row">
                <c:forEach items="${articleSet}" var="article">
                    <div class="col-md-12">
                    <h1 class="text-center overFlow"><b><a href="./article?id=${article.id}">${article.title}</a></b></h1>
                    <p class="overFlow"><a href="./personalHome?id=${article.authorId}">${article.username}</a></p>
                    <p class="text-right"><small>${article.time}</small></p>
                    <p class="text-justify overFlow">${article.briefContent}</p>
                    </div>
                </c:forEach>
            </div>
                </div>
        </div>
    </div>
</div>
    </div>
</div>



<%@include file="/html/footer.jsp"%>