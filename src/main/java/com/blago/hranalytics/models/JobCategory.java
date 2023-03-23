package com.blago.hranalytics.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "job_category", schema = "hr_employeedb", catalog = "")
public class JobCategory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "job_category_id", nullable = false)
    private Integer jobCategoryId;
    @Basic
    @Column(name = "category_name", nullable = true, length = 20)
    private String categoryName;

    public Integer getJobCategoryId() {
        return jobCategoryId;
    }

    public void setJobCategoryId(Integer jobCategoryId) {
        this.jobCategoryId = jobCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobCategory that = (JobCategory) o;
        return Objects.equals(jobCategoryId, that.jobCategoryId) && Objects.equals(categoryName, that.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobCategoryId, categoryName);
    }
}
