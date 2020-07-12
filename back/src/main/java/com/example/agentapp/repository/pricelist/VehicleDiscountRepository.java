package com.example.agentapp.repository.pricelist;

import com.example.agentapp.model.pricelist.VehicleDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDiscountRepository extends JpaRepository<VehicleDiscount, Long> {
}
