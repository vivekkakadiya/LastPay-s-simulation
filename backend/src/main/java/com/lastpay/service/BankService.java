package com.lastpay.service;

import com.lastpay.model.Car;
import com.lastpay.model.LoanApproval;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    public LoanApproval getLoanApproval(String customerName, Car car) {
        if (car == null) {
            return new LoanApproval(false, 0.0, 0.0, 0, "No Bank", "Invalid car details");
        }

        // Mock approval logic
        boolean approved = evaluateLoanApplication(customerName, car);
        double approvedAmount = approved ? car.getPrice() * 0.9 : 0.0; // 90% financing
        double interestRate = calculateInterestRate(car);
        int termMonths = 60; // 5 years
        String bankName = selectBank(car);
        String reason = approved ? "Application approved" : "Insufficient credit score";

        return new LoanApproval(approved, approvedAmount, interestRate, termMonths, bankName, reason);
    }

    private boolean evaluateLoanApplication(String customerName, Car car) {
        // Mock approval logic based on customer name and car price
        double priceScore = car.getPrice() < 50000 ? 1.0 : 0.7;
        
        return (priceScore > 0.8);
    }

    private double calculateInterestRate(Car car) {
        double baseRate = 5.5; // Base 5.5%
        
        if (car.getYear() == 2024) {
            baseRate -= 0.5; // New car discount
        }
        
        if ("Electric".equals(car.getFuelType())) {
            baseRate -= 1.0; // Green vehicle discount
        }
        
        return Math.round(baseRate * 100.0) / 100.0;
    }

    private String selectBank(Car car) {
        String[] banks = {"FirstNational Bank", "AutoCredit Union", "CarFinance Pro"};
        return banks[car.getBrand().hashCode() % banks.length];
    }
}
