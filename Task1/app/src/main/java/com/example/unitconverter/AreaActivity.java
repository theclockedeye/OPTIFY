package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AreaActivity extends AppCompatActivity {

    private Spinner fromSpinner, toSpinner;
    private EditText input;
    private TextView result;
    private Button convertButton;
    private String[] units = {"Square Meters", "Square Kilometers", "Hectares", "Acres", "Square Feet", "Square Yards"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

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
                double convertedValue = convertArea(fromUnit, toUnit, inputValue);
                result.setText(String.format("%.2f", convertedValue));
            }
        });
    }

    private double convertArea(String fromUnit, String toUnit, double value) {
        // Conversion logic here
        // Convert all to square meters first
        switch (fromUnit) {
            case "Square Kilometers":
                value *= 1_000_000;
                break;
            case "Hectares":
                value *= 10_000;
                break;
            case "Acres":
                value *= 4_046.86;
                break;
            case "Square Feet":
                value *= 0.092903;
                break;
            case "Square Yards":
                value *= 0.836127;
                break;
            // Square Meters require no conversion
        }

        // Convert from square meters to target unit
        switch (toUnit) {
            case "Square Kilometers":
                return value / 1_000_000;
            case "Hectares":
                return value / 10_000;
            case "Acres":
                return value / 4_046.86;
            case "Square Feet":
                return value / 0.092903;
            case "Square Yards":
                return value / 0.836127;
            // Square Meters require no conversion
        }
        return value; // No conversion needed
    }
}
