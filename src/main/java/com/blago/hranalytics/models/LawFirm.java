package com.blago.hranalytics.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "law_firm", schema = "hr_employeedb", catalog = "")
public class LawFirm {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "law_firm_id", nullable = false)
    private Integer lawFirmId;
    @Basic
    @Column(name = "law_firm_name", nullable = true, length = 25)
    private String lawFirmName;

    public Integer getLawFirmId() {
        return lawFirmId;
    }

    public void setLawFirmId(Integer lawFirmId) {
        this.lawFirmId = lawFirmId;
    }

    public String getLawFirmName() {
        return lawFirmName;
    }

    public void setLawFirmName(String lawFirmName) {
        this.lawFirmName = lawFirmName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LawFirm lawFirm = (LawFirm) o;
        return Objects.equals(lawFirmId, lawFirm.lawFirmId) && Objects.equals(lawFirmName, lawFirm.lawFirmName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lawFirmId, lawFirmName);
    }
}
