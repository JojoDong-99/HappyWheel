<%--
  author: jojodong
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%--
    <title>Login Page</title>

--%>


<!DOCTYPE html>
<html lang="en">
<head>
    <!-- jquery -->
    <link href="https://fonts.googleapis.com/css2?family=Arvo&display=swap" rel="stylesheet">
    <!-- jquery -->
    <title>Subha bootstrap</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <!-- jquery -->

    <link rel="stylesheet" type="text/css" href="./html/main.css">

    <script type="text/javascript">
        function deleteCookie()
        {
            document.cookie = "avatarCookie=; expires=Thu, 01 Jan 1970 00:00:00 UTC";
        }
    </script>
</head>
<body>



<%@include file="WEB-INF/jsp/commonPart/navBar.jsp"%>


<header class="page-header header container-fluid">
   <div class="overlay"></div>
       <div class="container pt-3">
           <div class="col-sm-6">
               <h2 class="text-warning"><strong>Log In / <a class="text-warning" onclick="deleteCookie()" href="./enroll.jsp">Create new account</a></strong></h2>
               <form action="./login" method="post">
                   <label class="text-warning" for="username"><strong>Username:</strong></label>
                   <input type="text" id="username" name="username"> <br><br>
                   <label class="text-warning" for="password"><strong>Password:</strong></label>
                   <input type="password" id="password" name="password"> <br><br>
                   <input type="hidden" name="articleId" value="${articleId}">
                   <c:if test="${not empty wrongMessage}">
                       <p class="text-warning">${wrongMessage}</p><br>
                   </c:if>
                   <button class="btn btn-danger" type="submit" value="login">Login</button>
                   <%-- <input class="submit" type="submit" value="login"> --%>
               </form>
           </div>
       </div>
</header>



<script src="./html/main.js"></script>
</body>
</html>