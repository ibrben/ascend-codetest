package com.ascendcorp.interviewquiz.services;

import com.ascendcorp.interviewquiz.clients.CovidReportServiceClient;
import com.ascendcorp.interviewquiz.entities.CovidReportEntity;
import com.ascendcorp.interviewquiz.models.CovidProvinceCaseData;
import com.ascendcorp.interviewquiz.models.CovidProvinceCaseResponse;
import com.ascendcorp.interviewquiz.models.CovidReportData;
import com.ascendcorp.interviewquiz.repositories.CovidReportRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
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
                return new CovidReportEntity(
                    data.getTxnDate(),
                    data.getNewCase(),
                    data.getTotalCase(),
                    data.getProvince(),
                    data.getNewCaseExcludeAbroad(),
                    data.getTotalCaseExcludeAbroad()
                );
            })
            .collect(Collectors.toSet());
        covidReportRepository.saveAll(entitySet);
        logger.info("End fetchCovidReport() [{} ms]", (System.nanoTime() - start) / 1_000_000);
    }

    @Override
    public List<CovidReportData> getReports(String date) {
        // TODO 4.2 Implement the first API by query from database
        if(date == null || "".equals(date)) {
            return Arrays.asList();
        }
        List<CovidReportEntity> result = covidReportRepository.findByDateIs(date).get();
        List<CovidReportData> report = result.stream().map(data -> {
            return new CovidReportData(
                data.getDate(),
                data.getNewCase(),
                data.getTotalCase(),
                data.getProvince(),
                data.getNewCaseExcludeAbroad(),
                data.getTotalCaseExcludeAbroad()
            );
        }).collect(Collectors.toList());
        
        return new ArrayList<>(report);
    }

    @Override
    public CovidProvinceCaseResponse getHighestLowestReport() {
        // TODO 4.3 Implement the second API by query from database
        List<CovidReportEntity> result = covidReportRepository.findByDateIs(covidReportRepository.getLastReportedDate().get()).get();
        CovidProvinceCaseResponse provinceCases = new CovidProvinceCaseResponse();
        provinceCases.setHighest(Arrays.asList(result.stream().max(Comparator.comparing(x -> x.getTotalCase())).map(x -> new CovidProvinceCaseData(x.getProvince(), x.getTotalCase())).get()));
        provinceCases.setLowest(Arrays.asList(result.stream().min(Comparator.comparing(x -> x.getTotalCase())).map(x -> new CovidProvinceCaseData(x.getProvince(), x.getTotalCase())).get()));
        return provinceCases;
    }

}
