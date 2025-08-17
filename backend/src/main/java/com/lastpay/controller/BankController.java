package com.lastpay.controller;

import com.lastpay.model.Car;
import com.lastpay.model.LoanApproval;
import com.lastpay.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/bank")
    public ResponseEntity<LoanApproval> getLoanApproval(@RequestBody Map<String, Object> request) {
        String customerName = (String) request.get("customerName");
        Car car = (Car) request.get("car");
        
        LoanApproval approval = bankService.getLoanApproval(customerName, car);
        return ResponseEntity.ok(approval);
    }
}
