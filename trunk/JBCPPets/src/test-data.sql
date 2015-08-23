--insert into users(username, password, enabled) values ('admin','admin',true);
insert into users(username, password, enabled, salt) values ('admin','admin',true,CAST(RAND()*1000000000 AS varchar));
insert into users(username, password, enabled, salt) values ('guest','guest',true,CAST(RAND()*1000000000 AS varchar));

insert into authorities(username,authority) values ('admin','ROLE_USER');
insert into authorities(username,authority) values ('admin','ROLE_ADMIN');
--insert into users(username, password, enabled) values ('guest','guest',true);
insert into authorities(username,authority) values ('guest','ROLE_USER');
insert into users(username, password, enabled, salt) values ('https://pmularien.myopenid.com/','unused',true,CAST(RAND()*1000000000 AS varchar));
insert into group_members(group_id, username) select id,'https://pmularien.myopenid.com/' from groups where group_name='Administrators';

insert into users(username, password, enabled, salt) values ('https://me.yahoo.com/pmularien#9a466','unused',true,CAST(RAND()*1000000000 AS varchar));
insert into group_members(group_id, username) select id,'https://me.yahoo.com/pmularien#9a466' from groups where group_name='Administrators';
commit;
