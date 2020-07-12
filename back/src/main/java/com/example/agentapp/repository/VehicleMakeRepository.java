package com.example.agentapp.repository;

import com.example.agentapp.model.VehicleMake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleMakeRepository extends JpaRepository<VehicleMake, Long> {
    VehicleMake findOneById(long parseLong);

    VehicleMake findByValue(String value);
}
