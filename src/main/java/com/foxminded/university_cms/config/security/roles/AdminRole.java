package com.foxminded.university_cms.config.security.roles;

public class AdminRole {
    public static final String ADMIN_RESOURCES = "/admin/**";

    public static final String ADD_TIMETABLE = "/timetable/add";

    public static final String DELETE_TIMETABLE = "/timetable/delete";

    public static final String UPDATE_TIMETABLE = "/timetable/update";

    public static final String TIMETABLE_MANAGER = "/timetable/manager";

    public static final String SUBJECTS_MANAGER = "/subjects/manager";

    public static final String SUBJECTS_ADD = "/subjects/add";

    public static final String SUBJECTS_DELETE = "/subjects/delete";

    public static final String SUBJECTS_UPDATE = "/subjects/update";

    public static final String GROUPS_MANAGER = "groups/manager";

    public static final String GROUPS_ADD = "groups/add";

    public static final String GROUPS_DELETE = "/groups/delete";

    public static final String GROUPS_UPDATE = "/groups/update";

    public static final String GROUP_STUDENTS = "/groups/students";

    public static final String GROUPS_STUDENTS_DELETE = "/groups/students/delete";

    public static final String GROUPS_STUDENTS_ADD = "groups/students/add";
}
