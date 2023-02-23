use productive_people;

select * from user;

insert into user(username, password, email, role, color)
values
('bz', 'asdf', 'asdfasdf', 'USER', 'blue');

 insert into goal(title, progress, timeline, completed, user_id, description, color)
 values
 ('Lose weight', 50, '2020-02-07', false, 1, "lift weights", "red"),
 ('Read Book', 15, '2021-12-10', false, 1, "read man's search for meaning", "blue");

select * from user;

insert into goals(title, progress, timeline, is_complete, user_id)
values
('weight loss', 30, '2023-02-07', true, 5);



SET SQL_SAFE_UPDATES = 0;

delete from goals;
delete from tasks;
delete from user;

SET SQL_SAFE_UPDATES = 1;
 
 select * from goal;