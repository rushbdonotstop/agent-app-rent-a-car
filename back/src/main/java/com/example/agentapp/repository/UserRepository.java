package com.example.agentapp.repository;

import com.example.agentapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  
    List<User> findAll();
    User findByUsernameAndPassword(String username, String password);
    User findOneById(Long id);
    User findOneByUsername(String username);
  
}
