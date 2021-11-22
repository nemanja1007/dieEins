package com.example.dieeins;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class SpielfeldActivity extends AppCompatActivity implements SensorEventListener{

    int[] wuerfel = new int[] {1,2,3,4,5,6};
    int[] gewuerfelt = new int[5];

    int punkteSpieler1 = 0;
    int punkteSpieler2 = 0;
    private SensorManager mgr;
    private Sensor temp;
    private TextView tempTxt;
    int startSpieler = 1;
    String spieler1 ="";
    String spieler2 = "";
    float acceleration;

    private TextView spielerAnDerReihe;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spielfeld);

        mgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);

        temp = mgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        tempTxt = (TextView) findViewById(R.id.textView10);



        Bundle bundle = getIntent().getExtras();
        spieler1 = bundle.getString("spieler1");
        spieler2 = bundle.getString("spieler2");
        TextView spieler1Name = (TextView) findViewById(R.id.textView15);
        TextView spieler2Name = (TextView) findViewById(R.id.textView16);
        spieler1Name.setText(spieler1);
        spieler2Name.setText(spieler2);

        TextView punkte1 = (TextView) findViewById(R.id.textView13);
        TextView punkte2 = (TextView) findViewById(R.id.textView14);

        punkte1.setText("Punkte: "+punkteSpieler1);
        punkte2.setText("Punkte: "+punkteSpieler2);

        spielerAnDerReihe = (TextView) findViewById(R.id.textView9);
        spielerAnDerReihe.setText(spieler1 + " ist an der Reihe.");
    }

    public static int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    @Override
    protected void onResume() {
        mgr.registerListener(this, temp, SensorManager.SENSOR_ACCELEROMETER);
        super.onResume();
    }

    @Override
    protected void onPause() {
        mgr.unregisterListener(this, temp);
        super.onPause();
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(acceleration != sensorEvent.values[0]){
            acceleration = sensorEvent.values[0];
            spielerWechsel();
        }
        tempTxt.setText("Schüttel durchgeführt"+acceleration);
        //text.invalidate();
    }

    public void spielerWechsel() {
        if (startSpieler == 1) {
            spielerAnDerReihe.setText(spieler1 + " ist an der Reihe.");
            startSpieler = 0;
        } else {
            spielerAnDerReihe.setText(spieler2 + " ist an der Reihe.");
            startSpieler = 1;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //ignore this, not needed
    }

}