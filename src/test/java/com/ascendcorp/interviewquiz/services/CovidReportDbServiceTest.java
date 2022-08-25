package com.ascendcorp.interviewquiz.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ascendcorp.interviewquiz.clients.CovidReportServiceClient;
import com.ascendcorp.interviewquiz.models.CovidReportData;
import com.ascendcorp.interviewquiz.repositories.CovidReportRepository;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CovidReportDbServiceTest {

    @Mock
    private CovidReportServiceClient covidReportServiceClient;

    @Mock
    private CovidReportRepository covidReportRepository;

    private CovidReportDbService covidReportDbService;

    @Before
    public void setup() {
        covidReportDbService = new CovidReportDbService(
            covidReportServiceClient,
            covidReportRepository
        );
    }

    @Test
    public void shouldSaveDataToDBWhenFetchCovidReportSuccess() {
        CovidReportData mockResponse1 = new CovidReportData(
            "2022-01-01",
            2L,
            135L,
            "bkk",
            0L,
            0L
        );

        when(covidReportServiceClient.fetchCovidReport()).thenReturn(List.of(mockResponse1));

        covidReportDbService.fetchCovidReport();

        verify(covidReportRepository).saveAll(any());
    }

    // TODO 4.4 Add unit tests for method getReports() and line coverage must be 100%

    // TODO 4.5 Add unit tests for method getHighestLowestReport() and line coverage must be 100%

}