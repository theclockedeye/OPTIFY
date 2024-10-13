package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VolumeActivity extends AppCompatActivity {

    private Spinner fromSpinner, toSpinner;
    private EditText input;
    private TextView result;
    private Button convertButton;
    private String[] units = {"Liters", "Milliliters", "Cubic Meters", "Cubic Centimeters"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);

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
                double convertedValue = convertVolume(fromUnit, toUnit, inputValue);
                result.setText(String.format("%.2f", convertedValue));
            }
        });
    }

    private double convertVolume(String fromUnit, String toUnit, double value) {
        // Conversion logic here
        // Convert all to liters first
        switch (fromUnit) {
            case "Milliliters":
                value /= 1000; // 1000 mL = 1 L
                break;
            case "Cubic Meters":
                value *= 1000; // 1 m³ = 1000 L
                break;
            case "Cubic Centimeters":
                value /= 1000; // 1000 cm³ = 1 L
                break;
            // Liters require no conversion
        }

        // Convert from liters to target unit
        switch (toUnit) {
            case "Milliliters":
                return value * 1000;
            case "Cubic Meters":
                return value / 1000;
            case "Cubic Centimeters":
                return value * 1000;
            // Liters require no conversion
        }
        return value; // No conversion needed
    }
}
