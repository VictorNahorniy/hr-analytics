package com.blago.hranalytics.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "employment_level", schema = "hr_employeedb", catalog = "")
public class EmploymentLevel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "employment_level_id", nullable = false)
    private Integer employmentLevelId;
    @Basic
    @Column(name = "level_name", nullable = true, length = 20)
    private String levelName;

    public Integer getEmploymentLevelId() {
        return employmentLevelId;
    }

    public void setEmploymentLevelId(Integer employmentLevelId) {
        this.employmentLevelId = employmentLevelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmploymentLevel that = (EmploymentLevel) o;
        return Objects.equals(employmentLevelId, that.employmentLevelId) && Objects.equals(levelName, that.levelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employmentLevelId, levelName);
    }
}
