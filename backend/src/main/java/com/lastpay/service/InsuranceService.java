package com.lastpay.service;

import com.lastpay.model.Car;
import com.lastpay.model.InsuranceQuote;
import org.springframework.stereotype.Service;

@Service
public class InsuranceService {

    public InsuranceQuote getInsuranceQuote(Car car) {
        if (car == null) {
            return new InsuranceQuote(0.0, "No Coverage", "No Provider", 0);
        }

        double basePremium = calculateBasePremium(car);
        String coverage = determineCoverage(car);
        String provider = selectProvider(car);
        int termMonths = 12;

        return new InsuranceQuote(basePremium, coverage, provider, termMonths);
    }

    private double calculateBasePremium(Car car) {
        double premium = car.getPrice() * 0.02; // Base 2% of car value

        // Adjust based on car category
        switch (car.getCategory().toLowerCase()) {
            case "suv":
                premium *= 1.2;
                break;
            case "electric sedan":
                premium *= 0.9; // Discount for electric
                break;
            case "sedan":
                premium *= 1.0;
                break;
            default:
                premium *= 1.1;
                break;
        }

        return Math.round(premium * 100.0) / 100.0;
    }

    private String determineCoverage(Car car) {
        if (car.getPrice() > 40000) {
            return "Comprehensive Plus";
        } else if (car.getPrice() > 25000) {
            return "Comprehensive";
        } else {
            return "Basic";
        }
    }

    private String selectProvider(Car car) {
        String[] providers = {"SafeGuard Insurance", "AutoProtect Pro", "DriveSecure"};
        return providers[car.getModel().hashCode() % providers.length];
    }
}
