package com.lastpay.controller;

import com.lastpay.model.Car;
import com.lastpay.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class DealerController {

    @Autowired
    private DealerService dealerService;

    @GetMapping("/dealer")
    public ResponseEntity<Map<String, Car>> getAllCars() {
        return ResponseEntity.ok(dealerService.getAllCars());
    }

    @GetMapping("/dealer/{carModel}")
    public ResponseEntity<Car> getCarDetails(@PathVariable String carModel) {
        Car car = dealerService.getCarDetails(carModel);
        if (car != null) {
            return ResponseEntity.ok(car);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
