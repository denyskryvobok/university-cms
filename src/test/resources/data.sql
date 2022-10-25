INSERT INTO groups (group_name)
VALUES ('HR-32');

INSERT INTO students (first_name, last_name, street, city, zip, country, studentCard, group_id)
VALUES ('James', 'Smith', '607 Derek Drive', 'Streetsboro', '44241', 'United States', 8201296, 1);

INSERT INTO teachers (first_name, last_name, position, street, city, zip, country)
VALUES ('Oliver', 'Taylor', 'Lecturer in Accounting', '367 Pritchard Cour', 'Owatonna', '55060', 'United States'),
       ('Jack', 'Davies', 'Associate Professorship of Computer Science', '2830 Elliot Avenue', 'Seattle', '98119', 'United States'),
       ('Harry', 'Evans', 'Senior Lecturer in Architecture', '2767 Barrington Court', 'Carryville', '72454', 'United States'),
       ('Thomas', 'Davis', 'Chemistry Teacher', '3952 Shinn Street', 'New York', '10004', 'United States'),
       ('George', 'Roberts', 'Teaching Fellow in English', '10 Tree Top Lane', 'Conshohocken', '19428', 'United States'),
       ('Jessica', 'Roberts', 'Art & Design Teacher', '2701 Fidler Drive', 'San Antonio', '78217', 'United States'),
       ('Green', 'Thomas', 'Lecturer in Psychology', '1484 Armbrester Drive', 'Irvine', '92614', 'United States'),
       ('Sarah', 'Hall', 'Lecturer in Roman History', '552 Parrish Avenue', 'San Antonio', '78205', 'United States'),
       ('Charles', 'Thomas', 'Lecturer - Foundation Law', '4632 Tanglewood Road', 'Tupelo', '38801', 'United States'),
       ('Karen', 'Clarke', 'Research Associate in the Economics', '4487 Nickel Road', 'Alhambra', '91801', 'United States');

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

INSERT INTO timetables (month, date_of_day, name_of_day, group_id, teacher_id, subject_id, subject_order)
VALUES ('OCTOBER', '2022-10-03', 'MONDAY', 1, 1, 1, 1),
       ('OCTOBER', '2022-10-03', 'MONDAY', 1, 2, 2, 2),
       ('OCTOBER', '2022-10-03', 'MONDAY', 1, 5, 5, 3),
       ('OCTOBER', '2022-10-03', 'MONDAY', 1, 6, 6, 4),
       ('OCTOBER', '2022-10-04', 'TUESDAY', 1, 2, 2, 1),
       ('OCTOBER', '2022-10-04', 'TUESDAY', 1, 7, 7, 2),
       ('OCTOBER', '2022-10-04', 'TUESDAY', 1, 9, 9, 3),
       ('OCTOBER', '2022-10-04', 'TUESDAY', 1, 10, 10, 4),
       ('OCTOBER', '2022-10-04', 'TUESDAY', 1, 1, 1, 5),
       ('OCTOBER', '2022-10-04', 'TUESDAY', 1, 3, 3, 6);
