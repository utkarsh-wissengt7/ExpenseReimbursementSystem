package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    private Double amount;
    private String category;
    private String receipt;
    private String status;
    private Long approvedBy;
    private Long rejectedBy;
    private String reasonForRejection;
    public Long getId() {
        return expenseID;
    }
}
