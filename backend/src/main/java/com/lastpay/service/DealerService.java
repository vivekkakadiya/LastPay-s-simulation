package com.lastpay.service;

import com.lastpay.model.Car;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DealerService {
    
    private final Map<String, Car> carInventory;

    public DealerService() {
        carInventory = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        carInventory.put("TOYOTA_CAMRY", new Car("Camry", "Toyota", 25000.0, "Sedan", "Hybrid", 2024));
        carInventory.put("BMW_X3", new Car("X3", "BMW", 45000.0, "SUV", "Gasoline", 2024));
        carInventory.put("TESLA_MODEL_3", new Car("Model 3", "Tesla", 35000.0, "Electric Sedan", "Electric", 2024));
    }

    public Car getCarDetails(String carModel) {
        return carInventory.get(carModel.toUpperCase());
    }

    public Map<String, Car> getAllCars() {
        return new HashMap<>(carInventory);
    }
}
