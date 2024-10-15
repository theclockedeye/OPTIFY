package com.example.stopwatch;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textViewMinutes, textViewSeconds, textViewMilliseconds;
    private Button buttonStart, buttonReset;

    private Handler handler = new Handler();
    private long startTime = 0L;
    private boolean isRunning = false;

    private  Runnable updateTimer = new Runnable() {
        @Override
        public void run() {
            if (isRunning) {
                long currentTime = System.currentTimeMillis() - startTime;

                int minutes = (int) (currentTime / 1000) / 60;
                int seconds = (int) (currentTime / 1000) % 60;
                int milliseconds = (int) (currentTime % 1000) / 10;

                // Update the TextViews
                textViewMinutes.setText(String.format("%02d", minutes));
                textViewSeconds.setText(String.format("%02d", seconds));
                textViewMilliseconds.setText(String.format("%02d", milliseconds));

                // Schedule the next update after 16 milliseconds (~60fps)
                handler.postDelayed(this, 16);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize views
        textViewMinutes = findViewById(R.id.textViewMinutes);
        textViewSeconds = findViewById(R.id.textViewSeconds);  // For seconds display
        textViewMilliseconds = findViewById(R.id.textViewMilliseconds);

        buttonStart = findViewById(R.id.buttonStart);
        buttonReset = findViewById(R.id.buttonReset);

        // Start Button Click Listener
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    startTime = System.currentTimeMillis();
                    handler.post(updateTimer);  // Start the timer
                    buttonStart.setText("Pause"); // Change button text to "Pause"
                    isRunning = true;
                } else {
                    handler.removeCallbacks(updateTimer);  // Pause the timer
                    buttonStart.setText("Resume"); // Change button text to "Resume"
                    isRunning = false;
                }
            }
        });

        // Reset Button Click Listener
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset the timer
                handler.removeCallbacks(updateTimer);
                isRunning = false;
                textViewMinutes.setText("00");
                textViewSeconds.setText("00");
                textViewMilliseconds.setText("00");

                // Reset the button text to "Start"
                buttonStart.setText("Start");
            }
        });
    }
}
