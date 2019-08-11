CREATE DATABASE chapter3;

USE chapter3;

CREATE TABLE t_role (
  id        INT(12) AUTO_INCREMENT,
  role_name VARCHAR(60)  NOT NULL,
  note      VARCHAR(256) NULL,
  PRIMARY KEY (id)
);

INSERT INTO t_role (role_name, note) VALUES ('role_name_1', 'note_1');
INSERT INTO t_role (role_name, note) VALUES ('role_name_2', 'note_4');
INSERT INTO t_role (role_name, note) VALUES ('role_name_4', 'note_2');
INSERT INTO t_role (role_name, note) VALUES ('role_name_3', 'note_3');

CREATE TABLE STUDENT (
  id      INT(12) AUTO_INCREMENT,
  name    VARCHAR(60) NOT NULL,
  address VARCHAR(60),
  major   VARCHAR(60),
  PRIMARY KEY (id)
);

INSERT INTO STUDENT (name, address, major) VALUE ('xiaoming', 'beijing', 'computer');
INSERT INTO STUDENT (name, address, major) VALUE ('xiaoliang', 'shanghai', 'communication');
INSERT INTO STUDENT (name, address, major) VALUE ('xiaoli', 'guangzhou', 'math');
INSERT INTO STUDENT (name, address, major) VALUE ('zhoujielun', 'guangzhou', 'math');
INSERT INTO STUDENT (name, address, major) VALUE ('james', 'fujian', 'computer');
INSERT INTO STUDENT (name, address, major) VALUE ('kobe', 'shanghai', 'math');
INSERT INTO STUDENT (name, address, major) VALUE ('xiaozhang', 'guangzhou', 'computer');