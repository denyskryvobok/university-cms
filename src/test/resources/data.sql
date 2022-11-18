INSERT INTO roles(role_name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_STUDENT'),
       ('ROLE_TEACHER');

INSERT INTO users (user_name, password, active)
VALUES ('jamessmith', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('olivertaylor', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('jackdavies', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('harryevans', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('thomasdavis', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('georgeroberts', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('jessicaroberts', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('greenthomas', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('sarahhall', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('charlesthomas', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('karenclarke', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('susanwalker', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('roberttaylor', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true),
       ('patriciabrown', '$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu', true);

INSERT INTO user_role
VALUES (1, 2),
       (2, 3),
       (2, 1),
       (3, 3),
       (4, 3),
       (5, 3),
       (6, 3),
       (7, 3),
       (8, 3),
       (9, 3),
       (10, 3),
       (11, 3),
       (12, 2),
       (13, 2),
       (14, 2);

INSERT INTO groups (group_name)
VALUES ('HR-32'),
       ('YJ-32');

INSERT INTO students (first_name, last_name, street, city, zip, country, student_card, group_id, user_id)
VALUES ('James', 'Smith', '607 Derek Drive', 'Streetsboro', '44241', 'United States', '8201296', 1, 1),
       ('Susan', 'Walker', '8 Terra Street', 'Renton', '98055', 'United States', '2873192', 1, 12),
       ('Robert', 'Taylor', '4232 Pick Street', 'Denver', '80202', 'United States', '7219310', 2, 13),
       ('Patricia', 'Brown', '1348 Mesa Drive', 'Laughlin', '89046', 'United States', '6190802', 2, 14);

INSERT INTO teachers (first_name, last_name, position, street, city, zip, country, user_id)
VALUES ('Oliver', 'Taylor', 'Lecturer in Accounting', '367 Pritchard Cour', 'Owatonna', '55060', 'United States', 2),
       ('Jack', 'Davies', 'Associate Professorship of Computer Science', '2830 Elliot Avenue', 'Seattle', '98119',
        'United States', 3),
       ('Harry', 'Evans', 'Senior Lecturer in Architecture', '2767 Barrington Court', 'Carryville', '72454',
        'United States', 4),
       ('Thomas', 'Davis', 'Chemistry Teacher', '3952 Shinn Street', 'New York', '10004', 'United States', 5),
       ('George', 'Roberts', 'Teaching Fellow in English', '10 Tree Top Lane', 'Conshohocken', '19428', 'United States',
        6),
       ('Jessica', 'Roberts', 'Art & Design Teacher', '2701 Fidler Drive', 'San Antonio', '78217', 'United States', 7),
       ('Green', 'Thomas', 'Lecturer in Psychology', '1484 Armbrester Drive', 'Irvine', '92614', 'United States', 8),
       ('Sarah', 'Hall', 'Lecturer in Roman History', '552 Parrish Avenue', 'San Antonio', '78205', 'United States', 9),
       ('Charles', 'Thomas', 'Lecturer - Foundation Law', '4632 Tanglewood Road', 'Tupelo', '38801', 'United States',
        10),
       ('Karen', 'Clarke', 'Research Associate in the Economics', '4487 Nickel Road', 'Alhambra', '91801',
        'United States', 11);

INSERT INTO subjects (subject_name)
VALUES ('Accounting and Finance'),
       ('Computer Science'),
       ('Architecture'),
       ('Chemistry'),
       ('English'),
       ('Art'),
       ('Psychology'),
       ('History'),
       ('Law'),
       ('Economics');

INSERT INTO calendar(date_of_day)
VALUES ('2022-10-03'),
       ('2022-10-04');

INSERT INTO timetables (calendar_id, group_id, teacher_id, subject_id, subject_order)
VALUES (1, 1, 1, 1, 1),
       (1, 1, 2, 2, 2),
       (1, 1, 1, 1, 3),
       (1, 1, 5, 5, 4),
       (1, 1, 6, 6, 5),
       (2, 1, 2, 2, 1),
       (2, 1, 7, 7, 2),
       (2, 1, 9, 9, 3),
       (2, 1, 10, 10, 4),
       (2, 1, 1, 1, 5),
       (2, 1, 3, 3, 6);
