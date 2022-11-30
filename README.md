# University-CMS

## University Schedule web application

### UML diagram: 
![UML diagram](University_App_UML.PNG)

## Development process 

### 1) Decompose university 
   At this stage, analysis and decomposition for the university were carried out. 
   The main entities for this project and the mapping strategies between them have been defined. 
   UML diagram was also created.


### 2) Bootstrap project
At this stage, A Spring Boot project with the required dependencies 
(Spring Web, Spring Data JPA, Thymeleaf, Flyway Migration, PostgreSQL Driver) was created. After that, A docker-compose 
file for the Postgres DB was created. Then an SQL migration script according to the UML was created.  Also, JPA repositories 
for each entity was created, which were defined before, and created a service for the Timetable entity where I made methods to get 
the schedule for a student for one day and month and the same for a teacher. 
A controller for Timetable with four endpoints for each method of Timetable service was created.
After that, I created an HTML home page(using Thymeleaf) and a page for getting schedules for teachers and students.


### 3) Create basic UI
At this stage, Bootstrap for the HTML pages was added.


### 4) Adding Security
At this stage, Spring Security was added. I added roles for users: ADMIN, STUDENT, and TEACHER.

#### Sequences of the task execution:
- A User entity was created.  A mapping strategy between the User, Student, and Teacher was added. 
- A Role entity was added with a mapping strategy between the User and the Role. 
- A realization for UserDetailsService was created, and added PasswordEncoder realization. 
- A user service was created in which methods were added to add a user as a student or teacher. methods were also created to get all users or a user by username.
- An authorization filter was added.
- A form login page was added where the user can authenticate by using his credentials or sign up as a Teacher or as a Student. 
- Profiles for Student, Teacher, and Admin were added.
- In the admin profile, the ability to list all users and change their roles was added. 
In the profile for students and teachers, you can get a schedule for a month and one day.

#### Try functionality
To try to log in, you can go to the home page and press the right button 'Login' on the navigation bar. 
After clicking this button, you will be taken to the login page where you can log in 
using your credentials or register as a teacher or student by clicking "Sign up". After your authorization, 
you will be redirected to your profile page where you can view your schedule for a month or a day. 
If you have the admin role, you will be redirected to the admin page, where you can see all users and select a role for them.
To log out you can click the right button 'Log out' on the navigation bar.


### 5) Implement Subjects view + edit feature
At this stage, the possibilities for creating, reading, updating, and deleting subjects were added.

#### Sequences of the task execution:
- A Subject service was created where methods for CRUD operations were added. 
- A Subject controller was created with the appropriate endpoints for reading, updating, creating, and deleting subjects.

#### Try functionality
To view all the subjects, you can click on the "Subjects" button on the navigation bar, after which 
you will be redirected to the page with all the subjects. If you have the admin role, 
you can click on the "Manage Subjects" button in the admin profile, 
after which you will be redirected to the subject manager page, where you will see a table with all the subjects. 
You can add a new subject by entering its name in the form that is located at the top of the table, 
you can also delete the subject by clicking on the delete button, 
or you can change the name


### 6) Implement Groups view + edit feature
At this stage, the possibilities for creating, reading, updating, and deleting groups were added.

#### Sequences of the task execution:
- A Group service was created where methods for CRUD operations were added.
- A Group controller was created with the appropriate endpoints for reading, updating, creating, and deleting groups.

#### Try functionality
To see all the groups, you can go to the group page by clicking on the "Groups" button on the navigation bar. 
Also, on this page, you can view the schedule for a particular group.
If you have the admin role, then in the admin profile, you can click on the "Manage Groups" button,
after which you will be redirected to the manager's group page, where you will see a list of all groups. 
At the top of this table, you can add a new group, by entering its name in the form and clicking the "Add Group" button. 
You can also delete a group by clicking delete or changing the name of the group and clicking the update button. 
When deleting, the schedule for this group will also be deleted.


### 7) Implement Students view + edit feature
At this stage, the ability to assign and reassign students was added.

#### Sequences of the task execution:
- Methods for deleting and adding a student were added to the group service. 
- Endpoints were added to the group controller by adding and deleting students, and an endpoint was added to get all the students of the selected group.

#### Try functionality
To see all students - you can click on the "Students" button on the navigation bar. If you have the admin role, 
you can click on the "Manage Groups" button on the admin page. On the group manager page, 
you will see a list of all groups, where each group has a button "Select students for a group" by clicking on it, 
you will be redirected to a page where all students from this group will be shown. 
On this page, you can remove a student from a group by clicking the divide button or adding a student to a group 
from the list above the table.


### 8) Implement Teachers view + edit features
At this stage, the ability for the teacher to see his subjects in his profile was added.

#### Sequences of the task execution:
- A method was added to the subject service to get the teacher's subjects. 
- A corresponding endpoint has was added to the teacher controller

#### Try functionality
To get the teacher's subjects, you need to go to the teacher's profile and click on the "Get Subjects" button, 
after which you will be redirected to the page with the subjects.


### 9) Implement Schedule view + edit features
At this stage, functionality was added by creating, reading, updating, and deleting schedules.

#### Sequences of the task execution:
- The Calendar service was added in which a method was made for obtaining dates with a schedule for a month for a certain group. 
- CRUD operations were added to the Timetable service. 
- The corresponding endpoints were added to the Timetable controller

#### Try functionality
To use this functionality, you must have an admin role. In the admin profile, 
you need to click on the "Manage Subjects" button, after which you will be redirected to the subject manager page. 
Above the table with subjects, you can select functionality with the addition of a schedule depending on the group and month. 
After selecting the group and the month for which the schedule will be made, you will be taken to the timetable manager page. 
This page will show the schedule for each day of the month. You can delete, update, and add, schedules for each day. 
To add a new subject to the schedule for a certain day, you will need to select its order, the name of the subject,
and the teacher itself. To update a certain subject in the schedule you need to change its order or name or teacher and press update. 
You can also remove a specific item from the schedule by clicking the delete button.


## User Capabilities
Unauthenticated user:
- User can log in
- User can register as teacher or student
- User can see all subjects

User is logged on as Admin:

- User can see all students, teachers, groups and subjects by clicking the corresponding button on the navigation bar
- User can go to the profile by clicking the 'My profile'
- User can list all registered users on user admin page
- User can set required role for each registered user
- User can create/read/update/delete subjects
- User can create/read/update/delete group information
- User can assign/ reassign Students to Group
- User can create/read/update/delete timetables for groups and teachers
- User can log out by clicking the 'Log out' button

User is logged on as Teacher:

- User can see all students, teachers, groups and subjects by clicking the corresponding button on the navigation bar 
- User can go to the profile by clicking the 'My profile'
- User can see own schedule according to selected date/range filter
- User can see all his courses
- User can log out by clicking the 'Log out' button

User is logged on as Student:

- User can see all students, teachers, groups and subjects by clicking the corresponding button on the navigation bar
- User can go to the profile by clicking the 'My profile'
- User can see own schedule according to selected date/range filter
- User can log out by clicking the 'Log out' button



## Technologies
Project is created with:

* Java 11
* Spring MVC
* Spring JPA
* Spring Security
* JUnit 5
* Mockito
* Testcontainers
* Postgres
* Flyway
* Docker
* Thymeleaf
* Lombok
