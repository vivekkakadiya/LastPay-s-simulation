package com.lastpay.dto;

import com.lastpay.model.Car;
import com.lastpay.model.InsuranceQuote;
import com.lastpay.model.LoanApproval;

public class CombinedResponse {
    private String customerName;
    private Car carDetails;
    private InsuranceQuote insuranceQuote;
    private LoanApproval loanApproval;
    private boolean overallApproved;

    public CombinedResponse() {}

    public CombinedResponse(String customerName, Car carDetails, InsuranceQuote insuranceQuote, 
                          LoanApproval loanApproval, boolean overallApproved) {
        this.customerName = customerName;
        this.carDetails = carDetails;
        this.insuranceQuote = insuranceQuote;
        this.loanApproval = loanApproval;
        this.overallApproved = overallApproved;
    }

    // Getters and Setters
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public Car getCarDetails() { return carDetails; }
    public void setCarDetails(Car carDetails) { this.carDetails = carDetails; }

    public InsuranceQuote getInsuranceQuote() { return insuranceQuote; }
    public void setInsuranceQuote(InsuranceQuote insuranceQuote) { this.insuranceQuote = insuranceQuote; }

    public LoanApproval getLoanApproval() { return loanApproval; }
    public void setLoanApproval(LoanApproval loanApproval) { this.loanApproval = loanApproval; }

    public boolean isOverallApproved() { return overallApproved; }
    public void setOverallApproved(boolean overallApproved) { this.overallApproved = overallApproved; }
}
