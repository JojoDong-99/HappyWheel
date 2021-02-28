

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    function deleteCookie()
    {
        document.cookie = "avatarCookie=; expires=Thu, 01 Jan 1970 00:00:00 UTC";
    }
</script>




<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">



<div class="topnav" id="myTopnav">

    <a href="./index"><i class="fa fa-fw fa-home"></i>Home</a>
    <c:if test="${sessionScope.user != null}">
        <a href="./logout" class="right">Log out</a>
        <a href="./personalHome?id=${sessionScope.user.id}" class="right">My Page</a>
        <a href="./createArticleServlet" class="right">Insert Article</a>
    </c:if>
    <c:if test="${sessionScope.user == null}">
        <a href="./login.jsp" class="right">Log in</a>
        <a onclick="deleteCookie()" href="./createUsernameServlet" class="right" id="createAccount">Create Account</a>
    </c:if>

</div>
