
CREATE TABLE addresses (
    id                   INT                    NOT NULL,
    user_id              INT                    NOT NULL,
    location_id          INT                    NOT NULL,
    postal_code          VARCHAR(15)            NOT NULL,
    street               VARCHAR(255)           NOT NULL,
    priority             INTEGER                NOT NULL,
    `type`               VARCHAR(31)            NOT NULL,
    PRIMARY KEY  (id)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE emails (
    personal      VARCHAR(31)    NOT NULL,
    hostname      VARCHAR(31)    NOT NULL,
    user_id       INT            NOT NULL,
    priority      INTEGER        NOT NULL,
    PRIMARY KEY  (personal, hostname)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE groups (
    id            INT            NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(127)   NOT NULL,
    description   VARCHAR(255)   NOT NULL,
    priority      INTEGER        NOT NULL,
    parent_id     INT            NULL,
    PRIMARY KEY (id),
    UNIQUE (`name`)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE group_role_map (
    group_id      INT            NOT NULL,
    role_id       INT            NOT NULL,
    PRIMARY KEY (group_id, role_id)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE locations (
    id                   INT                    NOT NULL,
    `name`               VARCHAR(255)           NOT NULL,
    abbr                 CHAR(2)                NOT NULL,
    parent_id            INT                    NULL,
    level                TINYINT(1)             NOT NULL,
    available            BOOLEAN                NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE permissions (
    id                   INT            NOT NULL AUTO_INCREMENT,
    role_id              INT            NOT NULL,
    permission           VARCHAR(255)   NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE roles (
    id                  INT            NOT NULL AUTO_INCREMENT,
    authority           VARCHAR(127)   NOT NULL,
    description         VARCHAR(255)   NOT NULL,
    priority            INT            NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (authority)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE users (
    id                 INT            NOT NULL,
    username           VARCHAR(31)    NOT NULL,
    password           VARCHAR(127)   NOT NULL,
    nickname           VARCHAR(63)    NOT NULL,
    posts              INT            NOT NULL default 0,
    reputation         INT            NOT NULL default 0,
    timezone           CHAR(3)        NOT NULL,
    locale             CHAR(5)        NOT NULL,
    createdate         DATETIME       NOT NULL,
    last_active        DATETIME       NOT NULL,
    PRIMARY KEY  (id),
    UNIQUE (username),
    INDEX user_password_idx (password)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE user_group_map (
    user_id            INT            NOT NULL,
    group_id           INT            NOT NULL,
    PRIMARY KEY (user_id, group_id)
) ENGINE=InnoDB CHARSET=utf8;


create table user_profiles (
    id                 INT            NOT NULL,
    gender             TINYINT(1)     NOT NULL,
    famaliy_name       VARCHAR(127)   NOT NULL,
    given_name         VARCHAR(127)   NOT NULL,
    birthday           DATETIME       NOT NULL,
    avatar             BLOB           NULL,
    signature          TEXT           NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;


create table user_properties (
    id                   INT                  NOT NULL,
    `key`                VARCHAR(63)          NOT NULL,
    `value`              VARCHAR(255)         NOT NULL,
    PRIMARY KEY (id, `key`)
) ENGINE=InnoDB CHARSET=utf8;
