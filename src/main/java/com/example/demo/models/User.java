package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @JsonIgnore
    private String password;

    // Updated role to store job titles (e.g., "Associate Software Engineer", "Principal Architect")
    private String role;

    private LocalDate dateOfJoining;

    // Self-referencing many-to-one relationship for managers
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    // One-to-many relationship for employees under this manager
    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<User> subordinates;

    // One-to-many relationship for expenses associated with a user
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expenses> expenses;
}
