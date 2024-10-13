package com.example.unitconverter;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    public void startLengthActivity(View view) {
        Intent intent = new Intent(this, LengthActivity.class);
        startActivity(intent);
    }
    public void startVolumeActivity(View view) {
        Intent intent = new Intent(this, VolumeActivity.class);
        startActivity(intent);
    }
    public void startWeightActivity(View view) {
        Intent intent = new Intent(this, WeightActivity.class);
        startActivity(intent);
    }
    public void startTemperatureActivity(View view) {
        Intent intent = new Intent(this, TemperatureActivity.class);
        startActivity(intent);
    }
    public void startTimeActivity(View view) {
        Intent intent = new Intent(this, TimeActivity.class);
        startActivity(intent);
    }
    public void startAreaActivity(View view) {
        Intent intent = new Intent(this, AreaActivity.class);
        startActivity(intent);
    }
    public void startSpeedActivity(View view) {
        Intent intent = new Intent(this, SpeedActivity.class);
        startActivity(intent);
    }
}