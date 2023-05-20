package com.SpringProject.SpringBootProject.controller;

import com.SpringProject.SpringBootProject.entity.Reports;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.ReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportsController {
    @Autowired
    private ReportsRepository reportsRepository;
    @GetMapping("/api/reports")
    public Page<Reports> findAllReports(Pageable pageable){
        return this.reportsRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Reports findReportById(@PathVariable (value = "id") long reportId){
        return this.reportsRepository.findById(reportId)
                .orElseThrow(()-> new ResourceNotFoundException("Report with this Id NOT FOUND!" + reportId));
    }
    @PostMapping
    public Reports createReport(@RequestBody Reports report){
        return this.reportsRepository.save(report);
    }
    @PutMapping("/{id}")
    public Reports updateReport(@RequestBody Reports report, @PathVariable (value="id") long reportId){
        Reports existingReport = this.reportsRepository.findById(reportId)
                .orElseThrow(()-> new ResourceNotFoundException("Report with this Id NOT FOUND!" + reportId));
        existingReport.setUser(existingReport.getUser());
        existingReport.setBook(existingReport.getBook());
        existingReport.setDescription(existingReport.getDescription());
        return this.reportsRepository.save(existingReport);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Reports> deleteReport(@PathVariable(value = "id") long reportId){
        Reports existingReport = this.reportsRepository.findById(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("Report with this id NOT FOUND: " + reportId));
        this.reportsRepository.delete(existingReport);
        return ResponseEntity.ok().build();
    }
}
