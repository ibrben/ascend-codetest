package com.ascendcorp.interviewquiz.services;


import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ascendcorp.interviewquiz.models.CovidProvinceCaseData;
import com.ascendcorp.interviewquiz.models.CovidProvinceCaseResponse;
import com.ascendcorp.interviewquiz.models.CovidReportData;
import com.flextrade.jfixture.JFixture;

import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response;

import com.ascendcorp.interviewquiz.clients.CovidReportServiceClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CovidReportApiServiceTest {

    @Mock
    private CovidReportServiceClient covidReportServiceClient;
    
    JFixture fixture;
    private CovidReportApiService covidReportApiService;

    @Before
    public void setup() {
        covidReportApiService = new CovidReportApiService(covidReportServiceClient);
        fixture = new JFixture();
    }
    // TODO 2.3 Add unit tests for method getReports() and line coverage must be 100%
    // Programmer code begin
    @Test
    public void shouldReturnEmptyListIfNoDateParamProvided() {
        //Arrange
        List<CovidReportData> reports = Lists.newArrayList(fixture.collections().createCollection(CovidReportData.class));
        
        when(covidReportServiceClient.fetchCovidReport()).thenReturn(reports);
        //Act
        var response = covidReportApiService.getReports(null);
        //Assert
        Assert.assertNotNull(response);
        Assert.assertEquals(0, response.size());
    }   
    @Test
    public void shouldReturnLIstOfCovidReportData() {
        //Arrange
        String date = fixture.create(String.class);
        fixture.customise().propertyOf(CovidReportData.class, "txnDate", date);
        List<CovidReportData> reports = Lists.newArrayList(fixture.collections().createCollection(CovidReportData.class));

        when(covidReportServiceClient.fetchCovidReport()).thenReturn(reports);
        //Act
        var response = covidReportApiService.getReports(date);

        //Assert
        assertNotNull(response);
        assertEquals(reports, response);
    }
    @Test
    public void shouldReturnLIstOfCovidReportDataWithMatchedDateOnly() {
        //Arrange
        String date = fixture.create(String.class);
        
        List<CovidReportData> reports = Lists.newArrayList(fixture.collections().createCollection(CovidReportData.class));
        CovidReportData specificReport = fixture.create(CovidReportData.class);
        specificReport.setTxnDate(date);
        reports.add(specificReport);
        when(covidReportServiceClient.fetchCovidReport()).thenReturn(reports);
        //Act
        var response = covidReportApiService.getReports(date);

        //Assert
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(specificReport, response.get(0));
    }
    // Programmer code end
    // TODO 2.4 Add unit tests for method getHighestLowestReport() and line coverage must be 100%
    // Programmer code begin
    @Test
    public void shouldReturnListOfProvinceReport() {

        //Arrange
        String date = fixture.create(String.class);
        // customize date to let all generated object have the save txnDate
        fixture.customise().propertyOf(CovidReportData.class, "txnDate", date);

        CovidReportData data1 = fixture.create(CovidReportData.class);
        CovidReportData data2 = fixture.create(CovidReportData.class);
        CovidReportData data3 = fixture.create(CovidReportData.class);

        data1.setTotalCase(Long.valueOf(1000));
        data2.setTotalCase(Long.valueOf(100));
        data3.setTotalCase(Long.valueOf(10));

        List<CovidReportData> reports = new ArrayList<CovidReportData> (Arrays.asList(data1, data2, data3));

        when(covidReportServiceClient.fetchCovidReport()).thenReturn(reports);

        //Act
        CovidProvinceCaseResponse response = covidReportApiService.getHighestLowestReport();

        //Assert
        Assert.assertEquals(CovidProvinceCaseResponse.class, response.getClass());
        Assert.assertEquals(data1.getTotalCase(),response.getHighest().get(0).getTotalCases());
        Assert.assertEquals(data3.getTotalCase(),response.getLowest().get(0).getTotalCases());
    }
    // Programmer code end

}