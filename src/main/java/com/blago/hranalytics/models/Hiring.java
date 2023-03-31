package com.blago.hranalytics.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
public class Hiring {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "hiring_id", nullable = false)
    private Integer hiringId;
    @Basic
    @Column(name = "hiring_date", nullable = true)
    private Date hiringDate;
    @Basic
    @Column(name = "wage", nullable = true, precision = 0)
    private Double wage;
    @Basic
    @Column(name = "PBI", nullable = true, precision = 0)
    private Double pbi;
    @Basic
    @Column(name = "employee_id", nullable = true)
    private Integer employeeId;
    @Basic
    @Column(name = "job_position_id", nullable = true)
    private Integer jobPositionId;
    @Basic
    @Column(name = "employee_department_id", nullable = true)
    private Integer employeeDepartmentId;
    @Basic
    @Column(name = "object_id", nullable = true)
    private Integer objectId;
    @Basic
    @Column(name = "law_firm_id", nullable = true)
    private Integer lawFirmId;
    @Basic
    @Column(name = "employment_type_id", nullable = true)
    private Integer employmentTypeId;
    @Basic
    @Column(name = "hiring_type_id", nullable = true)
    private Integer hiringTypeId;

    public Integer getHiringId() {
        return hiringId;
    }

    public void setHiringId(Integer hiringId) {
        this.hiringId = hiringId;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
    }

    public Double getWage() {
        return wage;
    }

    public void setWage(Double wage) {
        this.wage = wage;
    }

    public Double getPbi() {
        return pbi;
    }

    public void setPbi(Double pbi) {
        this.pbi = pbi;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getJobPositionId() {
        return jobPositionId;
    }

    public void setJobPositionId(Integer jobPositionId) {
        this.jobPositionId = jobPositionId;
    }

    public Integer getEmployeeDepartmentId() {
        return employeeDepartmentId;
    }

    public void setEmployeeDepartmentId(Integer employeeDepartmentId) {
        this.employeeDepartmentId = employeeDepartmentId;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getLawFirmId() {
        return lawFirmId;
    }

    public void setLawFirmId(Integer lawFirmId) {
        this.lawFirmId = lawFirmId;
    }

    public Integer getEmploymentTypeId() {
        return employmentTypeId;
    }

    public void setEmploymentTypeId(Integer employmentTypeId) {
        this.employmentTypeId = employmentTypeId;
    }

    public Integer getHiringTypeId() {
        return hiringTypeId;
    }

    public void setHiringTypeId(Integer hiringTypeId) {
        this.hiringTypeId = hiringTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hiring hiring = (Hiring) o;
        return Objects.equals(hiringId, hiring.hiringId) && Objects.equals(hiringDate, hiring.hiringDate) && Objects.equals(wage, hiring.wage) && Objects.equals(pbi, hiring.pbi) && Objects.equals(employeeId, hiring.employeeId) && Objects.equals(jobPositionId, hiring.jobPositionId) && Objects.equals(employeeDepartmentId, hiring.employeeDepartmentId) && Objects.equals(objectId, hiring.objectId) && Objects.equals(lawFirmId, hiring.lawFirmId) && Objects.equals(employmentTypeId, hiring.employmentTypeId) && Objects.equals(hiringTypeId, hiring.hiringTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hiringId, hiringDate, wage, pbi, employeeId, jobPositionId, employeeDepartmentId, objectId, lawFirmId, employmentTypeId, hiringTypeId);
    }

    @Override
    public String toString() {
        return "Hiring{" +
                "hiringId=" + hiringId +
                ", hiringDate=" + hiringDate +
                ", wage=" + wage +
                ", pbi=" + pbi +
                ", employeeId=" + employeeId +
                ", jobPositionId=" + jobPositionId +
                ", employeeDepartmentId=" + employeeDepartmentId +
                ", objectId=" + objectId +
                ", lawFirmId=" + lawFirmId +
                ", employmentTypeId=" + employmentTypeId +
                ", hiringTypeId=" + hiringTypeId +
                '}';
    }
}
