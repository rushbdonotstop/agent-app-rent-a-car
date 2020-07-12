package com.example.agentapp.repository.user;

import com.example.agentapp.model.user.AgentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRequestRepository extends JpaRepository<AgentRequest, Long> {
    AgentRequest findOneById(Long id);
}
