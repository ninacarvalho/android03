package com.marinacarvalho.quiz03;

import java.util.NavigableMap;
import java.util.TreeMap;

public class TaxCalculator {

    private double earnedIncome = 0;
    private final double RRSP_LIMIT = 27230;
    private double rsspDeduction = 0;
    private double taxableIncome;
    private static final NavigableMap<Double, Double> taxBrackets = new TreeMap<>();


    public TaxCalculator() {

    }

    // Constructor to initialize the values
    public TaxCalculator(double earnedIncome, double rsspDeduction) {
        this.earnedIncome = earnedIncome;
        this.rsspDeduction = rsspDeduction;

        taxableIncome = earnedIncome - rsspDeduction;
    }

    // Method to calculate the tax
    public double calculateTax(double rsspDeduction) {
        // Ensure RRSP contribution doesn't exceed the limit
        rsspDeduction = Math.min(rsspDeduction, RRSP_LIMIT);

        // Calculate taxable income
        double taxableIncome = earnedIncome - rsspDeduction;

        // Calculate tax owed based on taxable income
        return taxableIncome * (getTaxRate()/100);
    }

    public double getRSSPcontributionForNextYear() {

        double rrspContributionLimit = earnedIncome * 0.18;
        // Ensure RRSP contribution doesn't exceed the limit
        rrspContributionLimit = Math.min(earnedIncome, 32490);

        return rrspContributionLimit;
    }

    public void setRsspDeduction(double rsspDeduction) {
        this.rsspDeduction = rsspDeduction;
        taxableIncome = earnedIncome - rsspDeduction;
    }

    public void setEarnedIncome(double earnedIncome) {
        this.earnedIncome = earnedIncome;
        taxableIncome = earnedIncome - rsspDeduction;
    }

    public double getTaxRate() {
        return taxBrackets.floorEntry(taxableIncome).getValue();
    }

    static {
        taxBrackets.put(0.0, 20.05);          // Up to $52,886
        taxBrackets.put(52886.0, 24.15);     // $52,886 - $57,375
        taxBrackets.put(57375.0, 29.65);     // $57,375 - $93,132
        taxBrackets.put(93132.0, 31.48);     // $93,132 - $105,775
        taxBrackets.put(105775.0, 33.89);    // $105,775 - $109,727
        taxBrackets.put(109727.0, 37.91);    // $109,727 - $114,750
        taxBrackets.put(114750.0, 43.41);    // $114,750 - $150,000
        taxBrackets.put(150000.0, 44.97);    // $150,000 - $177,882
        taxBrackets.put(177882.0, 48.29);    // $177,882 - $220,000
        taxBrackets.put(220000.0, 49.85);    // $220,000 - $253,414
        taxBrackets.put(253414.0, 53.53);    // Over $253,414
    }
}
