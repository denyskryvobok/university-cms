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
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "group_name")
    private String groupName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group", orphanRemoval = true)
    @ToString.Exclude
    private Set<Student> students = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group", orphanRemoval = true)
    @ToString.Exclude
    private Set<Timetable> timetables = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Group group = (Group) o;
        return groupId != null && Objects.equals(groupId, group.groupId);
    }

    @Override
    public int hashCode() {
        return groupId != null ? groupId.hashCode() : 0;
    }
}
