package com.blago.hranalytics.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
public class Firing {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "firing_id", nullable = false)
    private Integer firingId;
    @Basic
    @Column(name = "firing_date", nullable = true)
    private Date firingDate;
    @Basic
    @Column(name = "reason", nullable = true, length = 100)
    private String reason;
    @Basic
    @Column(name = "firing_type_id", nullable = true)
    private Integer firingTypeId;
    @Basic
    @Column(name = "employee_id", nullable = true)
    private Integer employeeId;

    public Integer getFiringId() {
        return firingId;
    }

    public void setFiringId(Integer firingId) {
        this.firingId = firingId;
    }

    public Date getFiringDate() {
        return firingDate;
    }

    public void setFiringDate(Date firingDate) {
        this.firingDate = firingDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getFiringTypeId() {
        return firingTypeId;
    }

    public void setFiringTypeId(Integer firingTypeId) {
        this.firingTypeId = firingTypeId;
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
        Firing firing = (Firing) o;
        return Objects.equals(firingId, firing.firingId) && Objects.equals(firingDate, firing.firingDate) && Objects.equals(reason, firing.reason) && Objects.equals(firingTypeId, firing.firingTypeId) && Objects.equals(employeeId, firing.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firingId, firingDate, reason, firingTypeId, employeeId);
    }
}
