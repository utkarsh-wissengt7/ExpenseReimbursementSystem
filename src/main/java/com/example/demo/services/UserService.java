package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        // Assign manager if provided
        if (user.getManager() != null && user.getManager().getId() != null) {
            Optional<User> manager = userRepository.findById(user.getManager().getId());
            manager.ifPresent(user::setManager);
        }
        return userRepository.save(user);
    }

    public Optional<User> updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setRole(userDetails.getRole());

            // Update manager reference if provided
            if (userDetails.getManager() != null && userDetails.getManager().getId() != null) {
                Optional<User> manager = userRepository.findById(userDetails.getManager().getId());
                manager.ifPresent(user::setManager);
            } else {
                user.setManager(null); // Allow removal of manager
            }

            return userRepository.save(user);
        });
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Get employees reporting to a specific manager
    public List<User> getSubordinates(Long managerId) {
        return userRepository.findByManager_Id(managerId);
    }
}
