CREATE TABLE attachments (
    id              INT                         NOT NULL,
    owner_id        INT                         NOT NULL,
    original_name   VARCHAR(127)                NOT NULL,
    content_type    CHAR(15)                    NOT NULL,
    `size`          LONG                        NOT NULL,
    downloads       INT                         NOT NULL,
    createdate      DATETIME                    NOT NULL,
    PRIMARY KEY  (id)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE attachment_datas (
    id              INT                         NOT NULL,
    content         BLOB                        NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE attachment_properties (
    id                   INT                    NOT NULL,
    `key`                VARCHAR(63)            NOT NULL,
    `value`              VARCHAR(255)           NOT NULL,
    PRIMARY KEY (id, `key`)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE categories (
    id            INT                           NOT NULL AUTO_INCREMENT,
    subject       VARCHAR(31)                   NOT NULL,
    description   VARCHAR(255)                  NOT NULL,
    priority      INT                           NOT NULL,
    status        INT                           NOT NULL,
    PRIMARY KEY  (id)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE category_moderator_map (
    category_id          INT                    NOT NULL,
    moderator_id         INT                    NOT NULL,
    PRIMARY KEY (category_id, moderator_id)
) ENGINE=InnoDB CHARSET=utf8;


create table category_properties (
    id                   INT                    NOT NULL,
    `key`                VARCHAR(63)            NOT NULL,
    `value`              VARCHAR(255)           NOT NULL,
    PRIMARY KEY (id, `key`)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE dirtywords (
    id                      INT                 NOT NULL AUTO_INCREMENT,
    word                    VARCHAR(255)        NOT NULL,
    replacement             VARCHAR(255)        NOT NULL,
    PRIMARY KEY  (id)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE drafts (
    id                      INT                 NOT NULL AUTO_INCREMENT,
    user_id                 INT                 NOT NULL,
    subject                 VARCHAR(255)        NOT NULL,
    content                 VARCHAR(255)        NOT NULL,
    modification            DATE                NOT NULL,
    PRIMARY KEY  (id)
) ENGINE=InnoDB CHARSET=utf8;


create table draft_properties (
    id                      INT                 NOT NULL,
    `key`                   VARCHAR(63)         NOT NULL,
    `value`                 VARCHAR(255)        NOT NULL,
    PRIMARY KEY (id, `key`)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE emoticons (
    id                     INT                  NOT NULL AUTO_INCREMENT,
    emoticon               VARCHAR(31)          NOT NULL,
    image                  VARCHAR(255)         NOT NULL,
    PRIMARY KEY  (id)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE forums (
    id                     INT                  NOT NULL,
    subject                VARCHAR(31)          NOT NULL,
    description            VARCHAR(255)         NOT NULL,
    priority               INT                  NOT NULL,
    topics                 INT                  NOT NULL,
    replies                INT                  NOT NULL,
    category_id            INT                  NOT NULL,
    last_post_id           INT                  NOT NULL,
    status                 INT                  NOT NULL,
    createdate             DATETIME             NOT NULL,
    PRIMARY KEY  (id),
    INDEX forums.category_id_idx (category_id),
    INDEX forums.last_post_id_idx (last_post_id)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE forum_moderator_map (
    forum_id               INT                  NOT NULL,
    moderator_id           INT                  NOT NULL,
    PRIMARY KEY (forum_id, moderator_id)
) ENGINE=InnoDB CHARSET=utf8;


create table forum_properties (
    id                   INT                    NOT NULL,
    `key`                VARCHAR(63)            NOT NULL,
    `value`              VARCHAR(255)           NOT NULL,
    PRIMARY KEY (id, `key`)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE messages (
    id                   INT                    NOT NULL AUTO_INCREMENT,
    sender_id            INT                    NOT NULL,
    subject              VARCHAR(255)           NOT NULL,
    content              TEXT                   NOT NULL,
    is_read              TINYINT(1)             NOT NULL,
    status               INT                    NOT NULL,
    createdate           DATETIME               NOT NULL,
    PRIMARY KEY  (id),
    INDEX messages.sender_id_idx (sender_id)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE message_attachment_map (
    message_id            INT                  NOT NULL,
    attachment_id         INT                  NOT NULL,
    PRIMARY KEY (message_id, attachment_id)
) ENGINE=InnoDB CHARSET=utf8;


create table message_properties (
    id                   INT                    NOT NULL,
    `key`                VARCHAR(63)            NOT NULL,
    `value`              VARCHAR(255)           NOT NULL,
    PRIMARY KEY (id, `key`)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE message_recipient_map (
    message_id           INT                    NOT NULL,
    recipient_id         INT                    NOT NULL,
    PRIMARY KEY  (message_id, recipient_id)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE posts (
    id                   INT                    NOT NULL,
    poster_id            INT                    NOT NULL,
    topic_id             INT                    NOT NULL,
    content              TEXT                   NOT NULL,
    edits                INT                    NOT NULL,
    createdate           DATETIME               NOT NULL,
    status               INT                    NOT NULL,
    PRIMARY KEY  (id),
    INDEX posts.poster_id_idx (poster_id),
    INDEX posts.topic_id_idx (topic_id)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE post_attachment_map (
    post_id               INT                  NOT NULL,
    attachment_id         INT                  NOT NULL,
    PRIMARY KEY (post_id, attachment_id)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE post_edits (
    id                   INT                   NOT NULL,
    editor_id            INT                   NOT NULL,
    editdate             DATETIME              NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;


create table post_properties (
    id                   INT                    NOT NULL,
    `key`                VARCHAR(63)            NOT NULL,
    `value`              VARCHAR(255)           NOT NULL,
    PRIMARY KEY (id, `key`)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE rankings (
    id                   INT                    NOT NULL,
    min_score            INT                    NOT NULL,
    max_score            INT                    NOT NULL,
    `name`               VARCHAR(255)           NOT NULL,
    image                VARCHAR(255)           NOT NULL,
    status               INT                    NOT NULL,
    PRIMARY KEY  (id)
) ENGINE=InnoDB CHARSET=utf8;

create table tags (
    id                  INT                     NOT NULL AUTO_INCREMENT,
    `name`              CHAR(63)                NOT NULL,
    primary key (id),
    UNIQUE (`name`)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE topics (
    id                  INT                     NOT NULL,
    forum_id            INT                     NOT NULL,
    subject             VARCHAR(255)            NOT NULL,
    views               INT                     NOT NULL,
    replies             INT                     NOT NULL,
    status              INT                     NOT NULL,
    root_post_id        INT                     NOT NULL,
    last_post_id        INT                     NOT NULL,
    is_locked           TINYINT(1)              NOT NULL,
    createdate          DATETIME                NOT NULL,
    PRIMARY KEY  (id),
    INDEX topics.root_post_id_idx (root_post_id),
    INDEX topics.last_post_id_idx (last_post_id)
) ENGINE=InnoDB CHARSET=utf8;


create table topic_properties (
    id                  INT                     NOT NULL,
    `key`               VARCHAR(63)             NOT NULL,
    `value`             VARCHAR(255)            NOT NULL,
    PRIMARY KEY (id, `key`)
) ENGINE=InnoDB CHARSET=utf8;
