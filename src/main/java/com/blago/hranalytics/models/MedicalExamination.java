package com.blago.hranalytics.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "medical_examination", schema = "hr_employeedb", catalog = "")
public class MedicalExamination {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "medical_id")
    private Integer medicalId;
    @Basic
    @Column(name = "expiration")
    private Date expiration;
    @Basic
    @Column(name = "employee_id")
    private Integer employeeId;

    public Integer getMedicalId() {
        return medicalId;
    }

    public void setMedicalId(Integer medicalId) {
        this.medicalId = medicalId;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
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
        MedicalExamination that = (MedicalExamination) o;
        return Objects.equals(medicalId, that.medicalId) && Objects.equals(expiration, that.expiration) && Objects.equals(employeeId, that.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicalId, expiration, employeeId);
    }
}
