
CREATE TABLE id_generator (
    id            INT            NOT NULL,
    gen_name      CHAR(31)       NOT NULL DEFAULT '',
    gen_value     BIGINT         DEFAULT NULL,
    PRIMARY KEY  (id),
    UNIQUE (gen_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `id_generator` VALUES (1, 'address_id', 10);
INSERT INTO `id_generator` VALUES (2, 'attachment_id', 10);
INSERT INTO `id_generator` VALUES (3, 'message_id', 10);
INSERT INTO `id_generator` VALUES (4, 'post_id', 10);
INSERT INTO `id_generator` VALUES (5, 'user_id', 10);
INSERT INTO `id_generator` VALUES (6, 'topic_id', 10);

