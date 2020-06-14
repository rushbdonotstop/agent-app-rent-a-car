package com.example.agentapp.repository;

import com.example.agentapp.model.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long> {
    Street findByValue(String street);
}
