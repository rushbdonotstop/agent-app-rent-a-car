package com.example.agentapp.repository;

import com.example.agentapp.model.Pricelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PricelistRepository extends JpaRepository<Pricelist, Long> {
    List<Pricelist> findByVehicleId(Long vehicleId);

    List<Pricelist> findAll();

 
}
