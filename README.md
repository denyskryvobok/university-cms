# university-cms

University Schedule web application

UML diagram: 
![UML diagram](University_App_UML.PNG)


## General info
Unauthenticated user:
- User can log in by clicking on the 'Log in' button and enter credentials
- User can register as teacher or student

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
