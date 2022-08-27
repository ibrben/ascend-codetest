package com.ascendcorp.interviewquiz.services;

import com.ascendcorp.interviewquiz.clients.CovidReportServiceClient;
import com.ascendcorp.interviewquiz.models.CovidProvinceCaseData;
import com.ascendcorp.interviewquiz.models.CovidProvinceCaseResponse;
import com.ascendcorp.interviewquiz.models.CovidReportData;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CovidReportApiService implements CovidReportService {

    private final CovidReportServiceClient covidReportServiceClient;
    public CovidReportApiService(CovidReportServiceClient covidReportServiceClient) {
        this.covidReportServiceClient = covidReportServiceClient;
    }

    @Override
    public List<CovidReportData> getReports(String date) {
        // TODO 2.1 Implement the first API by call public API//Programmer code begin
        List<CovidReportData> reportDatas = covidReportServiceClient.fetchCovidReport();
        List<CovidReportData> responseData = new ArrayList<CovidReportData>();
        if(!"".equals(date) && date != null)
            responseData = reportDatas.stream().filter(data -> data.getTxnDate().equals(date)).toList();
        return responseData;
        //Programmer code end
    }

    @Override
    public CovidProvinceCaseResponse getHighestLowestReport() {
        // TODO 2.2 Implement the second API by call public API
        //Programmer code begin
        CovidProvinceCaseResponse covidProvinceCaseResponse;
        List<CovidReportData> reportDatas = covidReportServiceClient.fetchCovidReport();
        String latestRecordedDate = reportDatas.stream().max(Comparator.comparing(x -> x.getTxnDate())).get().getTxnDate();
        List<CovidReportData> latestRecordedData = reportDatas.stream().filter(x -> x.getTxnDate().equals(latestRecordedDate)).toList();
        Long maxCase = latestRecordedData.stream().mapToLong(x -> x.getTotalCase()).max().getAsLong();
        Long minCase = latestRecordedData.stream().mapToLong(x -> x.getTotalCase()).min().getAsLong();
        List<CovidProvinceCaseData> lstMaxCases = latestRecordedData.stream().filter(x -> x.getTotalCase().equals(maxCase)).map(x -> new CovidProvinceCaseData(x.getProvince(), x.getTotalCase())).toList();
        List<CovidProvinceCaseData> lstMinCases = latestRecordedData.stream().filter(x -> x.getTotalCase().equals(minCase)).map(x -> new CovidProvinceCaseData(x.getProvince(), x.getTotalCase())).toList();
        covidProvinceCaseResponse = new CovidProvinceCaseResponse(lstMaxCases, lstMinCases);
        
        //Programmer code end
        return covidProvinceCaseResponse;
    }

}
