package com.lastpay.controller;

import com.lastpay.dto.CombinedResponse;
import com.lastpay.dto.CustomerFormRequest;
import com.lastpay.service.RequestProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class RequestController {

    @Autowired
    private RequestProcessingService requestProcessingService;

    @PostMapping("/request")
    public ResponseEntity<CombinedResponse> processRequest(@RequestBody CustomerFormRequest request) {
        try {
            CombinedResponse response = requestProcessingService.processCustomerRequest(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
