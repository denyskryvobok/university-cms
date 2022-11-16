package com.foxminded.university_cms.entity;

import com.foxminded.university_cms.entity.security.User;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "teachers")
public class Teacher extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "position")
    private String position;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher", orphanRemoval = true)
    @ToString.Exclude
    private Set<Timetable> timetables = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    public Teacher(String firstName, String lastName, String street, String city, String zip, String country,
                   Long teacherId, String position) {
        super(firstName, lastName, street, city, zip, country);
        this.teacherId = teacherId;
        this.position = position;
    }

    public Teacher(String firstName, String lastName, String street, String city, String zip, String country,
                   String position) {
        this(firstName, lastName, street, city, zip, country, null, position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Teacher teacher = (Teacher) o;
        return teacherId != null && Objects.equals(teacherId, teacher.teacherId);
    }

    @Override
    public int hashCode() {
        return teacherId != null ? teacherId.hashCode() : 0;
    }
}
