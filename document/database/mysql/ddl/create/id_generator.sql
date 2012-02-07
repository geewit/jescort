
CREATE TABLE id_generator (
    gen_name      CHAR(31)       NOT NULL,
    gen_value     BIGINT         DEFAULT NULL,
    PRIMARY KEY  (gen_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `id_generator` VALUES ('address_id', 10);
INSERT INTO `id_generator` VALUES ('attachment_id', 10);
INSERT INTO `id_generator` VALUES ('message_id', 10);
INSERT INTO `id_generator` VALUES ('post_id', 10);
INSERT INTO `id_generator` VALUES ('user_id', 10);
INSERT INTO `id_generator` VALUES ('topic_id', 10);

