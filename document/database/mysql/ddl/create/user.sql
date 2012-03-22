
CREATE TABLE addresses (
    id                   INT                    NOT NULL,
    user_id              CHAR(32)               NOT NULL,
    location_id          INT                    NOT NULL,
    postal_code          VARCHAR(15)            NOT NULL,
    street               VARCHAR(255)           NOT NULL,
    priority             INTEGER                NOT NULL,
    address_type         VARCHAR(31)            NOT NULL,
    PRIMARY KEY  (id),
    INDEX address_idx (priority)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE emails (
    personal      VARCHAR(31)    NOT NULL,
    hostname      VARCHAR(31)    NOT NULL,
    user_id       CHAR(32)       NOT NULL,
    priority      INT            NOT NULL,
    PRIMARY KEY  (personal, hostname),
    INDEX email_idx (priority)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE groups (
    id            INT            NOT NULL,
    group_name    VARCHAR(127)   NOT NULL,
    description   VARCHAR(255)   NOT NULL,
    priority      INT            NOT NULL,
    parent_id     INT            NULL,
    PRIMARY KEY (id),
    UNIQUE (group_name),
    INDEX group_idx (priority)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE group_role_map (
    group_id      INT            NOT NULL,
    role_id       INT            NOT NULL,
    PRIMARY KEY (group_id, role_id)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE locations (
    id                   INT                    NOT NULL,
    location_name        VARCHAR(255)           NOT NULL,
    abbr                 CHAR(2)                NOT NULL,
    parent_id            INT                    NULL,
    level                TINYINT(1)             NOT NULL,
    available            BOOLEAN                NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE permissions (
    id                   INT            NOT NULL,
    role_id              INT            NOT NULL,
    permission           VARCHAR(255)   NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE roles (
    id                  INT            NOT NULL,
    authority           VARCHAR(127)   NOT NULL,
    description         VARCHAR(255)   NOT NULL,
    priority            INT            NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (authority),
    INDEX role_idx (priority)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE users (
    id                 CHAR(32)       NOT NULL,
    username           VARCHAR(31)    NOT NULL,
    password           VARCHAR(127)   NOT NULL,
    nickname           VARCHAR(63)    NOT NULL,
    gender             TINYINT(1)     NULL,
    famaliy_name       VARCHAR(127)   NULL,
    given_name         VARCHAR(127)   NULL,
    birthday           DATETIME       NULL,
    avatar             VARCHAR(63)    NULL,
    signature          TEXT           NULL,
    posts              INT            NOT NULL default 0,
    reputation         INT            NOT NULL default 0,
    timezone           CHAR(3)        NOT NULL,
    locale             CHAR(5)        NOT NULL,
    createdate         DATETIME       NOT NULL,
    last_active        DATETIME       NOT NULL,
    PRIMARY KEY  (id),
    UNIQUE (username)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE user_group_map (
    user_id            CHAR(32)       NOT NULL,
    group_id           INT            NOT NULL,
    PRIMARY KEY (user_id, group_id)
) ENGINE=InnoDB CHARSET=utf8;


create table user_properties (
    id                   CHAR(32)             NOT NULL,
    pkey                 VARCHAR(63)          NOT NULL,
    pvalue               VARCHAR(255)         NOT NULL,
    PRIMARY KEY (id, pkey)
) ENGINE=InnoDB CHARSET=utf8;
