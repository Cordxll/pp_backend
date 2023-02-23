drop database if exists productive_people;
create database productive_people;
use productive_people;

create table user(
    user_id varchar(250) primary key,
    password_hash varchar(2048) not null,
    email varchar(250) not null,
    date_of_birth date,
    first_name varchar(250),
    last_name varchar(250),
    image_url varchar(400)
);
create table friendship(
    `status` varchar(100) not null,
    
    constraint fk_friendship_request_user_id
    foreign key (request_user)
    references user(user_id),
    
    constraint fk_friendship_accept_user_id
    foreign key (accept_user)
    references user(user_id),
);
create table goal(
    goal_id int primary key auto_increment,
    title varchar(250) not null, 
    progess int not null,
    is_complete boolean not null,
    is_public boolean not null

);
create table task(
    task_id int primary key auto_increment,
    title varchar(250) not null,
    `description` varchar (1000) not null,
    timestamp datetime not null default(now()),
    end_time varchar(250),
    is_public boolean not null,
    is_completed boolean not null, 

    constraint fk_task_user_id
    foreign key (user_id)
    references user(user_id),

    constraint fk_task_goal_id
    foreign key (goal_id)
    references goal(goal_id),
);

insert into user(email,password_hash,image_url) 
    values  
    ("cordell@pp.com","null","https://avatars.githubusercontent.com/u/88411627?s=48&v=4"),
    ("brandon@pp.com","null",""),
    ("yuliia@pp.com","null",""),
    ("user@pp.com","null","https://m.media-amazon.com/images/M/MV5BMTgxMTU5NjEwM15BMl5BanBnXkFtZTcwNDgyNTYyNw@@._V1_.jpg")
;

insert into friendship(request_user,accept_user,`status`)
    values
    (1,2,"accepted"),
    (1,3,"accepted"),
    (1,4,"accepted"),
    (2,3,"accepted"),
    (2,4,"accepted"),
    (3,4,"accepted"),