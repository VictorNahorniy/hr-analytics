package com.blago.hranalytics.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "hiring_type", schema = "hr_employeedb", catalog = "")
public class HiringType {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "type_id", nullable = false)
    private Integer typeId;
    @Basic
    @Column(name = "name_type", nullable = true, length = 15)
    private String nameType;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HiringType that = (HiringType) o;
        return Objects.equals(typeId, that.typeId) && Objects.equals(nameType, that.nameType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, nameType);
    }
}
