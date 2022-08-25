package com.ascendcorp.interviewquiz.controllers;

import com.ascendcorp.interviewquiz.models.CovidProvinceCaseResponse;
import com.ascendcorp.interviewquiz.models.CovidReportData;
import com.ascendcorp.interviewquiz.services.CovidReportService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/covid-cases/reports")
public class CovidReportController {

    private final CovidReportService covidReportService;
    private final Logger logger = LoggerFactory.getLogger(CovidReportController.class);
    private final String covidReportServiceName;

    public CovidReportController(
        @Qualifier("covidReportApiService") CovidReportService covidReportService
    ) {
        this.covidReportService = covidReportService;
        this.covidReportServiceName = covidReportService.getClass().getSimpleName();
    }

    @GetMapping
    public List<CovidReportData> getReports(
        @RequestParam(value = "date", required = false) String date
    ) {
        logger.info("Start {}.getReports()", covidReportServiceName);
        long start = System.nanoTime();
        List<CovidReportData> response = covidReportService.getReports(date);
        logger.info(
            "End {}.getReports() [{} ms]", covidReportServiceName,
            (System.nanoTime() - start) / 1_000_000
        );
        return response;
    }

    @GetMapping("/highest-lowest")
    public CovidProvinceCaseResponse getHighestLowestReports() {
        logger.info("Start {}.getHighestLowestReports()", covidReportServiceName);
        long start = System.nanoTime();
        CovidProvinceCaseResponse response = covidReportService.getHighestLowestReport();
        logger.info(
            "End {}.getHighestLowestReports() [{} ms]", covidReportServiceName,
            (System.nanoTime() - start) / 1_000_000
        );
        return response;
    }

}
