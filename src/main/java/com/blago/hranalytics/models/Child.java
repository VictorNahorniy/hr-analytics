package com.blago.hranalytics.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Child {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "child_id")
    private Integer childId;
    @Basic
    @Column(name = "birthday")
    private Timestamp birthday;
    @Basic
    @Column(name = "employee_id")
    private Integer employeeId;

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Child child = (Child) o;
        return Objects.equals(childId, child.childId) && Objects.equals(birthday, child.birthday) && Objects.equals(employeeId, child.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(childId, birthday, employeeId);
    }
}
