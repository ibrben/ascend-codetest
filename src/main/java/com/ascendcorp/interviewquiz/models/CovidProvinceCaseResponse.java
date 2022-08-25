package com.ascendcorp.interviewquiz.models;

import java.util.List;

public class CovidProvinceCaseResponse {

    private List<CovidProvinceCaseData> highest;
    private List<CovidProvinceCaseData> lowest;

    public CovidProvinceCaseResponse() {
    }

    public CovidProvinceCaseResponse(
        List<CovidProvinceCaseData> highest,
        List<CovidProvinceCaseData> lowest
    ) {
        this.highest = highest;
        this.lowest = lowest;
    }

    public List<CovidProvinceCaseData> getHighest() {
        return highest;
    }

    public void setHighest(List<CovidProvinceCaseData> highest) {
        this.highest = highest;
    }

    public List<CovidProvinceCaseData> getLowest() {
        return lowest;
    }

    public void setLowest(List<CovidProvinceCaseData> lowest) {
        this.lowest = lowest;
    }

}
