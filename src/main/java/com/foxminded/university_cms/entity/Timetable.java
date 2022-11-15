package com.foxminded.university_cms.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "timetables")
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timetable_id")
    private Long timetableId;

    @Column(name = "subject_order")
    private Integer subjectOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    @ToString.Exclude
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id")
    @ToString.Exclude
    private Calendar calendar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    @ToString.Exclude
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    @ToString.Exclude
    private Subject subject;

    public Timetable(Long timetableId, Integer subjectOrder) {
        this.timetableId = timetableId;
        this.subjectOrder = subjectOrder;
    }

    public Timetable(Integer subjectOrder, Group group, Calendar calendar, Teacher teacher, Subject subject) {
        this.subjectOrder = subjectOrder;
        this.group = group;
        this.calendar = calendar;
        this.teacher = teacher;
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Timetable timetable = (Timetable) o;
        return timetableId != null && Objects.equals(timetableId, timetable.timetableId);
    }

    @Override
    public int hashCode() {
        return timetableId != null ? timetableId.hashCode() : 0;
    }
}
