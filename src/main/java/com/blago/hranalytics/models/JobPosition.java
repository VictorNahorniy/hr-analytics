package com.blago.hranalytics.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "job_position", schema = "hr_employeedb", catalog = "")
public class JobPosition {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "job_position_id", nullable = false)
    private Integer jobPositionId;
    @Basic
    @Column(name = "position_name", nullable = true, length = 50)
    private String positionName;
    @Basic
    @Column(name = "employment_level_id", nullable = true)
    private Integer employmentLevelId;
    @Basic
    @Column(name = "job_category_id", nullable = true)
    private Integer jobCategoryId;

    public Integer getJobPositionId() {
        return jobPositionId;
    }

    public void setJobPositionId(Integer jobPositionId) {
        this.jobPositionId = jobPositionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Integer getEmploymentLevelId() {
        return employmentLevelId;
    }

    public void setEmploymentLevelId(Integer employmentLevelId) {
        this.employmentLevelId = employmentLevelId;
    }

    public Integer getJobCategoryId() {
        return jobCategoryId;
    }

    public void setJobCategoryId(Integer jobCategoryId) {
        this.jobCategoryId = jobCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobPosition that = (JobPosition) o;
        return Objects.equals(jobPositionId, that.jobPositionId) && Objects.equals(positionName, that.positionName) && Objects.equals(employmentLevelId, that.employmentLevelId) && Objects.equals(jobCategoryId, that.jobCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobPositionId, positionName, employmentLevelId, jobCategoryId);
    }
}
