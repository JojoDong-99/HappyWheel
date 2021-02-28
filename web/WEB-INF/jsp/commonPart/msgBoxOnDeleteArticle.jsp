<%--
  Created by IntelliJ IDEA.
  User: subha
  Date: 11/06/20
  Time: 8:38 am
  To change this template use File | Settings | File Templates.
--%>
<div class="container">
    <h1>${article.title}</h1>
    <p>${article.body}</p>
    <p><a href="./deleteArticles?id=${article.id}">Delete this article (WARNING: Cannot be undone!)</a></p>
</div>