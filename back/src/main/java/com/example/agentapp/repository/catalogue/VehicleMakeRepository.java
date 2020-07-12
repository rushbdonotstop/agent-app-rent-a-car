package com.example.agentapp.repository.catalogue;

import com.example.agentapp.model.catalogue.VehicleMake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleMakeRepository extends JpaRepository<VehicleMake, Long> {
    VehicleMake findOneById(long parseLong);

    VehicleMake findByValue(String value);
}
