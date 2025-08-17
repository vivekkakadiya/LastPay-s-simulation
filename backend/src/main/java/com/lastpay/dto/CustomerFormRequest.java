package com.lastpay.dto;

public class CustomerFormRequest {
    private String customerName;
    private String selectedCarModel;

    public CustomerFormRequest() {}

    public CustomerFormRequest(String customerName, String selectedCarModel) {
        this.customerName = customerName;
        this.selectedCarModel = selectedCarModel;
    }

    // Getters and Setters
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getSelectedCarModel() { return selectedCarModel; }
    public void setSelectedCarModel(String selectedCarModel) { this.selectedCarModel = selectedCarModel; }
}
