package com.example.agentapp.service;

import com.example.agentapp.model.Report;
import com.example.agentapp.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ReportService {

    @Autowired
    ReportRepository reportRepository;

    public Report addReport(Report report) {
        return this.reportRepository.save(report);
    }

    public List<Report> findAll() {
        return this.reportRepository.findAll();
    }

    public Optional<Report> findById(Long id) {
        return this.reportRepository.findById(id);
    }

}
