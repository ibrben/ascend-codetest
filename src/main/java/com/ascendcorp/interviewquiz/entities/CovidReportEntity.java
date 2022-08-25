package com.ascendcorp.interviewquiz.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "covid_report")
public class CovidReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "date")
    private String date;
    @Column(name = "new_case")
    private Long newCase;
    @Column(name = "total_case")
    private Long totalCase;
    @Column(name = "province")
    private String province;
    @Column(name = "new_case_exclude_abroad")
    private Long newCaseExcludeAbroad;
    @Column(name = "total_case_exclude_abroad")
    private Long totalCaseExcludeAbroad;

    public CovidReportEntity() {
    }

    public CovidReportEntity(
        String date,
        Long newCase,
        Long totalCase,
        String province,
        Long newCaseExcludeAbroad,
        Long totalCaseExcludeAbroad
    ) {
        this.date = date;
        this.newCase = newCase;
        this.totalCase = totalCase;
        this.newCaseExcludeAbroad = newCaseExcludeAbroad;
        this.totalCaseExcludeAbroad = totalCaseExcludeAbroad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getNewCase() {
        return newCase;
    }

    public void setNewCase(Long newCase) {
        this.newCase = newCase;
    }

    public Long getTotalCase() {
        return totalCase;
    }

    public void setTotalCase(Long totalCase) {
        this.totalCase = totalCase;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Long getNewCaseExcludeAbroad() {
        return newCaseExcludeAbroad;
    }

    public void setNewCaseExcludeAbroad(Long newCaseExcludeAbroad) {
        this.newCaseExcludeAbroad = newCaseExcludeAbroad;
    }

    public Long getTotalCaseExcludeAbroad() {
        return totalCaseExcludeAbroad;
    }

    public void setTotalCaseExcludeAbroad(Long totalCaseExcludeAbroad) {
        this.totalCaseExcludeAbroad = totalCaseExcludeAbroad;
    }
}
