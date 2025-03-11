package com.example.demo.repositories;

import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    // Find users by job title (role)
    List<User> findByRole(String role);

    // Find employees reporting to a specific manager
    List<User> findByManager_Id(Long managerId);
}
