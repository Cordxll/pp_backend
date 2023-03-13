Drop database if exists productive_people;

create database productive_people;

use productive_people;

create table `user` (
user_id int primary key auto_increment,
username varchar(200) not null,
password varchar(300) not null,
email varchar(150) not null,
color varchar(20),
picture_url varchar(400) not null,
`role` varchar(25)
);

create table goal(
goal_id int primary key auto_increment,
title varchar (50) not null,
description varchar(300),
color varchar(20),
progress int,
timeline varchar(200), 
completed boolean,
user_id int,
constraint fk_goals_user_id
foreign key (user_id)
 references user(user_id)
 );
 
 create table task(
 task_id int primary key auto_increment,
 title varchar (50) not null,
 `description` varchar (2000),
 `time` datetime,
 completed boolean,
 is_public boolean,
 user_id int,
 goal_id int,
 constraint fk_tasks_user_id 
 foreign key (user_id)
 references user(user_id),
 constraint fk_tasks_goal_id
 foreign key(goal_id) 
 references goal(goal_id)
 );
 
 create table friend(
 friend_id int primary key auto_increment,
 user_id int, 
 username varchar(100),
 constraint fk_friends_user_id
 foreign key (user_id)
 references user(user_id)
 
 );
 


