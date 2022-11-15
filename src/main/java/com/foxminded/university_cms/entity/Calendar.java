package com.foxminded.university_cms.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "calendar")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calendarId;

    @Column(name = "date_of_day")
    @NonNull
    private LocalDate dateOfDay;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "calendar", orphanRemoval = true)
    @ToString.Exclude
    private Set<Timetable> timetables = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Calendar calendar = (Calendar) o;
        return calendarId != null && Objects.equals(calendarId, calendar.calendarId);
    }

    @Override
    public int hashCode() {
        return calendarId != null ? calendarId.hashCode() : 0;
    }
}
