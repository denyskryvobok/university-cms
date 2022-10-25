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
@Table(name = "students")
public class Student extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "student_card")
    private Integer studentCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    @ToString.Exclude
    private Group group;

    public Student(String firstName, String lastName, String street, String city, String zip, String country,
                   Long studentId, Integer studentCard) {
        super(firstName, lastName, street, city, zip, country);
        this.studentId = studentId;
        this.studentCard = studentCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Student student = (Student) o;
        return studentId != null && Objects.equals(studentId, student.studentId);
    }

    @Override
    public int hashCode() {
        return studentId != null ? studentId.hashCode() : 0;
    }
}
