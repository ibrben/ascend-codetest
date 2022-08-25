package com.ascendcorp.interviewquiz.models;

public class CovidProvinceCaseData {

    private String province;
    private Long totalCases;

    public CovidProvinceCaseData() {
    }

    public CovidProvinceCaseData(String province, Long totalCases) {
        this.province = province;
        this.totalCases = totalCases;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Long getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(Long totalCases) {
        this.totalCases = totalCases;
    }
}
