drop database expensetrackerdb;
drop user expensetracker;
create user expensetracker with password 'password';
create database expensetrackerdb with template=template0 owner=expensetracker;
\connect expensetrackerdb;
alter default privileges grant all on tables to expensetracker;
alter default privileges grant all on sequences to expensetracker;

create table et_users(
                         user_id integer primary key not null,
                         first_name varchar(20) not null,
                         last_name varchar(20) not null,
                         email varchar(30) not null,
                         password text not null
);

create table et_cars(
                              car_id integer primary key not null,
                              user_id integer not null,
                              title varchar(20) not null,
                              description varchar(50) not null
);
alter table et_cars add constraint cat_users_fk
    foreign key (user_id) references et_users(user_id);

create table et_fines(
                                fine_id integer primary key not null,
                                car_id integer not null,
                                user_id integer not null,
                                fine_Price numeric(10,2) not null,
                                note varchar(50) not null,
                                fine_category varchar(50) not null,
                                fine_date bigint not null
);
alter table et_fines add constraint trans_cat_fk
    foreign key (car_id) references et_cars(car_id);
alter table et_fines add constraint trans_users_fk
    foreign key (user_id) references et_users(user_id);

create sequence et_users_seq increment 1 start 1;
create sequence et_cars_seq increment 1 start 1;
create sequence et_fines_seq increment 1 start 1000;