INSERT INTO roles VALUES ('1', 'ADMIN', 'Administrator', '1');
INSERT INTO roles VALUES ('2', 'DEV', 'Developer', '2');
INSERT INTO roles VALUES ('3', 'SUPER', 'Supervisor', '3');
INSERT INTO roles VALUES ('4', 'GMOD', 'Globle Moderator', '4');
INSERT INTO roles VALUES ('5', 'MOD', 'Moderator', '5');
INSERT INTO roles VALUES ('6', 'VET', 'Veteran', '6');
INSERT INTO roles VALUES ('7', 'MVC', 'Most Valued Contributer', '7');
INSERT INTO roles VALUES ('8', 'SUB', 'Subscriber', '8');
INSERT INTO roles VALUES ('9', 'MEMBER', 'Member', '9');

INSERT INTO groups VALUES ('1', 'ADMIN', 'Administrators', '1', '3');
INSERT INTO groups VALUES ('2', 'DEV', 'Developers', '2', '4');
INSERT INTO groups VALUES ('3', 'SUPER', 'Supervisors', '3', '4');
INSERT INTO groups VALUES ('4', 'GMOD', 'Globle Moderators', '4', '5');
INSERT INTO groups VALUES ('5', 'MOD', 'Moderators', '5', '9');
INSERT INTO groups VALUES ('6', 'VET', 'Veterans', '6', '9');
INSERT INTO groups VALUES ('7', 'MVC', 'Most Valued Contributers', '7', '9');
INSERT INTO groups VALUES ('8', 'SUB', 'Subscribers', '8', '9');
INSERT INTO groups VALUES ('9', 'MEMBER', 'Members', '9', null);

INSERT INTO group_role_map VALUES ('1', '1');
INSERT INTO group_role_map VALUES ('1', '2');
INSERT INTO group_role_map VALUES ('1', '3');
INSERT INTO group_role_map VALUES ('1', '4');
INSERT INTO group_role_map VALUES ('1', '5');
INSERT INTO group_role_map VALUES ('1', '6');
INSERT INTO group_role_map VALUES ('1', '7');
INSERT INTO group_role_map VALUES ('1', '8');
INSERT INTO group_role_map VALUES ('1', '9');
