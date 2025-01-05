package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SpeedActivity extends AppCompatActivity {

    private Spinner fromSpinner, toSpinner;
    private EditText input;
    private TextView result;
    private Button convertButton;
    private String[] units = {"Meters/Second", "Kilometers/Hour", "Miles/Hour", "Feet/Second", "Knots"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed);

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
                double convertedValue = convertSpeed(fromUnit, toUnit, inputValue);
                result.setText(String.format("%.2f", convertedValue));
            }
        });
    }

    private double convertSpeed(String fromUnit, String toUnit, double value) {
        // Conversion logic here
        // Convert all to meters/second first
        switch (fromUnit) {
            case "Kilometers/Hour":
                value /= 3.6; // 1 km/h = 1/3.6 m/s
                break;
            case "Miles/Hour":
                value /= 2.23694; // 1 mph = 1/2.23694 m/s
                break;
            case "Feet/Second":
                value /= 3.28084; // 1 ft/s = 1/3.28084 m/s
                break;
            case "Knots":
                value /= 1.94384; // 1 knot = 1/1.94384 m/s
                break;
            // Meters/Second require no conversion
        }

        // Convert from meters/second to target unit
        switch (toUnit) {
            case "Kilometers/Hour":
                return value * 3.6;
            case "Miles/Hour":
                return value * 2.23694;
            case "Feet/Second":
                return value * 3.28084;
            case "Knots":
                return value * 1.94384;
            // Meters/Second require no conversion
        }
        return value; // No conversion needed
    }
}
