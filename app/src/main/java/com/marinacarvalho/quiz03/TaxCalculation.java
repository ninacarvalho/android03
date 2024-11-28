package com.marinacarvalho.quiz03;

public class TaxCalculation {

    private double taxAmount;
    private double rrspContributionLimit;
    private double earnedIncome = 120000;

    private double otherIncome;
    private double capitalGain;

    private double rrspMultiplier;
    private double taxRate;     // Simplified flat tax rate (for demonstration)

    private double sliderValue;

    public TaxCalculation(double otherIncome, double capitalGain, double rrspMultiplier) {
        this.otherIncome = otherIncome;
        this.capitalGain = capitalGain;
        this.rrspMultiplier = rrspMultiplier;
    }

    public TaxCalculation () {

    }

    //Calculate RRSP contribution limit (18% of earned income, capped at $27,230).
    private double calculateRRSPContributionLimit(double income) {
        double calculatedLimit = income * 0.18;
        return Math.min(calculatedLimit, 27230);
    }

    /***/
    public void adjustSlider(double sliderValue) {
        if (sliderValue < 0 || sliderValue > rrspContributionLimit) {
            throw new IllegalArgumentException("Slider value must be between 0 and the RRSP limit: " + rrspContributionLimit);
        }

        this.sliderValue = sliderValue;
        double taxableIncome = earnedIncome - sliderValue; // Deduct RRSP contribution from income
        double tax = taxableIncome * taxRate; // Simplified tax calculation

        // Display the results
        System.out.printf("RRSP Contribution: $%.2f\n", sliderValue);
        System.out.printf("Taxable Income: $%.2f\n", taxableIncome);
        System.out.printf("Tax Owed: $%.2f\n", tax);
    }

    /**
     * Get the current RRSP contribution limit.
     */
    public double getRRSPContributionLimit() {
        return rrspContributionLimit;
    }

    /***/

    public double getOtherIncome() {
        return otherIncome;
    }

    public void setOtherIncome(double otherIncome) {
        this.otherIncome = otherIncome;
    }

    public double getCapitalGain() {
        return capitalGain;
    }

    public void setCapitalGain(double capitalGain) {
        this.capitalGain = capitalGain;
    }

    public double getRrspMultiplier() {
        return rrspMultiplier;
    }

    public void setRrspMultiplier(double rrspMultiplier) {
        this.rrspMultiplier = rrspMultiplier;
    }

}
