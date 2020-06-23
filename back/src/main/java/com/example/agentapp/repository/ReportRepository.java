package com.example.agentapp.repository;

import com.example.agentapp.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findAll();

    List<Report> findByVehicleId(Long vehicleId);

    Optional<Report> findById(Long id);

}

