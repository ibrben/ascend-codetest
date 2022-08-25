package com.ascendcorp.interviewquiz.services;

import com.ascendcorp.interviewquiz.models.CovidProvinceCaseResponse;
import com.ascendcorp.interviewquiz.models.CovidReportData;
import java.util.List;

public interface CovidReportService {

    List<CovidReportData> getReports(String date);

    CovidProvinceCaseResponse getHighestLowestReport();

}