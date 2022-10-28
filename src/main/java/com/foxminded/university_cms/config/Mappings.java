package com.foxminded.university_cms.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mappings {

    public static final String MENU_PAGE = "/";

    public static final String GROUPS_PAGE = "/groups";

    public static final String STUDENTS_PAGE = "/students";

    public static final String SUBJECTS_PAGE = "/subjects";

    public static final String TEACHER_PAGE = "/teachers";

    public static final String TIMETABLE_PAGE = "/timetable";

    public static final String GROUP_TIMETABLE_FOR_MONTH = "/groupMonth";

    public static final String GROUP_TIMETABLE_FOR_DATE = "/groupDate";

    public static final String TEACHER_TIMETABLE_FOR_MONTH = "/teacherMonth";

    public static final String TEACHER_TIMETABLE_FOR_DATE = "/teacherDate";
}
