package com.blago.hranalytics.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "building_object", schema = "hr_employeedb", catalog = "")
public class BuildingObject {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "objectId", nullable = false)
    private Integer objectId;
    @Basic
    @Column(name = "objectName", nullable = true, length = 30)
    private String objectName;

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuildingObject that = (BuildingObject) o;
        return Objects.equals(objectId, that.objectId) && Objects.equals(objectName, that.objectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectId, objectName);
    }
}
