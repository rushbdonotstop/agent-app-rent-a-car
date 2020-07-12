package com.example.agentapp.repository.location;

import com.example.agentapp.model.location.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City findByValue(String city);
}
