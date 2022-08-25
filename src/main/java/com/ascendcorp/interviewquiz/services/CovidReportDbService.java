package com.ascendcorp.interviewquiz.services;

import com.ascendcorp.interviewquiz.clients.CovidReportServiceClient;
import com.ascendcorp.interviewquiz.entities.CovidReportEntity;
import com.ascendcorp.interviewquiz.models.CovidProvinceCaseResponse;
import com.ascendcorp.interviewquiz.models.CovidReportData;
import com.ascendcorp.interviewquiz.repositories.CovidReportRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CovidReportDbService implements CovidReportService {

    private final CovidReportServiceClient covidReportServiceClient;
    private final CovidReportRepository covidReportRepository;
    private final Logger logger = LoggerFactory.getLogger(CovidReportDbService.class);

    public CovidReportDbService(
        CovidReportServiceClient covidReportServiceClient,
        CovidReportRepository covidReportRepository
    ) {
        this.covidReportServiceClient = covidReportServiceClient;
        this.covidReportRepository = covidReportRepository;
    }

    @PostConstruct
    public void fetchCovidReport() {
        logger.info("Start fetchCovidReport()");
        long start = System.nanoTime();
        List<CovidReportData> response = covidReportServiceClient.fetchCovidReport();
        Set<CovidReportEntity> entitySet = response
            .stream()
            .map(data -> {
                // TODO 4.1 Initialize data into database
                return new CovidReportEntity();
            })
            .collect(Collectors.toSet());
        covidReportRepository.saveAll(entitySet);
        logger.info("End fetchCovidReport() [{} ms]", (System.nanoTime() - start) / 1_000_000);
    }

    @Override
    public List<CovidReportData> getReports(String date) {
        // TODO 4.2 Implement the first API by query from database
        return new ArrayList<>();
    }

    @Override
    public CovidProvinceCaseResponse getHighestLowestReport() {
        // TODO 4.3 Implement the second API by query from database
        return new CovidProvinceCaseResponse();
    }

}
