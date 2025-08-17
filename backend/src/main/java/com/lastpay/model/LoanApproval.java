package com.lastpay.model;

public class LoanApproval {
    private boolean approved;
    private double approvedAmount;
    private double interestRate;
    private int termMonths;
    private String bankName;
    private String reason;

    public LoanApproval() {}

    public LoanApproval(boolean approved, double approvedAmount, double interestRate, 
                       int termMonths, String bankName, String reason) {
        this.approved = approved;
        this.approvedAmount = approvedAmount;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.bankName = bankName;
        this.reason = reason;
    }

    // Getters and Setters
    public boolean isApproved() { return approved; }
    public void setApproved(boolean approved) { this.approved = approved; }

    public double getApprovedAmount() { return approvedAmount; }
    public void setApprovedAmount(double approvedAmount) { this.approvedAmount = approvedAmount; }

    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) { this.interestRate = interestRate; }

    public int getTermMonths() { return termMonths; }
    public void setTermMonths(int termMonths) { this.termMonths = termMonths; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
