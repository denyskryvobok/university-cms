package com.foxminded.university_cms.service;

import com.foxminded.university_cms.entity.Subject;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

public interface StudentService {

    Map<LocalDate, List<Subject>> findTimetableForOneDay(Long studentId, LocalDate date);

    Map<LocalDate, List<Subject>> findTimetableForMonth(Long studentId, Month month);
}
