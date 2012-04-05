
CREATE TABLE id_generator (
    `name`      CHAR(31)       NOT NULL,
    `value`     BIGINT         DEFAULT NULL,
    PRIMARY KEY  (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `id_generator` VALUES ('address_id', 1000);
INSERT INTO `id_generator` VALUES ('attachment_id', 1000);
INSERT INTO `id_generator` VALUES ('banned_id', 1000);
INSERT INTO `id_generator` VALUES ('category_id', 1000);
INSERT INTO `id_generator` VALUES ('forum_id', 1000);
INSERT INTO `id_generator` VALUES ('message_id', 1000);
INSERT INTO `id_generator` VALUES ('post_id', 1000);
INSERT INTO `id_generator` VALUES ('ranking_id', 1000);
INSERT INTO `id_generator` VALUES ('topic_id', 1000);

