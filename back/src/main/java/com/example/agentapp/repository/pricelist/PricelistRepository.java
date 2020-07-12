package com.example.agentapp.repository.pricelist;

import com.example.agentapp.model.pricelist.Pricelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PricelistRepository extends JpaRepository<Pricelist, Long> {
    List<Pricelist> findByVehicleId(Long vehicleId);

    List<Pricelist> findAll();

 
}
