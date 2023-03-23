package com.blago.hranalytics.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "employee_id")
    private Integer employeeId;
    @Basic
    @Column(name = "patronymic")
    private String patronymic;
    @Basic
    @Column(name = "birthday")
    private Date birthday;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "id_num_of_taxpayer")
    private String idNumOfTaxpayer;
    @Basic
    @Column(name = "is_male")
    private Byte isMale;
    @Basic
    @Column(name = "last_name")
    private String lastName;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getIdNumOfTaxpayer() {
        return idNumOfTaxpayer;
    }

    public void setIdNumOfTaxpayer(String idNumOfTaxpayer) {
        this.idNumOfTaxpayer = idNumOfTaxpayer;
    }

    public Byte getIsMale() {
        return isMale;
    }

    public void setIsMale(Byte isMale) {
        this.isMale = isMale;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeId, employee.employeeId) && Objects.equals(patronymic, employee.patronymic) && Objects.equals(birthday, employee.birthday) && Objects.equals(firstName, employee.firstName) && Objects.equals(idNumOfTaxpayer, employee.idNumOfTaxpayer) && Objects.equals(isMale, employee.isMale) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, patronymic, birthday, firstName, idNumOfTaxpayer, isMale, lastName);
    }
}
