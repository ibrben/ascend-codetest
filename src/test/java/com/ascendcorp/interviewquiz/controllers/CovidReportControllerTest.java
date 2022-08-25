package com.ascendcorp.interviewquiz.controllers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ascendcorp.interviewquiz.models.CovidProvinceCaseResponse;
import com.ascendcorp.interviewquiz.models.CovidReportData;
import com.ascendcorp.interviewquiz.services.CovidReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class CovidReportControllerTest {

    @Mock
    private CovidReportService covidReportService;

    private CovidReportController covidReportController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        covidReportController = new CovidReportController(covidReportService);
        mockMvc = MockMvcBuilders.standaloneSetup(covidReportController).build();
    }

    @Test
    public void shouldResponseOkAndCovidReportDataListWhenCallApiGetReportsWithDateSuccess()
        throws Exception {
        CovidReportData mockResponse1 = new CovidReportData(
            "2022-01-01",
            2L,
            135L,
            "bkk",
            0L,
            0L
        );
        CovidReportData mockResponse2 = new CovidReportData(
            "2022-01-02",
            3L,
            138L,
            "bkk",
            0L,
            0L
        );

        when(covidReportService.getReports(anyString())).thenReturn(
            List.of(mockResponse2, mockResponse1));

        mockMvc.perform(
                get("/covid-cases/reports").param("date", "2022-01-01")
            )
            .andExpect(status().isOk())
            .andExpect(content().json(
                objectMapper.writeValueAsString(List.of(mockResponse2, mockResponse1))));
    }

    @Test
    public void shouldResponseOkAndEmptyListWhenCallApiGetReportsWithoutDateSuccessButNoData() {
        // TODO 1.3 Implement unit test for api Get Reports for case no date param and api result is empty list
    }

    @Test
    public void shouldResponseOkAndCovidProvinceCaseResponseWhenCallApiGetHighestLowestReportsSuccess() {
        // TODO 1.4 Implement unit test for api Get Highest Lowest Reports for case success
    }

    @Test
    public void shouldResponseOkAndEmptyResponseWhenCallApiGetHighestLowestReportsSuccessButNodata()
        throws Exception {
        CovidProvinceCaseResponse expected = new CovidProvinceCaseResponse(List.of(), List.of());

        when(covidReportService.getHighestLowestReport()).thenReturn(expected);

        mockMvc.perform(
                get("/covid-cases/reports/highest-lowest")
            )
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(expected)));
    }

}