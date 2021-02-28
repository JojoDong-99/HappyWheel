<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jojodong
  Date: 11/06/20
  Time: 5:28 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script type="text/javascript">
    window.onload = function () {
        const replyButtons = document.querySelectorAll ( ".reply" );

        for (let button of replyButtons) {
            button.onclick = function (e) {
                const addCommentBox = e.target.parentElement.querySelector ( "div.addCommentBox" );
                addCommentBox.style.visibility = "visible";
            }
        }

        const showCommentsBtn = document.querySelector ( "#showComments" );
        const hideCommentsBtn = document.querySelector ( "#hideComments" );
        const comments = document.querySelector ( "#comments" );
        hideCommentsBtn.onclick = function () {
            comments.style.visibility = "hidden";
            showCommentsBtn.style.visibility = "visible";
            hideCommentsBtn.style.visibility = "hidden";
        }

        showCommentsBtn.onclick = function () {
            comments.style.visibility = "visible";
            hideCommentsBtn.style.visibility = "visible";
            showCommentsBtn.style.visibility = "hidden";
        }
    }
</script>


<%@include file="/html/header.jsp" %>
<%@include file="./commonPart/navBar.jsp" %>


<div class="description">
    <div class="container pt-3">
        <div class="shadow-lg p-4 mb-4 #ffeead">


            <div class="containerArticle">
               <%-- <div id="article">
                    <h2 class="text-center">${article.title}</h2>
                    <p><strong><a href="/personalHome?id=${article.authorId}"
                                  class="link">${article.username}</a></strong>,
                        <small>${article.time}</small></p>
                    <div class="text-justify">${article.content}</div>
                    <br><br>
                </div> --%>
                <div class="container">
                    <div id="article">
                        <h2 class="overFlow">${article.title}</h2>
                        <p class="overFlow"><strong><a href="./personalHome?id=${article.authorId}"
                                                       class="link">${article.username}</a></strong>,
                            ${article.time}</p>
                        <div>${article.content}</div>
                        <br><br>
                    </div>
                    <div class="btn-toolbar btn-block">
                    <c:if test="${article.username eq user.username}">
                        <form action="./editArticle" method="get">
                            <input type="hidden" name="articleID" value="${article.id}">
                            <button class="btn btn-danger btn-sm" type="submit" id="editButton" type="button">Edit</button>
                        </form>
                        <form action="./deleteArticle" method="get">
                            <input type="hidden" name="articleID" value="${article.id}">
                            <button class="btn btn-danger btn-sm" type="submit" id="deleteButton">Delete</button>
                        </form>
                    </c:if>
                    </div>

                    <div id="commentsWrapper">
                        <h4><small>comments</small></h4>
                        <div id="commentForm">


                            <div id="commentBox" class="commentBox">
                                <c:if test="${sessionScope.user == null}">
                                    <form action="./loginToComment" method="get">
                                        <input type="hidden" name="articleId" value="${article.id}">
                                        <input type="submit" value="Log in to add comment">
                                    </form>
                                </c:if>
                                <c:if test="${sessionScope.user != null}">
                                    <form action="./AddComment" method="post">
                                        <div>
                                            <input type="hidden" name="parent" value="">
                                            <input type="hidden" name="userId" value="${sessionScope.user.id}">
                                            <input type="hidden" name="article" value="${article.id}">
                                            <input type="hidden" name="level" value="0">
                                            <textarea name="content"></textarea>
                                            <button class="btn btn-danger btn-sm" type="submit" type="button">post</button>
                                        </div>
                                    </form>
                                </c:if>
                            </div>
                        </div>
                    </div>

                    <button style="visibility: hidden" id="showComments">
                        Show Comments
                    </button>
                    <br>
                    <button id="hideComments">Hide comments</button>
                    <div style="visibility: visible" id="comments">
                        <c:forEach var="comment" items="${article.comments}">
                            <div class="commentBox">
                                <!-- 1st level comment box -->
                                <c:if test="${comment.level == 0}">


                                    <c:if test="${comment.avatar eq 'none'}">
                                        <img class="img-fluid img-circle img-thumbnail"
                                             src="./images/${comment.imageFileName}">
                                    </c:if>
                                    <c:if test="${comment.imageFileName eq 'none'}">
                                        <img class="img-fluid img-circle img-thumbnail"
                                             src="./images/${comment.avatar}">
                                    </c:if>


                                    <div comentID="${comment.id}">
                                        <p>${comment.username} / <small>${comment.time}</small></p>
                                        <p>${comment.content}</p>
                                        <input type="hidden" value="${comment.id}">
                                        <c:if test="${sessionScope.user != null}">
                                            <button class="btn btn-danger btn-sm reply">reply</button>
                                            <c:if test="${user.id == article.authorId || user.id == comment.author}">
                                                <form action="./deleteComment" method="post">
                                                    <input type="hidden" name="id" value="${comment.id}">
                                                    <input type="hidden" name="articleId" value="${article.id}">
                                                    <button class="btn btn-danger btn-sm" type="submit" type="button">delete
                                                    </button>
                                                </form>
                                            </c:if>
                                        </c:if>
                                        <form action="./AddComment" method="post">
                                            <div style="visibility: hidden" class="addCommentBox">
                                                <input type="hidden" name="parent" value="${comment.id}">
                                                <input type="hidden" name="userId" value="${sessionScope.user.id}">
                                                <input type="hidden" name="article" value="${article.id}">
                                                <input type="hidden" name="level" value="1">
                                                <textarea name="content"></textarea>
                                                <button class="btn btn-danger btn-sm" type="submit" type="button">post</button>
                                            </div>
                                        </form>

                                        <!-- 2nd level comment box -->
                                        <c:forEach var="comment" items="${comment.comments}">
                                            <c:if test="${comment.level == 1}">
                                                <div class="commentBox level-2">


                                                    <c:if test="${comment.avatar eq 'none'}">
                                                        <img class="img-fluid img-circle img-thumbnail"
                                                             src="./images/${comment.imageFileName}">
                                                    </c:if>
                                                    <c:if test="${comment.imageFileName eq 'none'}">
                                                        <img class="img-fluid img-circle img-thumbnail"
                                                             src="./images/${comment.avatar}">
                                                    </c:if>


                                                        <%--                                    <img src="./images/${comment.avatar}">--%>
                                                    <div>
                                                        <p>${comment.username} / <small>${comment.time}</small></p>
                                                        <p>${comment.content}</p>
                                                        <input type="hidden" value="${comment.id}">
                                                        <c:if test="${sessionScope.user != null}">
                                                            <button class="btn btn-danger btn-sm reply">reply</button>
                                                            <c:if test="${user.id == article.authorId || user.id == comment.author}">
                                                                <form action="./deleteComment" method="post">
                                                                    <input type="hidden" name="id"
                                                                           value="${comment.id}">
                                                                    <input type="hidden" name="articleId"
                                                                           value="${article.id}">
                                                                    <button class="btn btn-danger btn-sm" type="submit">
                                                                        delete
                                                                    </button>
                                                                </form>
                                                            </c:if>
                                                        </c:if>
                                                        <form action="./AddComment" method="post">
                                                            <div style="visibility: hidden" class="addCommentBox">
                                                                <input type="hidden" name="parent"
                                                                       value="${comment.id}">
                                                                <input type="hidden" name="userId"
                                                                       value="${sessionScope.user.id}">
                                                                <input type="hidden" name="article"
                                                                       value="${article.id}">
                                                                <input type="hidden" name="level" value="2">
                                                                <textarea name="content"></textarea>
                                                                <button class="btn btn-danger btn-sm" type="submit"
                                                                        type="button">post
                                                                </button>
                                                            </div>
                                                        </form>

                                                        <!-- 3rd level comment box -->
                                                        <c:forEach var="comment" items="${comment.comments}">
                                                            <c:if test="${comment.level == 2}">
                                                                <div class="commentBox level-3">


                                                                    <c:if test="${comment.avatar eq 'none'}">
                                                                        <img class="img-fluid img-circle img-thumbnail"
                                                                             src="./images/${comment.imageFileName}">
                                                                    </c:if>
                                                                    <c:if test="${comment.imageFileName eq 'none'}">
                                                                        <img class="img-fluid img-circle img-thumbnail"
                                                                             src="./images/${comment.avatar}">
                                                                    </c:if>


                                                                        <%--                                                    <img src="./images/${comment.avatar}">--%>
                                                                    <p>${comment.username} /
                                                                        <small>${comment.time}</small></p>
                                                                    <p>${comment.content}</p>
                                                                    <input type="hidden" value="${comment.id}">
                                                                    <c:if test="${user.id == article.authorId || user.id == comment.author}">
                                                                        <form action="./deleteComment" method="post">
                                                                            <input type="hidden" name="id"
                                                                                   value="${comment.id}">
                                                                            <input type="hidden" name="articleId"
                                                                                   value="${article.id}">
                                                                            <button class="btn btn-danger btn-sm"
                                                                                    class="btn btn-danger btn-sm"
                                                                                    type="submit">delete
                                                                            </button>
                                                                        </form>
                                                                    </c:if>
                                                                </div>
                                                            </c:if>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </c:if>
                            </div>
                        </c:forEach>
                    </div>
                </div>


            </div>
        </div>
    </div>

<%@include file="/html/footer.jsp" %>