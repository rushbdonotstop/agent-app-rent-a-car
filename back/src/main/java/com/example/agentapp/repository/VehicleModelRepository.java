package com.example.agentapp.repository;

import com.example.agentapp.model.VehicleMake;
import com.example.agentapp.model.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModel, Long> {
    VehicleModel findOneById(long parseLong);

    List<VehicleModel> findAll();

    List<VehicleModel> findByVehicleMake(VehicleMake vehicleMake);
}
