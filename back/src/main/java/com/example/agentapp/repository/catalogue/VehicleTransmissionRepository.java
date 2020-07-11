package com.example.agentapp.repository.catalogue;

import com.example.agentapp.model.catalogue.VehicleTransmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleTransmissionRepository extends JpaRepository<VehicleTransmission, Long> {
    VehicleTransmission findOneById(long parseLong);

    List<VehicleTransmission> findAll();
}
