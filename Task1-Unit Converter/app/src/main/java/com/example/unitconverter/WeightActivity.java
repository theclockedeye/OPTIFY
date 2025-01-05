package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WeightActivity extends AppCompatActivity {

    private Spinner fromSpinner, toSpinner;
    private EditText input;
    private TextView result;
    private Button convertButton;
    private String[] units = {"Kilograms", "Grams", "Pounds", "Ounces"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
        input = findViewById(R.id.inputValue);
        result = findViewById(R.id.resultText);
        convertButton = findViewById(R.id.convertButton);

        // Set up the spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        // Set up the convert button listener
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromUnit = fromSpinner.getSelectedItem().toString();
                String toUnit = toSpinner.getSelectedItem().toString();
                double inputValue = Double.parseDouble(input.getText().toString());
                double convertedValue = convertWeight(fromUnit, toUnit, inputValue);
                result.setText(String.format("%.2f", convertedValue));
            }
        });
    }

    private double convertWeight(String fromUnit, String toUnit, double value) {
        // Conversion logic here
        // Convert all to kilograms first
        switch (fromUnit) {
            case "Grams":
                value /= 1000; // 1000 g = 1 kg
                break;
            case "Pounds":
                value *= 0.453592; // 1 lb = 0.453592 kg
                break;
            case "Ounces":
                value *= 0.0283495; // 1 oz = 0.0283495 kg
                break;
            // Kilograms require no conversion
        }

        // Convert from kilograms to target unit
        switch (toUnit) {
            case "Grams":
                return value * 1000;
            case "Pounds":
                return value / 0.453592;
            case "Ounces":
                return value / 0.0283495;
            // Kilograms require no conversion
        }
        return value; // No conversion needed
    }
}
