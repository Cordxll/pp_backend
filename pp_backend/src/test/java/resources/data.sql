insert into user(id, username, password, email)
values
(1, 'bz', 'asdf', 'asdf');

insert into friend(id, user_id)
values
(1, 1);

insert into goal(id, title, progress, timeline, is_complete, user_id)
values
(1, 'weight loss', 30, '2023-02-07', true, 1 );

insert into task(id, title, description, time, is_complete, is_public, user_id, goal_id)
values
(1, 'python learning', 'hello there', now(), false, true, 1, 1);
