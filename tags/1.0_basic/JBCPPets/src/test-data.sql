--insert into users(username, password, enabled) values ('admin','admin',true);
insert into users(username, password, enabled, salt) values ('admin','admin',true,CAST(RAND()*1000000000 AS varchar));
insert into users(username, password, enabled, salt) values ('guest','guest',true,CAST(RAND()*1000000000 AS varchar));

insert into authorities(username,authority) values ('admin','ROLE_USER');
insert into authorities(username,authority) values ('admin','ROLE_ADMIN');
--insert into users(username, password, enabled) values ('guest','guest',true);
insert into authorities(username,authority) values ('guest','ROLE_USER');
commit;
