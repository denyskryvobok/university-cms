package com.foxminded.university_cms.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mappings {

    public static final String HOME_PAGE = "/";

    public static final String GROUPS_PAGE = "/groups";

    public static final String GROUP_MANAGER = "/manager";

    public static final String GROUP_ADD = "/add";

    public static final String GROUP_UPDATE = "/update";

    public static final String GROUP_DELETE = "/delete";

    public static final String GROUP_STUDENTS = "/students";

    public static final String GROUP_STUDENTS_ADD = "/students/add";

    public static final String GROUP_STUDENTS_DELETE = "/students/delete";

    public static final String PROFILE_PAGE = "/profile";

    public static final String USER_PROFILE = "/userProfile";

    public static final String STUDENTS_PAGE = "/students";

    public static final String SUBJECTS_PAGE = "/subjects";

    public static final String SUBJECTS_MANAGER = "/manager";

    public static final String SUBJECTS_ADD = "/add";

    public static final String SUBJECTS_DELETE = "/delete";

    public static final String SUBJECTS_UPDATE = "/update";

    public static final String TEACHER_PAGE = "/teachers";

    public static final String TEACHER_SUBJECTS = "/subjects";

    public static final String TIMETABLE_PAGE = "/timetable";

    public static final String TIMETABLE_MANAGER = "/manager";

    public static final String TIMETABLE_DELETE = "/delete";

    public static final String TIMETABLE_UPDATE = "/update";

    public static final String TIMETABLE_ADD = "/add";

    public static final String REGISTER_PAGE = "/register";

    public static final String STUDENT_FORM = "/studentForm";

    public static final String TEACHER_FORM = "/teacherForm";

    public static final String ADMIN_PAGE = "/admin";

    public static final String ADMIN_PROFILE = "/adminProfile";

    public static final String ADMIN_UPDATE_ROLES = "/updateRoles";

    public static final String STUDENT_REGISTRATION_FORM = "/studentReg";

    public static final String TEACHER_REGISTRATION_FORM = "/teacherReg";

    public static final String GROUP_TIMETABLE_FOR_MONTH = "/groupMonth";

    public static final String GROUP_TIMETABLE_FOR_DATE = "/groupDate";

    public static final String TEACHER_TIMETABLE_FOR_MONTH = "/teacherMonth";

    public static final String TEACHER_TIMETABLE_FOR_DATE = "/teacherDate";
}
