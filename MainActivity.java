package com.example.arclake;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private EditText lightYearInput, kelvinInput;
    private TextView result, fahrenheitResult, celsiusResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightYearInput = findViewById(R.id.lightYearInput);
        kelvinInput = findViewById(R.id.kelvinInput);
        result = findViewById(R.id.result);
        fahrenheitResult = findViewById(R.id.fahrenheitResult);
        celsiusResult = findViewById(R.id.celsiusResult);
    }

    public void calculateSpeedOfLightCubed(View view) {
        try {
            BigDecimal lightYearInputValue = new BigDecimal(lightYearInput.getText().toString());
            BigDecimal speedOfLight = new BigDecimal("299792458");
            BigDecimal speedOfLightCubed = speedOfLight.pow(3);
            BigDecimal distanceAtSpeedOfLightCubed = lightYearInputValue.multiply(speedOfLightCubed);

            BigDecimal secondsInYear = new BigDecimal(365.25 * 24 * 3600);
            BigDecimal lightYearsEquivalent = distanceAtSpeedOfLightCubed.divide(speedOfLightCubed.multiply(secondsInYear), BigDecimal.ROUND_HALF_UP);

            int years = lightYearsEquivalent.intValue();
            BigDecimal remainingDays = lightYearsEquivalent.subtract(new BigDecimal(years)).multiply(new BigDecimal(365.25));
            int months = remainingDays.intValue() / 30;
            int days = remainingDays.intValue() % 30;
            int hours = remainingDays.subtract(new BigDecimal(days)).multiply(new BigDecimal(24)).intValue();
            int minutes = remainingDays.subtract(new BigDecimal(days)).multiply(new BigDecimal(24 * 60)).intValue();
            int seconds = remainingDays.subtract(new BigDecimal(days)).multiply(new BigDecimal(24 * 60 * 60)).intValue();

            result.setText(String.format("At the speed of light cubed, you would cover a distance equivalent to approximately %d years, %d months, %d days, %d hours, %d minutes, and %d seconds in terms of DART travel.", years, months, days, hours, minutes, seconds));
        } catch (Exception e) {
            Toast.makeText(this, "Please enter a valid number of light-years.", Toast.LENGTH_SHORT).show();
        }
    }

    public void convertTemperature(View view) {
        try {
            BigDecimal kelvinValue = new BigDecimal(kelvinInput.getText().toString());

            BigDecimal celsiusValue = kelvinValue.subtract(new BigDecimal("273.15"));
            BigDecimal fahrenheitValue = celsiusValue.multiply(new BigDecimal("9")).divide(new BigDecimal("5")).add(new BigDecimal("32"));

            fahrenheitResult.setText(String.format("Fahrenheit: %.2f °F", fahrenheitValue));
            celsiusResult.setText(String.format("Celsius: %.2f °C", celsiusValue));
        } catch (Exception e) {
            Toast.makeText(this, "Please enter a valid Kelvin temperature.", Toast.LENGTH_SHORT).show();
        }
    }
}