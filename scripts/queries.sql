SELECT * FROM comment INNER JOIN user on comment.author = user.id;

SELECT *
FROM user;

SELECT * FROM article INNER JOIN user ON article.author = user.id;

DROP TABLE IF EXISTS writtenBy;

SELECT * FROM article INNER JOIN user ON article.author = user.id WHERE article.id = 3;

SELECT * FROM comment INNER JOIN user on comment.author = user.id WHERE articleId = 2 AND level = 0;

DROP TABLE IF EXISTS account;