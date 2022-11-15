package com.example.vegeta1;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Temperature extends AppCompatActivity implements SensorEventListener {
    protected TextView textView;
    protected SensorManager sensorManager;
    protected Sensor tempSensor;
    protected boolean isSensorOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        textView = findViewById(R.id.textTemp);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isSensorOK = true;
        } else {
            textView.setText("No disponible por el momento");
            isSensorOK = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        textView.setText(sensorEvent.values[0] + " °C");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isSensorOK) {
            sensorManager.registerListener(this, tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isSensorOK) {
            sensorManager.unregisterListener(this);
        }
    }

    public void btnVolver(View view) {
        Intent intentVolver = new Intent(this, SegundoActivity.class);
        startActivity(intentVolver);
    }
}
