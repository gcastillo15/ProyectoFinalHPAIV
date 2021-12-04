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

import com.example.proyectofinal.MainActivity;
import com.example.proyectofinal.R;

public class GyroscopeSensor extends AppCompatActivity {

    private SensorManager sm;
    private Sensor gyroscopeSensor;
    private SensorEventListener gyroscopeSensorListener;
    Button regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope_sensor);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        regresar = findViewById(R.id.btnregresar);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });

        if (gyroscopeSensor == null) {
            Toast.makeText(this, "This device hasn´t Gyroscope", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Create a listener
        gyroscopeSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.values[2] > 0.5f) { // anticlockwise
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                    //(IZQUIERDA)Toast.makeText(this, "Izquierda",Toast.LENGTH_SHORT).show();

                } else if(sensorEvent.values[2] < -0.5f) { // clockwise
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                    //(DERECHA) Toast.makeText(this, "DERECHA", Toast.LENGTH_SHORT).show();
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
        sm.registerListener(gyroscopeSensorListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sm.registerListener(gyroscopeSensorListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }
}