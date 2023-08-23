package com.SpringProject.SpringBootProject;
import com.SpringProject.SpringBootProject.controller.ReportsController;
import com.SpringProject.SpringBootProject.entity.Reports;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.ReportsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReportsControllerTests {
    @Mock
    ReportsRepository reportsRepository;
    @InjectMocks
    ReportsController reportsController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testFindAllReports(){
        List<Reports> reportsList = new ArrayList<>();
        reportsList.add(new Reports(null, null, "44,Page missing."));
        Page<Reports> reportsPage = new PageImpl<>(reportsList);

        when(reportsRepository.findAll(any(Pageable.class))).thenReturn(reportsPage);
        Page<Reports> result = reportsController.findAllReports(Pageable.unpaged());
        assertEquals(reportsList.size(), result.getContent().size());
    }

    @Test
    public void testFindReportById(){
       Reports report = new Reports(null, null, "44,Page missing.");
        when(reportsRepository.findById(anyLong())).thenReturn(Optional.of(report));

       Reports result = reportsController.findReportById(1L);
        assertEquals(report.getDescription(),result.getDescription());
    }
    @Test
    public void testFindReportByIdNotFound(){
        Reports report = new Reports(null, null, "44,Page missing.");
        when(reportsRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()-> reportsController.findReportById(1L) );

    }
    @Test
    public void testCreateOrder(){
        Reports report = new Reports(null, null, "44,Page missing.");
        when(reportsRepository.save(any(Reports.class))).thenReturn(report);
        Reports result = reportsController.createReport(report);
        assertEquals(result.getDescription(),report.getDescription());
    }
    @Test
    public void testUpdateReport(){
        Reports existingReport = new Reports(null, null, "44,Page missing.");
        when(reportsRepository.findById(anyLong())).thenReturn(Optional.of(existingReport));
        when(reportsRepository.save(any(Reports.class))).thenReturn(existingReport);

       Reports updatedReport = new Reports(null, null, "80,Empty page.");
        Reports result = reportsController.updateReport(updatedReport,1L);
        assertEquals(existingReport.getDescription(),result.getDescription());
        assertEquals(existingReport.getDescription(),result.getDescription());

    }

    @Test
    public void testUpdateReportNotFound(){
        when(reportsRepository.findById(anyLong())).thenReturn(Optional.empty());

        Reports updatedReport = new Reports(null, null, "80,Empty page.");


        assertThrows(ResourceNotFoundException.class, ()-> reportsController.updateReport(updatedReport,1L));
    }

    @Test
    public void testDeleteReport(){
        Reports existingReport = new Reports(null, null, "44,Page missing.");

        when(reportsRepository.findById(anyLong())).thenReturn(Optional.of(existingReport));
        ResponseEntity<Reports> result = reportsController.deleteReport(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    @Test
    public void testDeletePaymentNotFound(){
        when(reportsRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, ()-> reportsController.deleteReport(1L));
    }




}
