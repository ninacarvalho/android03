package com.marinacarvalho.quiz03;

public class TaxCalculation {
    // Constants for tax brackets and rates (2024 Ontario + Federal Combined Rates)
    private final double[][] TAX_BRACKETS = {
            {0, 49020, 0.150},    // 15% on first $49,020
            {49020, 98040, 0.205}, // 20.5% on next $49,020
            {98040, 151978, 0.26}, // 26% on next $53,938
            {151978, 216511, 0.29}, // 29% on next $64,533
            {216511, Double.MAX_VALUE, 0.33} // 33% on anything above $216,511
    };

    private final double RRSP_LIMIT_PERCENTAGE = 0.18; // 18% of annual income
    private final double MAX_RRSP_LIMIT = 27230; // Maximum RRSP deduction limit for 2024

    // User's income and RRSP contribution for calculations
    private double annualIncome;
    private double rrspContribution;

    // Constructor
    public TaxCalculation(double annualIncome, double rrspContribution) {
        this.annualIncome = annualIncome;
        this.rrspContribution = rrspContribution;
    }

    // Method to calculate combined federal and provincial taxes
    public double calculateTax() {
        double taxableIncome = annualIncome - rrspContribution;
        double tax = 0.0;

        for (double[] bracket : TAX_BRACKETS) {
            double lower = bracket[0];
            double upper = bracket[1];
            double rate = bracket[2];

            if (taxableIncome > lower) {
                double incomeInBracket = Math.min(upper - lower, taxableIncome - lower);
                tax += incomeInBracket * rate;
            } else {
                break;
            }
        }
        return tax;
    }

    // Method to calculate RRSP limit
    public double calculateRRSPDeductionLimit() {
        double limit = Math.min(annualIncome * RRSP_LIMIT_PERCENTAGE, MAX_RRSP_LIMIT);
        return limit;
    }

    // What-if analysis for RRSP contributions
    public double calculateTaxWithRRSP(double newRrspContribution) {
        if (newRrspContribution > calculateRRSPDeductionLimit()) {
            newRrspContribution = calculateRRSPDeductionLimit(); // Cap at max limit
        }
        this.rrspContribution = newRrspContribution;
        return calculateTax();
    }

    // Getters and Setters
    public double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public double getRrspContribution() {
        return rrspContribution;
    }

    public void setRrspContribution(double rrspContribution) {
        this.rrspContribution = rrspContribution;
    }
}
