CREATE TABLE groups
(
    group_id   bigserial PRIMARY KEY,
    group_name char(5) UNIQUE
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
    student_card int UNIQUE,
    group_id    int REFERENCES groups (group_id)
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
    country    varchar(30)
);

CREATE TABLE subjects
(
    subject_id   bigserial PRIMARY KEY,
    subject_name varchar(30)
);

CREATE TABLE timetables
(
    timetable_id bigserial PRIMARY KEY,
    month         varchar(9),
    date_of_day   date,
    name_of_day   varchar(9),
    group_id      bigint REFERENCES groups (group_id),
    teacher_id    bigint REFERENCES teachers (teacher_id),
    subject_id    bigint REFERENCES subjects (subject_id),
    subject_order smallint
);
