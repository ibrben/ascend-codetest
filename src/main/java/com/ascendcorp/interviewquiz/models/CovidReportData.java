package com.ascendcorp.interviewquiz.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CovidReportData {

    @JsonProperty("txn_date")
    private String txnDate;
    @JsonProperty("new_case")
    private Long newCase;
    @JsonProperty("total_case")
    private Long totalCase;
    @JsonProperty("province")
    private String province;
    @JsonProperty("new_case_excludeabroad")
    private Long newCaseExcludeAbroad;
    @JsonProperty("total_case_excludeabroad")
    private Long totalCaseExcludeAbroad;

    public CovidReportData() {
    }

    public CovidReportData(
        String txnDate,
        Long newCase,
        Long totalCase,
        String province,
        Long newCaseExcludeAbroad,
        Long totalCaseExcludeAbroad
    ) {
        this.txnDate = txnDate;
        this.newCase = newCase;
        this.totalCase = totalCase;
        this.newCaseExcludeAbroad = newCaseExcludeAbroad;
        this.totalCaseExcludeAbroad = totalCaseExcludeAbroad;
    }

    public String getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(String txnDate) {
        this.txnDate = txnDate;
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
