package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TemperatureActivity extends AppCompatActivity {

    private Spinner fromSpinner, toSpinner;
    private EditText input;
    private TextView result;
    private Button convertButton;
    private String[] units = {"Celsius", "Fahrenheit", "Kelvin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

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
                double convertedValue = convertTemperature(fromUnit, toUnit, inputValue);
                result.setText(String.format("%.2f", convertedValue));
            }
        });
    }

    private double convertTemperature(String fromUnit, String toUnit, double value) {
        // Conversion logic here
        if (fromUnit.equals("Celsius")) {
            if (toUnit.equals("Fahrenheit")) {
                return (value * 9/5) + 32;
            } else if (toUnit.equals("Kelvin")) {
                return value + 273.15;
            }
        } else if (fromUnit.equals("Fahrenheit")) {
            if (toUnit.equals("Celsius")) {
                return (value - 32) * 5/9;
            } else if (toUnit.equals("Kelvin")) {
                return (value - 32) * 5/9 + 273.15;
            }
        } else if (fromUnit.equals("Kelvin")) {
            if (toUnit.equals("Celsius")) {
                return value - 273.15;
            } else if (toUnit.equals("Fahrenheit")) {
                return (value - 273.15) * 9/5 + 32;
            }
        }
        return value; // No conversion needed
    }
}
