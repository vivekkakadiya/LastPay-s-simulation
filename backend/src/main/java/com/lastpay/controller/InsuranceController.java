package com.lastpay.controller;

import com.lastpay.model.Car;
import com.lastpay.model.InsuranceQuote;
import com.lastpay.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    @PostMapping("/insurance")
    public ResponseEntity<InsuranceQuote> getInsuranceQuote(@RequestBody Car car) {
        InsuranceQuote quote = insuranceService.getInsuranceQuote(car);
        return ResponseEntity.ok(quote);
    }
}
