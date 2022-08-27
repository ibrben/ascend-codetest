package com.ascendcorp.interviewquiz.clients;

import com.ascendcorp.interviewquiz.models.CovidReportData;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CovidReportServiceClient {

    @Qualifier("webClient")
    private final WebClient webClient;

    private final String COVID_REPORT_SOURCE_ENDPOINT = "https://covid19.ddc.moph.go.th/api/Cases/round-1to2-by-provinces";

    public CovidReportServiceClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<CovidReportData> fetchCovidReport() {
        // TODO 1.1 Implement calling public api to get covid cases data
        
        // Programmer code begin
        List<CovidReportData> reports = new ArrayList<CovidReportData>() ;
        var reportData = webClient.get().uri(COVID_REPORT_SOURCE_ENDPOINT)
            .retrieve().bodyToFlux(CovidReportData.class).collectList().block();//.subscribe(success -> reports.add(success), throwable -> System.out.println("err"));;

        // Programmer code end
        return reportData;
    }
}
