DROP SCHEMA IF EXISTS university_schedule_web_app CASCADE;
CREATE SCHEMA university_schedule_web_app;

CREATE TABLE users
(
    user_id   serial PRIMARY KEY,
    user_name varchar(30) UNIQUE NOT NULL,
    password  text               NOT NULL,
    active    bool
);

CREATE TABLE roles
(
    role_id   serial PRIMARY KEY,
    role_name varchar(30) UNIQUE NOT NULL
);

CREATE TABLE user_role
(
    user_id int REFERENCES users (user_id),
    role_id int REFERENCES roles (role_id)
);

CREATE TABLE groups
(
    group_id   bigserial PRIMARY KEY,
    group_name varchar(15) UNIQUE
);

CREATE TABLE calendar
(
    calendar_id bigserial PRIMARY KEY,
    date_of_day date
);

CREATE TABLE students
(
    student_id  bigserial PRIMARY KEY,
    first_name  varchar(10),
    last_name   varchar(10),
    street      varchar(40),
    city        varchar(20),
    zip         varchar(5),
    country     varchar(30),
    student_card varchar(7) UNIQUE,
    group_id    int REFERENCES groups (group_id),
    user_id     int REFERENCES users (user_id)
);

CREATE TABLE teachers
(
    teacher_id bigserial PRIMARY KEY,
    first_name varchar(10),
    last_name  varchar(10),
    position   varchar(80),
    street     varchar(40),
    city       varchar(20),
    zip        varchar(5),
    country    varchar(30),
    user_id    int REFERENCES users (user_id)
);

CREATE TABLE subjects
(
    subject_id   bigserial PRIMARY KEY,
    subject_name varchar(30)
);

CREATE TABLE timetables
(
    timetable_id  bigserial PRIMARY KEY,
    calendar_id   bigint REFERENCES calendar (calendar_id),
    group_id      bigint REFERENCES groups (group_id),
    teacher_id    bigint REFERENCES teachers (teacher_id),
    subject_id    bigint REFERENCES subjects (subject_id),
    subject_order smallint
);
