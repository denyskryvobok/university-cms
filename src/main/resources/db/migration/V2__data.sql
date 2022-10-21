INSERT INTO groups (group_name)
VALUES ('HR-32'),
       ('YJ-56'),
       ('HN-12');

INSERT INTO students (first_name, last_name, street, city, zip, country, studentCard, group_id)
VALUES ('James', 'Smith', '607 Derek Drive', 'Streetsboro', '44241', 'United States', 8201296, 1),
       ('Mary', 'Jones', '1349 Lords Way', 'Memphis', '38110', 'United States', 7641097, 1),
       ('Robert', 'Taylor', '4232 Pick Street', 'Denver', '80202', 'United States', 7219310, 1),
       ('Patricia', 'Brown', '1348 Mesa Drive', 'Laughlin', '89046', 'United States', 6190802, 1),
       ('John', 'Williams', '2120 Jenna Lane', 'Des Moines', '50317', 'United States', 2979129, 1),
       ('Jennifer', 'Wilson', '1899 Williams Lane', 'Wichita', '67218', 'United States', 8671016, 2),
       ('Michael', 'Johnson', '1930 Peck Court', '1930 Peck Court', '92618', 'United States', 7168163, 2),
       ('Linda', 'Davies', '4290 Cottrill Lane', 'Maryland Heights', '63043', 'United States', 3127819, 2),
       ('David', 'Patel', '4828 Snyder Avenue', 'Fisher', '61843', 'United States', 7541095, 2),
       ('Elizabeth', 'Robinson', ' 277 Tree Top Lane', 'Lansdowne', '19050', 'United States', 7419254, 2),
       ('William', 'Wright', '601 Franklin Street', 'Dothan', '36303', 'United States', 7543910, 3),
       ('Barbara', 'Thompson', '4836 Hampton Meadows', 'South Boston', '02127', 'United States', 6542931, 3),
       ('Richard', 'Evans', '774 Villa Drive', 'Plymouth', '46563', 'United States', 3751012, 3),
       ('Susan', 'Walker', '8 Terra Street', 'Renton', '98055', 'United States', 2873192, 3),
       ('Joseph', 'White', '1259 Cabell Avenue', 'Arlington', '22204', 'United States', 2123129, 3);

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
       ('OCTOBER', '2022-10-04', 'TUESDAY', 1, 3, 3, 6),
       ('OCTOBER', '2022-10-05', 'WEDNESDAY', 1, 4, 4, 1),
       ('OCTOBER', '2022-10-05', 'WEDNESDAY', 1, 8, 8, 2),
       ('OCTOBER', '2022-10-05', 'WEDNESDAY', 1, 9, 9, 3),
       ('OCTOBER', '2022-10-05', 'WEDNESDAY', 1, 5, 5, 4),
       ('OCTOBER', '2022-10-06', 'THURSDAY', 1, 1, 1, 1),
       ('OCTOBER', '2022-10-06', 'THURSDAY', 1, 10, 10, 2),
       ('OCTOBER', '2022-10-06', 'THURSDAY', 1, 7, 7, 3),
       ('OCTOBER', '2022-10-07', 'FRIDAY', 1, 1, 1, 1),
       ('OCTOBER', '2022-10-07', 'FRIDAY', 1, 2, 2, 2),
       ('OCTOBER', '2022-10-07', 'FRIDAY', 1, 6, 6, 3),
       ('OCTOBER', '2022-10-07', 'FRIDAY', 1, 4, 4, 4),
       ('OCTOBER', '2022-10-10', 'MONDAY', 1, 1, 1, 1),
       ('OCTOBER', '2022-10-10', 'MONDAY', 1, 2, 2, 2),
       ('OCTOBER', '2022-10-10', 'MONDAY', 1, 5, 5, 3),
       ('OCTOBER', '2022-10-10', 'MONDAY', 1, 6, 6, 4),
       ('OCTOBER', '2022-10-11', 'TUESDAY', 1, 2, 2, 1),
       ('OCTOBER', '2022-10-11', 'TUESDAY', 1, 7, 7, 2),
       ('OCTOBER', '2022-10-11', 'TUESDAY', 1, 9, 9, 3),
       ('OCTOBER', '2022-10-11', 'TUESDAY', 1, 10, 10, 4),
       ('OCTOBER', '2022-10-11', 'TUESDAY', 1, 1, 1, 5),
       ('OCTOBER', '2022-10-11', 'TUESDAY', 1, 3, 3, 6),
       ('OCTOBER', '2022-10-12', 'WEDNESDAY', 1, 4, 4, 1),
       ('OCTOBER', '2022-10-12', 'WEDNESDAY', 1, 8, 8, 2),
       ('OCTOBER', '2022-10-12', 'WEDNESDAY', 1, 9, 9, 3),
       ('OCTOBER', '2022-10-12', 'WEDNESDAY', 1, 5, 5, 4),
       ('OCTOBER', '2022-10-13', 'THURSDAY', 1, 1, 1, 1),
       ('OCTOBER', '2022-10-13', 'THURSDAY', 1, 10, 10, 2),
       ('OCTOBER', '2022-10-13', 'THURSDAY', 1, 7, 7, 3),
       ('OCTOBER', '2022-10-14', 'FRIDAY', 1, 1, 1, 1),
       ('OCTOBER', '2022-10-14', 'FRIDAY', 1, 2, 2, 2),
       ('OCTOBER', '2022-10-14', 'FRIDAY', 1, 6, 6, 3),
       ('OCTOBER', '2022-10-14', 'FRIDAY', 1, 4, 4, 4),
       ('OCTOBER', '2022-10-17', 'MONDAY', 1, 1, 1, 1),
       ('OCTOBER', '2022-10-17', 'MONDAY', 1, 2, 2, 2),
       ('OCTOBER', '2022-10-17', 'MONDAY', 1, 5, 5, 3),
       ('OCTOBER', '2022-10-17', 'MONDAY', 1, 6, 6, 4),
       ('OCTOBER', '2022-10-18', 'TUESDAY', 1, 2, 2, 1),
       ('OCTOBER', '2022-10-18', 'TUESDAY', 1, 7, 7, 2),
       ('OCTOBER', '2022-10-18', 'TUESDAY', 1, 9, 9, 3),
       ('OCTOBER', '2022-10-18', 'TUESDAY', 1, 10, 10, 4),
       ('OCTOBER', '2022-10-18', 'TUESDAY', 1, 1, 1, 5),
       ('OCTOBER', '2022-10-18', 'TUESDAY', 1, 3, 3, 6),
       ('OCTOBER', '2022-10-19', 'WEDNESDAY', 1, 4, 4, 1),
       ('OCTOBER', '2022-10-19', 'WEDNESDAY', 1, 8, 8, 2),
       ('OCTOBER', '2022-10-19', 'WEDNESDAY', 1, 9, 9, 3),
       ('OCTOBER', '2022-10-19', 'WEDNESDAY', 1, 5, 5, 4),
       ('OCTOBER', '2022-10-20', 'THURSDAY', 1, 1, 1, 1),
       ('OCTOBER', '2022-10-20', 'THURSDAY', 1, 10, 10, 2),
       ('OCTOBER', '2022-10-20', 'THURSDAY', 1, 7, 7, 3),
       ('OCTOBER', '2022-10-21', 'FRIDAY', 1, 1, 1, 1),
       ('OCTOBER', '2022-10-21', 'FRIDAY', 1, 2, 2, 2),
       ('OCTOBER', '2022-10-21', 'FRIDAY', 1, 6, 6, 3),
       ('OCTOBER', '2022-10-21', 'FRIDAY', 1, 4, 4, 4),
       ('OCTOBER', '2022-10-24', 'MONDAY', 1, 1, 1, 1),
       ('OCTOBER', '2022-10-24', 'MONDAY', 1, 2, 2, 2),
       ('OCTOBER', '2022-10-24', 'MONDAY', 1, 5, 5, 3),
       ('OCTOBER', '2022-10-24', 'MONDAY', 1, 6, 6, 4),
       ('OCTOBER', '2022-10-25', 'TUESDAY', 1, 2, 2, 1),
       ('OCTOBER', '2022-10-25', 'TUESDAY', 1, 7, 7, 2),
       ('OCTOBER', '2022-10-25', 'TUESDAY', 1, 9, 9, 3),
       ('OCTOBER', '2022-10-25', 'TUESDAY', 1, 10, 10, 4),
       ('OCTOBER', '2022-10-25', 'TUESDAY', 1, 1, 1, 5),
       ('OCTOBER', '2022-10-25', 'TUESDAY', 1, 3, 3, 6),
       ('OCTOBER', '2022-10-26', 'WEDNESDAY', 1, 4, 4, 1),
       ('OCTOBER', '2022-10-26', 'WEDNESDAY', 1, 8, 8, 2),
       ('OCTOBER', '2022-10-26', 'WEDNESDAY', 1, 9, 9, 3),
       ('OCTOBER', '2022-10-26', 'WEDNESDAY', 1, 5, 5, 4),
       ('OCTOBER', '2022-10-27', 'THURSDAY', 1, 1, 1, 1),
       ('OCTOBER', '2022-10-27', 'THURSDAY', 1, 10, 10, 2),
       ('OCTOBER', '2022-10-27', 'THURSDAY', 1, 7, 7, 3),
       ('OCTOBER', '2022-10-28', 'FRIDAY', 1, 1, 1, 1),
       ('OCTOBER', '2022-10-28', 'FRIDAY', 1, 2, 2, 2),
       ('OCTOBER', '2022-10-28', 'FRIDAY', 1, 6, 6, 3),
       ('OCTOBER', '2022-10-28', 'FRIDAY', 1, 4, 4, 4),
       ('OCTOBER', '2022-10-31', 'MONDAY', 1, 1, 1, 1),
       ('OCTOBER', '2022-10-31', 'MONDAY', 1, 2, 2, 2),
       ('OCTOBER', '2022-10-31', 'MONDAY', 1, 5, 5, 3),
       ('OCTOBER', '2022-10-31', 'MONDAY', 1, 6, 6, 4),
       ('OCTOBER', '2022-10-03', 'MONDAY', 2, 2, 2, 1),
       ('OCTOBER', '2022-10-03', 'MONDAY', 2, 5, 5, 2),
       ('OCTOBER', '2022-10-03', 'MONDAY', 2, 7, 7, 3),
       ('OCTOBER', '2022-10-03', 'MONDAY', 2, 10, 10, 4),
       ('OCTOBER', '2022-10-04', 'TUESDAY', 2, 7, 7, 1),
       ('OCTOBER', '2022-10-04', 'TUESDAY', 2, 9, 9, 2),
       ('OCTOBER', '2022-10-04', 'TUESDAY', 2, 3, 3, 3),
       ('OCTOBER', '2022-10-04', 'TUESDAY', 2, 8, 8, 4),
       ('OCTOBER', '2022-10-04', 'TUESDAY', 2, 10, 10, 5),
       ('OCTOBER', '2022-10-04', 'TUESDAY', 2, 6, 6, 6),
       ('OCTOBER', '2022-10-05', 'WEDNESDAY', 2, 8, 8, 1),
       ('OCTOBER', '2022-10-05', 'WEDNESDAY', 2, 4, 4, 2),
       ('OCTOBER', '2022-10-05', 'WEDNESDAY', 2, 5, 5, 3),
       ('OCTOBER', '2022-10-05', 'WEDNESDAY', 2, 9, 9, 4),
       ('OCTOBER', '2022-10-06', 'THURSDAY', 2, 7, 7, 1),
       ('OCTOBER', '2022-10-06', 'THURSDAY', 2, 6, 6, 2),
       ('OCTOBER', '2022-10-06', 'THURSDAY', 2, 5, 5, 3),
       ('OCTOBER', '2022-10-06', 'THURSDAY', 2, 2, 2, 4),
       ('OCTOBER', '2022-10-07', 'FRIDAY', 2, 5, 5, 1),
       ('OCTOBER', '2022-10-07', 'FRIDAY', 2, 1, 1, 2),
       ('OCTOBER', '2022-10-07', 'FRIDAY', 2, 4, 4, 3),
       ('OCTOBER', '2022-10-07', 'FRIDAY', 2, 3, 3, 4),
       ('OCTOBER', '2022-10-10', 'MONDAY', 2, 2, 2, 1),
       ('OCTOBER', '2022-10-10', 'MONDAY', 2, 5, 5, 2),
       ('OCTOBER', '2022-10-10', 'MONDAY', 2, 7, 7, 3),
       ('OCTOBER', '2022-10-10', 'MONDAY', 2, 10, 10, 4),
       ('OCTOBER', '2022-10-11', 'TUESDAY', 2, 7, 7, 1),
       ('OCTOBER', '2022-10-11', 'TUESDAY', 2, 9, 9, 2),
       ('OCTOBER', '2022-10-11', 'TUESDAY', 2, 3, 3, 3),
       ('OCTOBER', '2022-10-11', 'TUESDAY', 2, 8, 8, 4),
       ('OCTOBER', '2022-10-11', 'TUESDAY', 2, 10, 10, 5),
       ('OCTOBER', '2022-10-11', 'TUESDAY', 2, 6, 6, 6),
       ('OCTOBER', '2022-10-12', 'WEDNESDAY', 2, 8, 8, 1),
       ('OCTOBER', '2022-10-12', 'WEDNESDAY', 2, 4, 4, 2),
       ('OCTOBER', '2022-10-12', 'WEDNESDAY', 2, 5, 5, 3),
       ('OCTOBER', '2022-10-12', 'WEDNESDAY', 2, 9, 9, 4),
       ('OCTOBER', '2022-10-13', 'THURSDAY', 2, 7, 7, 1),
       ('OCTOBER', '2022-10-13', 'THURSDAY', 2, 6, 6, 2),
       ('OCTOBER', '2022-10-13', 'THURSDAY', 2, 5, 5, 3),
       ('OCTOBER', '2022-10-13', 'THURSDAY', 2, 2, 2, 4),
       ('OCTOBER', '2022-10-14', 'FRIDAY', 2, 5, 5, 1),
       ('OCTOBER', '2022-10-14', 'FRIDAY', 2, 1, 1, 2),
       ('OCTOBER', '2022-10-14', 'FRIDAY', 2, 4, 4, 3),
       ('OCTOBER', '2022-10-14', 'FRIDAY', 2, 3, 3, 4),
       ('OCTOBER', '2022-10-17', 'MONDAY', 2, 2, 2, 1),
       ('OCTOBER', '2022-10-17', 'MONDAY', 2, 5, 5, 2),
       ('OCTOBER', '2022-10-17', 'MONDAY', 2, 7, 7, 3),
       ('OCTOBER', '2022-10-17', 'MONDAY', 2, 10, 10, 4),
       ('OCTOBER', '2022-10-18', 'TUESDAY', 2, 7, 7, 1),
       ('OCTOBER', '2022-10-18', 'TUESDAY', 2, 9, 9, 2),
       ('OCTOBER', '2022-10-18', 'TUESDAY', 2, 3, 3, 3),
       ('OCTOBER', '2022-10-18', 'TUESDAY', 2, 8, 8, 4),
       ('OCTOBER', '2022-10-18', 'TUESDAY', 2, 10, 10, 5),
       ('OCTOBER', '2022-10-18', 'TUESDAY', 2, 6, 6, 6),
       ('OCTOBER', '2022-10-19', 'WEDNESDAY', 2, 8, 8, 1),
       ('OCTOBER', '2022-10-19', 'WEDNESDAY', 2, 4, 4, 2),
       ('OCTOBER', '2022-10-19', 'WEDNESDAY', 2, 5, 5, 3),
       ('OCTOBER', '2022-10-19', 'WEDNESDAY', 2, 9, 9, 4),
       ('OCTOBER', '2022-10-20', 'THURSDAY', 2, 7, 7, 1),
       ('OCTOBER', '2022-10-20', 'THURSDAY', 2, 6, 6, 2),
       ('OCTOBER', '2022-10-20', 'THURSDAY', 2, 5, 5, 3),
       ('OCTOBER', '2022-10-20', 'THURSDAY', 2, 2, 2, 4),
       ('OCTOBER', '2022-10-21', 'FRIDAY', 2, 5, 5, 1),
       ('OCTOBER', '2022-10-21', 'FRIDAY', 2, 1, 1, 2),
       ('OCTOBER', '2022-10-21', 'FRIDAY', 2, 4, 4, 3),
       ('OCTOBER', '2022-10-21', 'FRIDAY', 2, 3, 3, 4),
       ('OCTOBER', '2022-10-24', 'MONDAY', 2, 2, 2, 1),
       ('OCTOBER', '2022-10-24', 'MONDAY', 2, 5, 5, 2),
       ('OCTOBER', '2022-10-24', 'MONDAY', 2, 7, 7, 3),
       ('OCTOBER', '2022-10-24', 'MONDAY', 2, 10, 10, 4),
       ('OCTOBER', '2022-10-25', 'TUESDAY', 2, 7, 7, 1),
       ('OCTOBER', '2022-10-25', 'TUESDAY', 2, 9, 9, 2),
       ('OCTOBER', '2022-10-25', 'TUESDAY', 2, 3, 3, 3),
       ('OCTOBER', '2022-10-25', 'TUESDAY', 2, 8, 8, 4),
       ('OCTOBER', '2022-10-25', 'TUESDAY', 2, 10, 10, 5),
       ('OCTOBER', '2022-10-25', 'TUESDAY', 2, 6, 6, 6),
       ('OCTOBER', '2022-10-26', 'WEDNESDAY', 2, 8, 8, 1),
       ('OCTOBER', '2022-10-26', 'WEDNESDAY', 2, 4, 4, 2),
       ('OCTOBER', '2022-10-26', 'WEDNESDAY', 2, 5, 5, 3),
       ('OCTOBER', '2022-10-26', 'WEDNESDAY', 2, 9, 9, 4),
       ('OCTOBER', '2022-10-27', 'THURSDAY', 2, 7, 7, 1),
       ('OCTOBER', '2022-10-27', 'THURSDAY', 2, 6, 6, 2),
       ('OCTOBER', '2022-10-27', 'THURSDAY', 2, 5, 5, 3),
       ('OCTOBER', '2022-10-27', 'THURSDAY', 2, 2, 2, 4),
       ('OCTOBER', '2022-10-28', 'FRIDAY', 2, 5, 5, 1),
       ('OCTOBER', '2022-10-28', 'FRIDAY', 2, 1, 1, 2),
       ('OCTOBER', '2022-10-28', 'FRIDAY', 2, 4, 4, 3),
       ('OCTOBER', '2022-10-28', 'FRIDAY', 2, 3, 3, 4),
       ('OCTOBER', '2022-10-31', 'MONDAY', 2, 2, 2, 1),
       ('OCTOBER', '2022-10-31', 'MONDAY', 2, 5, 5, 2),
       ('OCTOBER', '2022-10-31', 'MONDAY', 2, 7, 7, 3),
       ('OCTOBER', '2022-10-31', 'MONDAY', 2, 10, 10, 4),
       ('OCTOBER', '2022-10-03', 'MONDAY', 3, 3, 3, 1),
       ('OCTOBER', '2022-10-03', 'MONDAY', 3, 9, 9, 2),
       ('OCTOBER', '2022-10-03', 'MONDAY', 3, 2, 2, 3),
       ('OCTOBER', '2022-10-03', 'MONDAY', 3, 8, 8, 4),
       ('OCTOBER', '2022-10-04', 'TUESDAY', 3, 6, 6, 1),
       ('OCTOBER', '2022-10-04', 'TUESDAY', 3, 10, 10, 2),
       ('OCTOBER', '2022-10-04', 'TUESDAY', 3, 7, 7, 3),
       ('OCTOBER', '2022-10-04', 'TUESDAY', 3, 4, 4, 4),
       ('OCTOBER', '2022-10-04', 'TUESDAY', 3, 2, 2, 5),
       ('OCTOBER', '2022-10-04', 'TUESDAY', 3, 8, 8, 6),
       ('OCTOBER', '2022-10-05', 'WEDNESDAY', 3, 1, 1, 1),
       ('OCTOBER', '2022-10-05', 'WEDNESDAY', 3, 2, 2, 2),
       ('OCTOBER', '2022-10-05', 'WEDNESDAY', 3, 6, 6, 3),
       ('OCTOBER', '2022-10-05', 'WEDNESDAY', 3, 7, 7, 4),
       ('OCTOBER', '2022-10-06', 'THURSDAY', 3, 2, 2, 1),
       ('OCTOBER', '2022-10-06', 'THURSDAY', 3, 3, 3, 2),
       ('OCTOBER', '2022-10-06', 'THURSDAY', 3, 6, 6, 3),
       ('OCTOBER', '2022-10-06', 'THURSDAY', 3, 5, 5, 4),
       ('OCTOBER', '2022-10-07', 'FRIDAY', 3, 10, 10, 1),
       ('OCTOBER', '2022-10-07', 'FRIDAY', 3, 5, 5, 2),
       ('OCTOBER', '2022-10-07', 'FRIDAY', 3, 8, 8, 3),
       ('OCTOBER', '2022-10-10', 'MONDAY', 3, 3, 3, 1),
       ('OCTOBER', '2022-10-10', 'MONDAY', 3, 9, 9, 2),
       ('OCTOBER', '2022-10-10', 'MONDAY', 3, 2, 2, 3),
       ('OCTOBER', '2022-10-10', 'MONDAY', 3, 8, 8, 4),
       ('OCTOBER', '2022-10-11', 'TUESDAY', 3, 6, 6, 1),
       ('OCTOBER', '2022-10-11', 'TUESDAY', 3, 10, 10, 2),
       ('OCTOBER', '2022-10-11', 'TUESDAY', 3, 7, 7, 3),
       ('OCTOBER', '2022-10-11', 'TUESDAY', 3, 4, 4, 4),
       ('OCTOBER', '2022-10-11', 'TUESDAY', 3, 2, 2, 5),
       ('OCTOBER', '2022-10-11', 'TUESDAY', 3, 8, 8, 6),
       ('OCTOBER', '2022-10-12', 'WEDNESDAY', 3, 1, 1, 1),
       ('OCTOBER', '2022-10-12', 'WEDNESDAY', 3, 2, 2, 2),
       ('OCTOBER', '2022-10-12', 'WEDNESDAY', 3, 6, 6, 3),
       ('OCTOBER', '2022-10-12', 'WEDNESDAY', 3, 7, 7, 4),
       ('OCTOBER', '2022-10-13', 'THURSDAY', 3, 2, 2, 1),
       ('OCTOBER', '2022-10-13', 'THURSDAY', 3, 3, 3, 2),
       ('OCTOBER', '2022-10-13', 'THURSDAY', 3, 6, 6, 3),
       ('OCTOBER', '2022-10-13', 'THURSDAY', 3, 5, 5, 4),
       ('OCTOBER', '2022-10-14', 'FRIDAY', 3, 10, 10, 1),
       ('OCTOBER', '2022-10-14', 'FRIDAY', 3, 5, 5, 2),
       ('OCTOBER', '2022-10-14', 'FRIDAY', 3, 8, 8, 3),
       ('OCTOBER', '2022-10-17', 'MONDAY', 3, 3, 3, 1),
       ('OCTOBER', '2022-10-17', 'MONDAY', 3, 9, 9, 2),
       ('OCTOBER', '2022-10-17', 'MONDAY', 3, 2, 2, 3),
       ('OCTOBER', '2022-10-17', 'MONDAY', 3, 8, 8, 4),
       ('OCTOBER', '2022-10-18', 'TUESDAY', 3, 6, 6, 1),
       ('OCTOBER', '2022-10-18', 'TUESDAY', 3, 10, 10, 2),
       ('OCTOBER', '2022-10-18', 'TUESDAY', 3, 7, 7, 3),
       ('OCTOBER', '2022-10-18', 'TUESDAY', 3, 4, 4, 4),
       ('OCTOBER', '2022-10-18', 'TUESDAY', 3, 2, 2, 5),
       ('OCTOBER', '2022-10-18', 'TUESDAY', 3, 8, 8, 6),
       ('OCTOBER', '2022-10-19', 'WEDNESDAY', 3, 1, 1, 1),
       ('OCTOBER', '2022-10-19', 'WEDNESDAY', 3, 2, 2, 2),
       ('OCTOBER', '2022-10-19', 'WEDNESDAY', 3, 6, 6, 3),
       ('OCTOBER', '2022-10-19', 'WEDNESDAY', 3, 7, 7, 4),
       ('OCTOBER', '2022-10-20', 'THURSDAY', 3, 2, 2, 1),
       ('OCTOBER', '2022-10-20', 'THURSDAY', 3, 3, 3, 2),
       ('OCTOBER', '2022-10-20', 'THURSDAY', 3, 6, 6, 3),
       ('OCTOBER', '2022-10-20', 'THURSDAY', 3, 5, 5, 4),
       ('OCTOBER', '2022-10-21', 'FRIDAY', 3, 10, 10, 1),
       ('OCTOBER', '2022-10-21', 'FRIDAY', 3, 5, 5, 2),
       ('OCTOBER', '2022-10-21', 'FRIDAY', 3, 8, 8, 3),
       ('OCTOBER', '2022-10-24', 'MONDAY', 3, 3, 3, 1),
       ('OCTOBER', '2022-10-24', 'MONDAY', 3, 9, 9, 2),
       ('OCTOBER', '2022-10-24', 'MONDAY', 3, 2, 2, 3),
       ('OCTOBER', '2022-10-24', 'MONDAY', 3, 8, 8, 4),
       ('OCTOBER', '2022-10-25', 'TUESDAY', 3, 6, 6, 1),
       ('OCTOBER', '2022-10-25', 'TUESDAY', 3, 10, 10, 2),
       ('OCTOBER', '2022-10-25', 'TUESDAY', 3, 7, 7, 3),
       ('OCTOBER', '2022-10-25', 'TUESDAY', 3, 4, 4, 4),
       ('OCTOBER', '2022-10-25', 'TUESDAY', 3, 2, 2, 5),
       ('OCTOBER', '2022-10-25', 'TUESDAY', 3, 8, 8, 6),
       ('OCTOBER', '2022-10-26', 'WEDNESDAY', 3, 1, 1, 1),
       ('OCTOBER', '2022-10-26', 'WEDNESDAY', 3, 2, 2, 2),
       ('OCTOBER', '2022-10-26', 'WEDNESDAY', 3, 6, 6, 3),
       ('OCTOBER', '2022-10-26', 'WEDNESDAY', 3, 7, 7, 4),
       ('OCTOBER', '2022-10-27', 'THURSDAY', 3, 2, 2, 1),
       ('OCTOBER', '2022-10-27', 'THURSDAY', 3, 3, 3, 2),
       ('OCTOBER', '2022-10-27', 'THURSDAY', 3, 6, 6, 3),
       ('OCTOBER', '2022-10-27', 'THURSDAY', 3, 5, 5, 4),
       ('OCTOBER', '2022-10-28', 'FRIDAY', 3, 10, 10, 1),
       ('OCTOBER', '2022-10-28', 'FRIDAY', 3, 5, 5, 2),
       ('OCTOBER', '2022-10-28', 'FRIDAY', 3, 8, 8, 3),
       ('OCTOBER', '2022-10-31', 'MONDAY', 3, 3, 3, 1),
       ('OCTOBER', '2022-10-31', 'MONDAY', 3, 9, 9, 2),
       ('OCTOBER', '2022-10-31', 'MONDAY', 3, 2, 2, 3),
       ('OCTOBER', '2022-10-31', 'MONDAY', 3, 8, 8, 4);
