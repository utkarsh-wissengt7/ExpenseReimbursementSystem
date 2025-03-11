package com.example.demo.controllers;

import com.example.demo.models.Expenses;
import com.example.demo.services.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenses")
public class ExpensesController {

    @Autowired
    private ExpensesService expensesService;

    @GetMapping
    public List<Expenses> getAllExpenses() {
        return expensesService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public Optional<Expenses> getExpenseById(@PathVariable Long id) {
        return expensesService.getExpenseById(id);
    }

    @PostMapping
    public Expenses createExpense(@RequestBody Expenses expense) {
        return expensesService.createExpense(expense);
    }

    @PutMapping("/{id}")
    public Expenses updateExpense(@PathVariable Long id, @RequestBody Expenses updatedExpense) {
        return expensesService.updateExpense(id, updatedExpense);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expensesService.deleteExpense(id);
    }

    @GetMapping("/pending")
    public List<Expenses> getPendingExpenses() {
        return expensesService.getPendingExpenses();
    }

    @GetMapping("/approved")
    public List<Expenses> getApprovedExpenses() {
        return expensesService.getApprovedExpenses();
    }

    @PutMapping("/{id}/status")
    public Expenses updateApprovalStatus(@PathVariable Long id, @RequestParam String status) {
        return expensesService.updateApprovalStatus(id, status);
    }
}
