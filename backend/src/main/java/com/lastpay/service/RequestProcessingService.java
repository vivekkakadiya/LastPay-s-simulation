package com.lastpay.service;

import com.lastpay.dto.CombinedResponse;
import com.lastpay.dto.CustomerFormRequest;
import com.lastpay.model.*;
import com.lastpay.repository.CustomerRequestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestProcessingService {

    @Autowired
    private DealerService dealerService;

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private BankService bankService;

    @Autowired
    private CustomerRequestRepository customerRequestRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public CombinedResponse processCustomerRequest(CustomerFormRequest request) {
        try {
            // Get car details from dealer
            Car carDetails = dealerService.getCarDetails(request.getSelectedCarModel());
            if (carDetails == null) {
                throw new RuntimeException("Car model not found: " + request.getSelectedCarModel());
            }

            // Get insurance quote
            InsuranceQuote insuranceQuote = insuranceService.getInsuranceQuote(carDetails);

            // Get loan approval
            LoanApproval loanApproval = bankService.getLoanApproval(request.getCustomerName(), carDetails);

            // Determine overall approval
            boolean overallApproved = loanApproval.isApproved();

            // Save to database
            CustomerRequest customerRequest = new CustomerRequest(request.getCustomerName(), request.getSelectedCarModel());
            customerRequest.setCarDetails(objectMapper.writeValueAsString(carDetails));
            customerRequest.setInsuranceResult(objectMapper.writeValueAsString(insuranceQuote));
            customerRequest.setLoanResult(objectMapper.writeValueAsString(loanApproval));
            customerRequest.setApproved(overallApproved);

            customerRequestRepository.save(customerRequest);

            // Return combined response
            return new CombinedResponse(request.getCustomerName(), carDetails, insuranceQuote, loanApproval, overallApproved);

        } catch (Exception e) {
            throw new RuntimeException("Error processing request: " + e.getMessage(), e);
        }
    }
}
