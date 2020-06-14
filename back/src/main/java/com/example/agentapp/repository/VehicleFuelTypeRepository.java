package com.example.agentapp.repository;

import com.example.agentapp.model.VehicleFuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleFuelTypeRepository extends JpaRepository<VehicleFuelType, Long> {
    VehicleFuelType findOneById(long parseLong);
}
