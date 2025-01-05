package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TimeActivity extends AppCompatActivity {

    private Spinner fromSpinner, toSpinner;
    private EditText input;
    private TextView result;
    private Button convertButton;
    private String[] units = {"Seconds", "Minutes", "Hours", "Days"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

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
                double convertedValue = convertTime(fromUnit, toUnit, inputValue);
                result.setText(String.format("%.2f", convertedValue));
            }
        });
    }

    private double convertTime(String fromUnit, String toUnit, double value) {
        // Conversion logic here
        // Convert all to seconds first
        switch (fromUnit) {
            case "Minutes":
                value *= 60; // 1 min = 60 seconds
                break;
            case "Hours":
                value *= 3600; // 1 hour = 3600 seconds
                break;
            case "Days":
                value *= 86400; // 1 day = 86400 seconds
                break;
            // Seconds require no conversion
        }

        // Convert from seconds to target unit
        switch (toUnit) {
            case "Minutes":
                return value / 60;
            case "Hours":
                return value / 3600;
            case "Days":
                return value / 86400;
            // Seconds require no conversion
        }
        return value; // No conversion needed
    }
}
