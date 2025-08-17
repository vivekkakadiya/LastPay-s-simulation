package com.lastpay.model;

public class InsuranceQuote {
    private double premium;
    private String coverage;
    private String provider;
    private int termMonths;

    public InsuranceQuote() {}

    public InsuranceQuote(double premium, String coverage, String provider, int termMonths) {
        this.premium = premium;
        this.coverage = coverage;
        this.provider = provider;
        this.termMonths = termMonths;
    }

    // Getters and Setters
    public double getPremium() { return premium; }
    public void setPremium(double premium) { this.premium = premium; }

    public String getCoverage() { return coverage; }
    public void setCoverage(String coverage) { this.coverage = coverage; }

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public int getTermMonths() { return termMonths; }
    public void setTermMonths(int termMonths) { this.termMonths = termMonths; }
}
