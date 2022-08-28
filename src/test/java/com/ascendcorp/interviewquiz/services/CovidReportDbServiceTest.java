package com.ascendcorp.interviewquiz.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ascendcorp.interviewquiz.clients.CovidReportServiceClient;
import com.ascendcorp.interviewquiz.entities.CovidReportEntity;
import com.ascendcorp.interviewquiz.models.CovidProvinceCaseResponse;
import com.ascendcorp.interviewquiz.models.CovidReportData;
import com.ascendcorp.interviewquiz.repositories.CovidReportRepository;
import com.flextrade.jfixture.JFixture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.assertj.core.util.Lists;
import org.junit.Assert;
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

    JFixture fixture;

    @Before
    public void setup() {
        covidReportDbService = new CovidReportDbService(
            covidReportServiceClient,
            covidReportRepository
        );

        fixture = new JFixture();
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
    @Test
    public void shouldReturnEmptyArrayIfDateParamNotProvided() {
        //Act
        var response = covidReportDbService.getReports(null);
        //Assert
        Assert.assertNotNull(response);
        Assert.assertEquals(0, response.size());
    }
    
    @Test
    public void shouldReturnEmptyArrayIfEmptyStringProvidedAsDate() {
        //Act
        var response = covidReportDbService.getReports("");
        //Assert
        Assert.assertNotNull(response);
        Assert.assertEquals(0, response.size());
    }

    @Test
    public void shouldReturnLIstOfCovidReportData() {
        //Arrange
        String date = fixture.create(String.class);
        fixture.customise().propertyOf(CovidReportData.class, "txnDate", date);
        List<CovidReportEntity> reports = Lists.newArrayList(fixture.collections().createCollection(CovidReportEntity.class));

        when(covidReportRepository.findByDateIs(anyString())).thenReturn(Optional.of(reports));
        //Act
        var response = covidReportDbService.getReports(date);

        //Assert
        Assert.assertNotNull(response);
        Assert.assertTrue(response.size() > 0);
    }
    // TODO 4.5 Add unit tests for method getHighestLowestReport() and line coverage must be 100%
    @Test
    public void shouldReturnListOfProvinceData() {
        //Arrange
        String date = fixture.create(String.class);
        // customize date to let all generated object have the save txnDate
        fixture.customise().propertyOf(CovidReportData.class, "txnDate", date);

        CovidReportEntity data1 = fixture.create(CovidReportEntity.class);
        CovidReportEntity data2 = fixture.create(CovidReportEntity.class);
        CovidReportEntity data3 = fixture.create(CovidReportEntity.class);

        data1.setTotalCase(Long.valueOf(1000));
        data2.setTotalCase(Long.valueOf(100));
        data3.setTotalCase(Long.valueOf(10));

        List<CovidReportEntity> reports = new ArrayList<CovidReportEntity> (Arrays.asList(data1, data2, data3));

        when(covidReportRepository.getLastReportedDate()).thenReturn(Optional.of(date));
        when(covidReportRepository.findByDateIs(anyString())).thenReturn(Optional.of(reports));

        //Act
        CovidProvinceCaseResponse response = covidReportDbService.getHighestLowestReport();

        //Assert
        Assert.assertEquals(CovidProvinceCaseResponse.class, response.getClass());
        Assert.assertEquals(data1.getTotalCase(),response.getHighest().get(0).getTotalCases());
        Assert.assertEquals(data3.getTotalCase(),response.getLowest().get(0).getTotalCases());
    }
}