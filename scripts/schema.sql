-- TODO: Add your database init script here. This should initialize all your tables, and add any initial data required.
drop table if exists comment;
DROP TABLE IF EXISTS article;
DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS user
(
    id        INT NOT NULL primary key AUTO_INCREMENT,
    username  VARCHAR(50)  NOT NULL,
    hashedPsw VARCHAR(100) NOT NULL,
    salt      VARCHAR(64)  NOT NULL,
    iteration INT          NOT NULL DEFAULT 100000,
    firstName VARCHAR(100) NOT NULL,
    lastName  VARCHAR(100) NOT NULL,
    birthday  DATE  NOT NULL,
    brief     TEXT,
    avatar    VARCHAR(200),
    imageName VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS article
(
    Id      INT primary key NOT NULL AUTO_INCREMENT,
    author  INT             NOT NULL,
    title   VARCHAR(100),
    time    TIMESTAMP       NOT NULL,
    content TEXT            NOT NULL,
    FOREIGN KEY (author) REFERENCES user (id) ON DELETE CASCADE
);

Create table if not exists comment
(
    id        int       primary key auto_increment,
    author    int       not null,
    content   varchar(500),
    time      timestamp not null,
    articleId int       not null,
    -- parent is null represents this is a top level comment
    parent    int,
    -- level is 0 when this is a top level comment
    level     int       not null,
    foreign key (author) references user(id) on delete cascade,
    foreign key (articleId) references article (id) ON delete cascade,
    foreign key (parent) references comment (id) ON delete cascade
);

