package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProximitySensor extends AppCompatActivity {

    private SensorManager sm;
    private Sensor PROXSensor;
    private SensorEventListener PROXSensorListener;
    Button rgrsar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_sensor);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        PROXSensor= sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        rgrsar = findViewById(R.id.btnregresar);

        rgrsar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });

        if (PROXSensor == null) {
            Toast.makeText(this, "This device hasn´t Proximity", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Create a listener
        PROXSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.values[0] < PROXSensor.getMaximumRange()) {
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);

                }
                else  {
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
}
    // Register the listener
    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(PROXSensorListener, PROXSensor,2 * 1000 * 1000);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(PROXSensorListener);
    }
}