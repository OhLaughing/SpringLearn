CREATE TABLE `user` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT ,
  `username` varchar(32) NOT NULL ,
  `passWord` varchar(32) NOT NULL ,
  `age` INTEGER
);

insert into user(username, passWord, age) values('kobe', 'abc123', 23);
insert into user(id, username, passWord, age) values(3, 'james', 'abc123', 24);
