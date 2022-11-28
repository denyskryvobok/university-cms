package com.foxminded.university_cms.service;

import com.foxminded.university_cms.dto.TimetableDTO;
import com.foxminded.university_cms.entity.Timetable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TimetableServiceTest extends TestcontainersInitializer {
    @Autowired
    private TimetableService timetableService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void getGroupTimetableForOneDay_shouldReturnListOfTimetable_whenThereIsTimetableForInputDate() {
        List<Timetable> actual = timetableService.getGroupTimetableForOneDay(1L, LocalDate.parse("2022-10-03"));

        List<Timetable> expected = getTimetablesForGroupForOneDay();

        assertEquals(expected, actual);
    }

    @Test
    void getGroupTimetableForOneDay_shouldReturnEmptyList_whenThereIsNotTimetableForInputDate() {
        List<Timetable> actual = timetableService.getGroupTimetableForOneDay(1L, LocalDate.parse("2022-09-03"));

        List<Timetable> expected = Collections.emptyList();

        assertEquals(expected, actual);
    }

    @Test
    void getGroupTimetableForMonth_shouldReturnMapDateToListOfSubject_whenThereIsTimetableForInputMonth() {
        Map<LocalDate, List<Timetable>> actual = timetableService.getGroupTimetableForMonth(1L, "2022-10");

        Map<LocalDate, List<Timetable>> expected = getGroupDateToTimetableForMonth();

        assertEquals(expected, actual);
    }

    @Test
    void getGroupTimetableForMonth_shouldReturnEmptyMap_whenThereIsNotTimetableForInputMonth() {
        Map<LocalDate, List<Timetable>> actual = timetableService.getGroupTimetableForMonth(1L, "2022-09");

        Map<LocalDate, List<Timetable>> expected = Collections.emptyMap();

        assertEquals(expected, actual);
    }

    @Test
    void getTeacherTimetableForOneDay_shouldReturnListOfTimetables_whenThereIsTimetableForInputDate() {
        List<Timetable> actual = timetableService.getTeacherTimetableForOneDay(1L, LocalDate.parse("2022-10-03"));

        List<Timetable> expected = getTeacherTimetableForOneDay();

        assertEquals(expected, actual);

    }

    @Test
    void getTeacherTimetableForOneDay_shouldReturnEmptyList_whenThereIsNotTimetableForInputDate() {
        List<Timetable> actual = timetableService.getTeacherTimetableForOneDay(1L, LocalDate.parse("2022-09-03"));

        List<Timetable> expected = Collections.emptyList();

        assertEquals(expected, actual);
    }

    @Test
    void getTeacherTimetableForMonth_shouldReturnMapDateToListOfTimetable_whenThereIsTimetableForInputDate() {
        Map<LocalDate, List<Timetable>> actual = timetableService.getTeacherTimetableForMonth(1L, "2022-10");

        Map<LocalDate, List<Timetable>> expected = getTeacherDateToTimetableMapForMonth();

        assertEquals(expected, actual);
    }

    @Test
    void getTeacherTimetableForMonth_shouldReturnEmptyMap_whenThereIsNotTimetableForInputMonth() {
        Map<LocalDate, List<Timetable>> actual = timetableService.getTeacherTimetableForMonth(1L, "2022-09");

        Map<LocalDate, List<Timetable>> expected = Collections.emptyMap();

        assertEquals(expected, actual);
    }

    @Test
    void updateTimetable_shouldUpdateTimetableAndReturnTrue_whenDataInDtoAndInTimetableAreDifferent() {
        TimetableDTO timetableDTO = new TimetableDTO();
        timetableDTO.setTimetableId(1L);
        timetableDTO.setSubjectOrder(7);
        timetableDTO.setSubjectId(2L);
        timetableDTO.setTeacherId(2L);

        boolean result = timetableService.updateTimetable(timetableDTO);
        Timetable timetable = entityManager.find(Timetable.class, 1L);

        assertEquals(timetable.getSubjectOrder(), timetableDTO.getSubjectOrder());
        assertTrue(result);
    }

    @Test
    void updateTimetable_shouldNotUpdateTimetableAndReturnFalse_whenDtoHaveSameData() {
        TimetableDTO timetableDTO = new TimetableDTO();
        timetableDTO.setTimetableId(1L);
        timetableDTO.setSubjectOrder(1);
        timetableDTO.setSubjectId(1L);
        timetableDTO.setTeacherId(1L);

        boolean result = timetableService.updateTimetable(timetableDTO);

        assertFalse(result);
    }

    @Test
    void addTimetable_shouldAddNewTimetableInDB() {
        TimetableDTO timetableDTO = new TimetableDTO();
        timetableDTO.setSubjectOrder(3);
        timetableDTO.setSubjectId(1L);
        timetableDTO.setTeacherId(2L);
        timetableDTO.setGroupId(1L);
        timetableDTO.setCalendarId(1L);

        timetableService.addTimetable(timetableDTO);
        Timetable result = entityManager.find(Timetable.class, 12L);

        assertAll(
                () -> assertEquals(result.getTeacher().getTeacherId(), timetableDTO.getTeacherId()),
                () -> assertEquals(result.getSubject().getSubjectId(), timetableDTO.getSubjectId()),
                () -> assertEquals(result.getGroup().getGroupId(), timetableDTO.getGroupId()),
                () -> assertEquals(result.getCalendar().getCalendarId(), timetableDTO.getCalendarId())
        );

    }

    private List<Timetable> getTimetablesForGroupForOneDay() {
        Timetable first = new Timetable(1L, 1);
        Timetable second = new Timetable(2L, 2);
        Timetable third = new Timetable(3L, 3);
        Timetable fourth = new Timetable(4L, 4);
        Timetable fifth = new Timetable(5L, 5);

        return List.of(first, second, third, fourth, fifth);
    }

    private Map<LocalDate, List<Timetable>> getGroupDateToTimetableForMonth() {
        Timetable t1 = new Timetable(1L, 1);

        Timetable t2 = new Timetable(2L, 2);

        Timetable t3 = new Timetable(3L, 3);

        Timetable t4 = new Timetable(4L, 3);

        Timetable t5 = new Timetable(5L, 4);

        Timetable t6 = new Timetable(6L, 1);

        Timetable t7 = new Timetable(7L, 2);

        Timetable t8 = new Timetable(8L, 3);

        Timetable t9 = new Timetable(9L, 4);

        Timetable t10 = new Timetable(10L, 5);

        Timetable t11 = new Timetable(11L, 6);

        Map<LocalDate, List<Timetable>> dateToTimetable = new LinkedHashMap<>();

        dateToTimetable.put(LocalDate.parse("2022-10-03"), List.of(t1, t2, t3, t4, t5));
        dateToTimetable.put(LocalDate.parse("2022-10-04"), List.of(t6, t7, t8, t9, t10, t11));

        return dateToTimetable;
    }

    private List<Timetable> getTeacherTimetableForOneDay() {
        Timetable firstTimetable = new Timetable(1L, 1);
        Timetable secondTimetable = new Timetable(3L, 3);

        return List.of(firstTimetable, secondTimetable);
    }

    private Map<LocalDate, List<Timetable>> getTeacherDateToTimetableMapForMonth() {
        Timetable firstTimetableMonday = new Timetable(1L, 1);
        Timetable thirdTimetableMonday = new Timetable(3L, 3);
        Timetable firstTimetableTuesday = new Timetable(10L, 5);

        Map<LocalDate, List<Timetable>> expected = new LinkedHashMap<>();
        expected.put(LocalDate.parse("2022-10-03"), List.of(firstTimetableMonday, thirdTimetableMonday));
        expected.put(LocalDate.parse("2022-10-04"), List.of(firstTimetableTuesday));
        return expected;
    }
}
