package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LengthActivity extends AppCompatActivity {

    private Spinner fromSpinner, toSpinner;
    private EditText input;
    private TextView result;
    private Button convertButton;
    private String[] units = {"Meters", "Kilometers", "Miles", "Yards", "Feet"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

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
                double convertedValue = convertLength(fromUnit, toUnit, inputValue);
                result.setText(String.format("%.2f", convertedValue));
            }
        });
    }

    private double convertLength(String fromUnit, String toUnit, double value) {
        // Conversion logic here
        // Convert all to meters first
        switch (fromUnit) {
            case "Kilometers":
                value *= 1000;
                break;
            case "Miles":
                value *= 1609.34;
                break;
            case "Yards":
                value *= 0.9144;
                break;
            case "Feet":
                value *= 0.3048;
                break;
            // Meters require no conversion
        }

        // Convert from meters to target unit
        switch (toUnit) {
            case "Kilometers":
                return value / 1000;
            case "Miles":
                return value / 1609.34;
            case "Yards":
                return value / 0.9144;
            case "Feet":
                return value / 0.3048;
            // Meters require no conversion
        }
        return value; // No conversion needed
    }
}
