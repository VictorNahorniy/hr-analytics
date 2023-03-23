package com.blago.hranalytics.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "employee_department", schema = "hr_employeedb", catalog = "")
public class EmployeeDepartment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "employee_department_id", nullable = false)
    private Integer employeeDepartmentId;
    @Basic
    @Column(name = "department_name", nullable = true, length = 50)
    private String departmentName;

    public Integer getEmployeeDepartmentId() {
        return employeeDepartmentId;
    }

    public void setEmployeeDepartmentId(Integer employeeDepartmentId) {
        this.employeeDepartmentId = employeeDepartmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDepartment that = (EmployeeDepartment) o;
        return Objects.equals(employeeDepartmentId, that.employeeDepartmentId) && Objects.equals(departmentName, that.departmentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeDepartmentId, departmentName);
    }
}
