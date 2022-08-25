package com.ascendcorp.interviewquiz.services;

import com.ascendcorp.interviewquiz.models.CovidProvinceCaseResponse;
import com.ascendcorp.interviewquiz.models.CovidReportData;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CovidReportApiService implements CovidReportService {

    @Override
    public List<CovidReportData> getReports(String date) {
        // TODO 2.1 Implement the first API by call public API
        return new ArrayList<>();
    }

    @Override
    public CovidProvinceCaseResponse getHighestLowestReport() {
        // TODO 2.2 Implement the second API by call public API
        return new CovidProvinceCaseResponse();
    }

}
