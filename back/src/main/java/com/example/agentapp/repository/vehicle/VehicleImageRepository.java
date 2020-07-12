package com.example.agentapp.repository.vehicle;

import com.example.agentapp.model.vehicle.VehicleImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleImageRepository extends JpaRepository<VehicleImage, Long> {
    Optional<VehicleImage> findByName(String name);
}
