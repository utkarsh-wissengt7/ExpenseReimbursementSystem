package com.example.demo.services;

import com.example.demo.models.Expenses;
import com.example.demo.repositories.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpensesService {

    @Autowired
    private ExpensesRepository expensesRepository;

    public List<Expenses> getAllExpenses() {
        return expensesRepository.findAll();
    }

    public Optional<Expenses> getExpenseById(Long id) {
        return expensesRepository.findById(id);
    }

    public Expenses createExpense(Expenses expense) {
        expense.setStatus("PENDING"); // Set default status
        return expensesRepository.save(expense);
    }

    public Expenses updateExpense(Long id, Expenses updatedExpense) {
        return expensesRepository.findById(id).map(expense -> {
            expense.setAmount(updatedExpense.getAmount());
            expense.setCategory(updatedExpense.getCategory());
            expense.setReceipt(updatedExpense.getReceipt());
            return expensesRepository.save(expense);
        }).orElse(null);
    }

    public void deleteExpense(Long id) {
        expensesRepository.deleteById(id);
    }

    public List<Expenses> getPendingExpenses() {
        return expensesRepository.findByStatus("PENDING");
    }

    public List<Expenses> getApprovedExpenses() {
        return expensesRepository.findByStatus("APPROVED");
    }

    public Expenses updateApprovalStatus(Long id, String status) {
        return expensesRepository.findById(id).map(expense -> {
            if (status.equalsIgnoreCase("APPROVED") || status.equalsIgnoreCase("REJECTED")) {
                expense.setStatus(status);
                return expensesRepository.save(expense);
            }
            return null;
        }).orElse(null);
    }
}
