package com.ascendcorp.interviewquiz.clients;

import com.ascendcorp.interviewquiz.models.CovidReportData;
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
        return Arrays.asList();
    }

}
