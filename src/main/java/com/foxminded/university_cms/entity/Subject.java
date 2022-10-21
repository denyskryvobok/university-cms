package com.foxminded.university_cms.entity;

import lombok.Getter;
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
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "subject_name")
    private String subjectName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subject", orphanRemoval = true)
    @ToString.Exclude
    private Set<Timetable> timetables = new HashSet<>();

    public Subject(Long subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Subject subject = (Subject) o;
        return subjectId != null && Objects.equals(subjectId, subject.subjectId);
    }

    @Override
    public int hashCode() {
        return subjectId != null ? subjectId.hashCode() : 0;
    }
}
