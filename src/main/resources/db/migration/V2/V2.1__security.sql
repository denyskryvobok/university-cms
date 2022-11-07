CREATE TABLE users
(
    user_id   serial PRIMARY KEY,
    user_name varchar(30) UNIQUE NOT NULL,
    password  text NOT NULL,
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

ALTER TABLE students
    ADD COLUMN user_id int;
ALTER TABLE students
    ADD FOREIGN KEY (user_id) REFERENCES users (user_id);
ALTER TABLE teachers
    ADD COLUMN user_id int;
ALTER TABLE teachers
    ADD FOREIGN KEY (user_id) REFERENCES users (user_id);

INSERT INTO roles(role_name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_STUDENT'),
       ('ROLE_TEACHER');

INSERT INTO users (user_name, password, active)
VALUES ('jamessmith', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('maryjones', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('roberttaylor', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('patriciabrown', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('johnwilliams', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('jenniferwilson', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('michaeljohnson', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('lindadavies', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('davidpatel', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('elizabethrobinson', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('williamwright', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('barbarathompson', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('richardevans', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('susanwalker', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('josephwhite', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('olivertaylor', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('jackdavies', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('harryevans', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('thomasdavis', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('georgeroberts', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('jessicaroberts', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('greenthomas', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('sarahhall', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('charlesthomas', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('karenclarke', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true);

INSERT INTO user_role
VALUES (1, 2),
       (2, 2),
       (3, 2),
       (4, 2),
       (5, 2),
       (6, 2),
       (7, 2),
       (8, 2),
       (9, 2),
       (10, 2),
       (11, 2),
       (12, 2),
       (13, 2),
       (14, 2),
       (15, 2),
       (16, 3),
       (16, 1),
       (17, 3),
       (18, 3),
       (19, 3),
       (20, 3),
       (21, 3),
       (22, 3),
       (23, 3),
       (24, 3),
       (25, 3);

UPDATE students
SET user_id = 1
WHERE student_id = 1;
UPDATE students
SET user_id = 2
WHERE student_id = 2;
UPDATE students
SET user_id = 3
WHERE student_id = 3;
UPDATE students
SET user_id = 4
WHERE student_id = 4;
UPDATE students
SET user_id = 5
WHERE student_id = 5;
UPDATE students
SET user_id = 6
WHERE student_id = 6;
UPDATE students
SET user_id = 7
WHERE student_id = 7;
UPDATE students
SET user_id = 8
WHERE student_id = 8;
UPDATE students
SET user_id = 9
WHERE student_id = 9;
UPDATE students
SET user_id = 10
WHERE student_id = 10;
UPDATE students
SET user_id = 11
WHERE student_id = 11;
UPDATE students
SET user_id = 12
WHERE student_id = 12;
UPDATE students
SET user_id = 13
WHERE student_id = 13;
UPDATE students
SET user_id = 14
WHERE student_id = 14;
UPDATE students
SET user_id = 15
WHERE student_id = 15;

UPDATE teachers
SET user_id = 16
WHERE teacher_id = 1;
UPDATE teachers
SET user_id = 17
WHERE teacher_id = 2;
UPDATE teachers
SET user_id = 18
WHERE teacher_id = 3;
UPDATE teachers
SET user_id = 19
WHERE teacher_id = 4;
UPDATE teachers
SET user_id = 20
WHERE teacher_id = 5;
UPDATE teachers
SET user_id = 21
WHERE teacher_id = 6;
UPDATE teachers
SET user_id = 22
WHERE teacher_id = 7;
UPDATE teachers
SET user_id = 23
WHERE teacher_id = 8;
UPDATE teachers
SET user_id = 24
WHERE teacher_id = 9;
UPDATE teachers
SET user_id = 25
WHERE teacher_id = 10;
