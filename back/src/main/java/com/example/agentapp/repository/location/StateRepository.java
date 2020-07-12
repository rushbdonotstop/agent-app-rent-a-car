package com.example.agentapp.repository.location;

import com.example.agentapp.model.location.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    State findByValue(String state);
}
