package com.lastpay.model;

import jakarta.persistence.*;

@Entity
public class CustomerRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String customerName;
    private String selectedCarModel;
    
    @Column(length = 1000)
    private String carDetails;
    
    @Column(length = 1000)
    private String insuranceResult;
    
    @Column(length = 1000)
    private String loanResult;
    
    private boolean approved;

    public CustomerRequest() {}

    public CustomerRequest(String customerName, String selectedCarModel) {
        this.customerName = customerName;
        this.selectedCarModel = selectedCarModel;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getSelectedCarModel() { return selectedCarModel; }
    public void setSelectedCarModel(String selectedCarModel) { this.selectedCarModel = selectedCarModel; }

    public String getCarDetails() { return carDetails; }
    public void setCarDetails(String carDetails) { this.carDetails = carDetails; }

    public String getInsuranceResult() { return insuranceResult; }
    public void setInsuranceResult(String insuranceResult) { this.insuranceResult = insuranceResult; }

    public String getLoanResult() { return loanResult; }
    public void setLoanResult(String loanResult) { this.loanResult = loanResult; }

    public boolean isApproved() { return approved; }
    public void setApproved(boolean approved) { this.approved = approved; }
}
