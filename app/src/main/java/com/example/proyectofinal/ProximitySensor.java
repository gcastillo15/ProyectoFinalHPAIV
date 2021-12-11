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
import android.widget.ImageView;
import android.widget.TextView;

public class ProximitySensor extends AppCompatActivity {

    SensorManager sm;
    Sensor PROXSensor;
    SensorEventListener PROXSensorListener;
    Button rgrsar;
    ImageView imagen;
    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_sensor);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        PROXSensor= sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        rgrsar = findViewById(R.id.btnregresar);
        imagen = findViewById(R.id.imageView);
        txt =findViewById(R.id.txt1);

        rgrsar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });


        if (PROXSensor == null) {
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        }

        // Create a listener
        PROXSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.values[0] < PROXSensor.getMaximumRange()) {
                    imagen.setImageResource(R.drawable.cerca);
                    txt.setText("Cerca");

                }
                else  {
                    imagen.setImageResource(R.drawable.white);
                    txt.setText("Lejos");
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