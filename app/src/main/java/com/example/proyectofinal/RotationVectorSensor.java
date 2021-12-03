package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RotationVectorSensor extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor rotationVectorSensor;
    SensorEventListener rvListener;
    ImageView imagen;
    TextView txt;
    Button regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation_vector_sensor);

        imagen = (ImageView)findViewById(R.id.imagen);
        txt = (TextView)findViewById(R.id.txt);
        regresar = findViewById(R.id.regresar);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        // Create a listener
        rvListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                float[] rotationMatrix = new float[16];
                SensorManager.getRotationMatrixFromVector(rotationMatrix, sensorEvent.values);

                // Remap coordinate system
                float[] remappedRotationMatrix = new float[16];
                SensorManager.remapCoordinateSystem(rotationMatrix, SensorManager.AXIS_X, SensorManager.AXIS_Z, remappedRotationMatrix);

                // Convert to orientations
                float[] orientations = new float[3];
                SensorManager.getOrientation(remappedRotationMatrix, orientations);

                for(int i = 0; i < 3; i++) {
                    orientations[i] = (float)(Math.toDegrees(orientations[i]));
                }

                if(orientations[2] > 30) { //DERECHA
                    imagen.setImageResource(R.drawable.derecha);
                    txt.setText("¡Giraste el móvil hacia la derecha!");
                } else if(orientations[2] < -30) { //IZQUIERDA
                    imagen.setImageResource(R.drawable.izquierda);
                    txt.setText("¡Giraste el móvil hacia la izquierda!");
                } else if(Math.abs(orientations[2]) < 10) { //CENTRO
                    imagen.setImageResource(R.drawable.centro);
                    txt.setText("¡Hey gira tu móvil y adivinaré a qué lado lo giraste!");
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

        sensorManager.registerListener(rvListener, rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}