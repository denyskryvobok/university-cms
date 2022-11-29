package com.foxminded.university_cms.service;

import com.foxminded.university_cms.dao.CalendarDAO;
import com.foxminded.university_cms.dao.GroupDAO;
import com.foxminded.university_cms.dao.SubjectDAO;
import com.foxminded.university_cms.dao.TeacherDAO;
import com.foxminded.university_cms.dao.TimetableDAO;
import com.foxminded.university_cms.entity.Calendar;
import com.foxminded.university_cms.entity.Group;
import com.foxminded.university_cms.entity.Subject;
import com.foxminded.university_cms.entity.Teacher;
import com.foxminded.university_cms.entity.Timetable;
import com.foxminded.university_cms.dto.TimetableDTO;
import com.foxminded.university_cms.exception.CalendarNotFoundException;
import com.foxminded.university_cms.exception.GroupNotFoundException;
import com.foxminded.university_cms.exception.SubjectNotFoundException;
import com.foxminded.university_cms.exception.TeacherNotFoundException;
import com.foxminded.university_cms.exception.TimetableNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.lang.String.format;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@Transactional
public class TimetableServiceImp implements TimetableService {
    @Autowired
    private TimetableDAO timetableDAO;

    @Autowired
    private GroupDAO groupDAO;
    @Autowired
    private CalendarDAO calendarDAO;
    @Autowired
    private SubjectDAO subjectDAO;
    @Autowired
    private TeacherDAO teacherDAO;

    @Override
    public List<Timetable> getGroupTimetableForOneDay(Long groupId, LocalDate date) {
        log.info("GetGroupScheduleForOneDay start with groupId:{}, date:{}", groupId, date);
        Set<Timetable> timetableForOneDay = timetableDAO.findGroupTimetableForOneDay(groupId, date);
        return timetableForOneDay.stream()
                .sorted(comparing(Timetable::getSubjectOrder))
                .collect(toList());
    }

    @Override
    public Map<LocalDate, List<Timetable>> getGroupTimetableForMonth(Long groupId, YearMonth yearMonth) {
        log.info("GetGroupScheduleForMonth start with groupId:{}, month:{}", groupId, yearMonth);
        Set<Timetable> timetableForMonth =
                timetableDAO.findGroupTimetableForMonth(groupId, yearMonth.getMonthValue(), yearMonth.getYear());
        return getTimetablesByDate(timetableForMonth);
    }

    @Override
    public List<Timetable> getTeacherTimetableForOneDay(Long teacherId, LocalDate date) {
        log.info("GetTeacherScheduleForOneDay start with groupId:{}, date:{}", teacherId, date);
        Set<Timetable> timetableForOneDay = timetableDAO.findTeacherTimetableForOneDay(teacherId, date);
        return timetableForOneDay.stream()
                .sorted(comparing(Timetable::getSubjectOrder))
                .collect(toList());
    }

    @Override
    public Map<LocalDate, List<Timetable>> getTeacherTimetableForMonth(Long teacherId, YearMonth yearMonth) {
        log.info("GetTeacherScheduleForMonth start with groupId:{}, month:{}", teacherId, yearMonth);
        Set<Timetable> timetableForMonth =
                timetableDAO.findTeacherTimetableForMonth(teacherId, yearMonth.getMonthValue(), yearMonth.getYear());
        return getTimetablesByDate(timetableForMonth);
    }

    @Override
    public void deleteTimetable(Long timetableId) {
        log.info("DeleteTimetable start with timetableId:{}", timetableId);
        Optional<Timetable> optionalTimetable = timetableDAO.findTimetable(timetableId);
        if (optionalTimetable.isEmpty()) {
            log.error("Timetable with id {} not found", timetableId);
            throw new TimetableNotFoundException(format("Timetable with id '%d' not found", timetableId));
        }
        Timetable timetable = optionalTimetable.get();
        timetableDAO.delete(timetable);
    }

    @Override
    public boolean updateTimetable(TimetableDTO timetableDTO) {
        log.info("UpdateTimetable start with timetableDTO:{}", timetableDTO);
        Optional<Timetable> optionalTimetable = timetableDAO.findTimetable(timetableDTO.getTimetableId());
        if (optionalTimetable.isEmpty()) {
            log.error("Timetable with id {} not found", timetableDTO.getTimetableId());
            throw new TimetableNotFoundException(format("Timetable with id '%d' not found", timetableDTO.getTimetableId()));
        }
        Timetable timetable = optionalTimetable.get();

        boolean checkUpdate = false;

        if (!timetable.getSubjectOrder().equals(timetableDTO.getSubjectOrder())) {
            timetable.setSubjectOrder(timetableDTO.getSubjectOrder());
            checkUpdate = true;
        }

        if (!timetable.getSubject().getSubjectId().equals(timetableDTO.getSubjectId())) {
            Subject subject = subjectDAO.findById(timetableDTO.getSubjectId())
                    .orElseThrow(SubjectNotFoundException::new);
            timetable.setSubject(subject);
            checkUpdate = true;
        }

        if (!timetable.getTeacher().getTeacherId().equals(timetableDTO.getTeacherId())) {
            Teacher teacher = teacherDAO.findById(timetableDTO.getTeacherId()).orElseThrow(TeacherNotFoundException::new);
            timetable.setTeacher(teacher);
            checkUpdate = true;
        }
        return checkUpdate;
    }

    @Override
    public void addTimetable(TimetableDTO timetableDTO) {
        log.info("AddTimetable start with timetableDTO:{}", timetableDTO);
        Teacher teacher = teacherDAO.findById(timetableDTO.getTeacherId()).orElseThrow(TeacherNotFoundException::new);
        Subject subject = subjectDAO.findById(timetableDTO.getSubjectId()).orElseThrow(SubjectNotFoundException::new);
        Calendar calendar = calendarDAO.findById(timetableDTO.getCalendarId()).orElseThrow(CalendarNotFoundException::new);
        Group group = groupDAO.findById(timetableDTO.getGroupId()).orElseThrow(GroupNotFoundException::new);
        Timetable timetable = new Timetable(timetableDTO.getSubjectOrder(), group, calendar, teacher, subject);
        timetableDAO.save(timetable);
    }

    private Map<LocalDate, List<Timetable>> getTimetablesByDate(Set<Timetable> timetables) {
        log.info("GetTimetablesByDate start with timetables:{}", timetables);
        return timetables.stream()
                .sorted(comparing(timetable -> timetable.getCalendar().getDateOfDay()))
                .collect(groupingBy(o -> o.getCalendar().getDateOfDay(), LinkedHashMap::new,
                        collectingAndThen(toList(),
                                groupTimetables -> groupTimetables.stream()
                                        .sorted(comparing(Timetable::getSubjectOrder))
                                        .collect(toList()))));
    }
}
