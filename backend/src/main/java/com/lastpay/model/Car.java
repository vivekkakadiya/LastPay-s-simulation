package com.lastpay.model;

public class Car {
    private String model;
    private String brand;
    private double price;
    private String category;
    private String fuelType;
    private int year;

    public Car() {}

    public Car(String model, String brand, double price, String category, String fuelType, int year) {
        this.model = model;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.fuelType = fuelType;
        this.year = year;
    }

    // Getters and Setters
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getFuelType() { return fuelType; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
}
