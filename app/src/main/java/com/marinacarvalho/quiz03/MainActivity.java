package com.marinacarvalho.quiz03;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.slider.Slider;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.slider.Slider;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TaxCalculator taxCalculator; // Reference to TaxCalculator class
    private EditText otherIncomeInput;
    private Slider rrspSlider;
    private TextView taxCalculated, taxAmountValue, rrspLimitValue;
    private Button calculateTaxButton, refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        otherIncomeInput = findViewById(R.id.other_income_input);
        rrspSlider = findViewById(R.id.rrsp_slider);
        taxCalculated = findViewById(R.id.tax_calculated);
        taxAmountValue = findViewById(R.id.tax_amount_value);
        rrspLimitValue = findViewById(R.id.rrsp_limit_value);
        calculateTaxButton = findViewById(R.id.calculate_tax_button);
        refreshButton = findViewById(R.id.refresh_button);
        // Initialize Slider and TextView
        Slider slider = findViewById(R.id.rrsp_slider);
        TextView taxCalculated = findViewById(R.id.tax_calculated);

        // Instantiate the TaxCalculator (example)
        taxCalculator = new TaxCalculator();

        // Set listeners
        calculateTaxButton.setOnClickListener(v -> calculateTax());
        refreshButton.setOnClickListener(v -> refreshData());
        setupSliderListener();
    }


    private void setupSliderListener() {
        rrspSlider.addOnChangeListener((slider, value, fromUser) -> {
            double rrspDeduction = value;
            taxCalculated.setText("Tax: $" + String.format("%.2f", rrspDeduction));
        });
    }

    private void calculateTax() {
        // Retrieve user input
        String otherIncome = otherIncomeInput.getText().toString().trim();
        double rrspDeduction = rrspSlider.getValue();

        // Store in local variables (for now, no processing)
        double otherIncomeValue = otherIncome.isEmpty() ? 0 : Float.parseFloat(otherIncome);

        // Further calculations could be added here in the future
        taxCalculator.setEarnedIncome(otherIncomeValue);
        taxCalculator.setRsspDeduction(rrspDeduction);

        // Calculate the tax
        double taxOwed = taxCalculator.calculateTax(rrspDeduction);
        double contributionLimitNextYear = taxCalculator.getRSSPcontributionForNextYear();

        // Update the tax_amount_value TextView
        taxAmountValue.setText(String.format("$%.2f", taxOwed));
        rrspLimitValue.setText(String.format("$%.2f", contributionLimitNextYear));
    }

    private void refreshData() {
        // Reset all input values
        otherIncomeInput.setText("");
        rrspSlider.setValue(0); // Reset slider to minimum value
        taxCalculated.setText("Tax: $0.00");
        taxAmountValue.setText("0.00");
        rrspLimitValue.setText("0.00");
    }
}
