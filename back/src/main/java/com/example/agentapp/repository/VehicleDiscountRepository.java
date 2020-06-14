package com.example.agentapp.repository;

import com.example.agentapp.model.VehicleDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDiscountRepository extends JpaRepository<VehicleDiscount, Long> {
}
