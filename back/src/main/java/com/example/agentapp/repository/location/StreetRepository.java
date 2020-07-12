package com.example.agentapp.repository.location;

import com.example.agentapp.model.location.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long> {
    Street findByValue(String street);
}
