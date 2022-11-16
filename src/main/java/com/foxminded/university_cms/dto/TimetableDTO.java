package com.foxminded.university_cms.dto;

import lombok.Data;

@Data
public class TimetableDTO {

    private Long timetableId;

    private Long subjectId;

    private Integer subjectOrder;

    private Long teacherId;

    private Long groupId;

    private Long calendarId;
}
