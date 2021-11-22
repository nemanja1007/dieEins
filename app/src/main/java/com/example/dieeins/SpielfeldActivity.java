package com.example.dieeins;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class SpielfeldActivity extends AppCompatActivity implements SensorEventListener{

    int[] wuerfel = new int[] {1,2,3,4,5,6};
    int[] gewuerfelt = new int[5];

    int punkteSpieler1;
    int punkteSpieler2;

    private String SpielerDran;

    private SensorManager mgr;
    private Sensor temp;
    private TextView tempTxt;
    private TextView gewuerfelteZahlen;
    private TextView spielerAnDerReihe;
    private TextView punkteTitelTV;
    private TextView spieler2Name;
    private TextView punkte1TV;
    private TextView punkte2TV;
    private TextView entaelt1;

    private String spieler1;
    private String spieler2;

    int startSpieler = 1;
    float acceleration;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spielfeld);

        mgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);

        temp = mgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        tempTxt = (TextView) findViewById(R.id.textView10);
        gewuerfelteZahlen = (TextView) findViewById(R.id.textView11);
        entaelt1 = (TextView) findViewById(R.id.textView34);


        Bundle bundle = getIntent().getExtras();
        spieler1 = bundle.getString("spieler1");
        spieler2 = bundle.getString("spieler2");
        punkteTitelTV = (TextView) findViewById(R.id.textView89);


        punkte1TV = (TextView) findViewById(R.id.textView90);
        punkte2TV = (TextView) findViewById(R.id.textView91);
        // punktestände werden auf 0 gesetzt
        punkte1TV.setText(spieler1 + ": " + punkteSpieler1);
        punkte2TV.setText(spieler2 + ": " + punkteSpieler2);

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
            if(acceleration > 6.5) {

                spielerWechsel();
                wuerfeln();
            }
        }
        tempTxt.setText("Schüttel durchgeführt "+acceleration);
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



    @SuppressLint("SetTextI18n")
    public void wuerfeln(){

        int total = 0;

        //wünf mal Würfel
        for (int i = 0; i < 5 ; i++ ){
            int temp = getRandom(wuerfel);
            gewuerfelt[i] = temp;
            total = total + temp;
        }

        ImageView imageView1 = (ImageView) findViewById(R.id.imageView);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView3);
        ImageView imageView3 = (ImageView) findViewById(R.id.imageView4);
        ImageView imageView4 = (ImageView) findViewById(R.id.imageView5);
        ImageView imageView5 = (ImageView) findViewById(R.id.imageView6);


        //würfel 1
        if (gewuerfelt[0] == 1) {
            imageView1.setImageResource(R.drawable.dice1);
        }else if (gewuerfelt[0] == 2) {
            imageView1.setImageResource(R.drawable.dice2);
        }else if (gewuerfelt[0] == 3) {
            imageView1.setImageResource(R.drawable.dice3);
        }else if (gewuerfelt[0] == 4) {
            imageView1.setImageResource(R.drawable.dice4);
        }else if (gewuerfelt[0] == 5) {
            imageView1.setImageResource(R.drawable.dice5);
        }else if (gewuerfelt[0] == 6) {
            imageView1.setImageResource(R.drawable.dice6);
        }

        //würfel 2
        if (gewuerfelt[1] == 1) {
            imageView2.setImageResource(R.drawable.dice1);
        }else if (gewuerfelt[1] == 2) {
            imageView2.setImageResource(R.drawable.dice2);
        }else if (gewuerfelt[1] == 3) {
            imageView2.setImageResource(R.drawable.dice3);
        }else if (gewuerfelt[1] == 4) {
            imageView2.setImageResource(R.drawable.dice4);
        }else if (gewuerfelt[1] == 5) {
            imageView2.setImageResource(R.drawable.dice5);
        }else if (gewuerfelt[1] == 6) {
            imageView2.setImageResource(R.drawable.dice6);
        }

        //würfel 3
        if (gewuerfelt[2] == 1) {
            imageView3.setImageResource(R.drawable.dice1);
        }else if (gewuerfelt[2] == 2) {
            imageView3.setImageResource(R.drawable.dice2);
        }else if (gewuerfelt[2] == 3) {
            imageView3.setImageResource(R.drawable.dice3);
        }else if (gewuerfelt[2] == 4) {
            imageView3.setImageResource(R.drawable.dice4);
        }else if (gewuerfelt[2] == 5) {
            imageView3.setImageResource(R.drawable.dice5);
        }else if (gewuerfelt[2] == 6) {
            imageView3.setImageResource(R.drawable.dice6);
        }

        //würfel 4
        if (gewuerfelt[3] == 1) {
            imageView4.setImageResource(R.drawable.dice1);
        }else if (gewuerfelt[3] == 2) {
            imageView4.setImageResource(R.drawable.dice2);
        }else if (gewuerfelt[3] == 3) {
            imageView4.setImageResource(R.drawable.dice3);
        }else if (gewuerfelt[3] == 4) {
            imageView4.setImageResource(R.drawable.dice4);
        }else if (gewuerfelt[3] == 5) {
            imageView4.setImageResource(R.drawable.dice5);
        }else if (gewuerfelt[3] == 6) {
            imageView4.setImageResource(R.drawable.dice6);
        }

        //würfel 5
        if (gewuerfelt[4] == 1) {
            imageView5.setImageResource(R.drawable.dice1);
        }else if (gewuerfelt[4] == 2) {
            imageView5.setImageResource(R.drawable.dice2);
        }else if (gewuerfelt[4] == 3) {
            imageView5.setImageResource(R.drawable.dice3);
        }else if (gewuerfelt[4] == 4) {
            imageView5.setImageResource(R.drawable.dice4);
        }else if (gewuerfelt[4] == 5) {
            imageView5.setImageResource(R.drawable.dice5);
        }else if (gewuerfelt[4] == 6) {
            imageView5.setImageResource(R.drawable.dice6);
        }

        boolean enthaeltEins = false;

        for (int i = 0; i < 5 ; i++ ){
            if (gewuerfelt[i] == 1){
                enthaeltEins = true;
            }
        }


        if (enthaeltEins != true){

            entaelt1.setText("Du hast keine 1 gewürfelt");

            if (spielerAnDerReihe.equals(spieler1)) {

                punkteSpieler1 = punkteSpieler1 + total;
                punkte1TV.setText(spieler1 + ": " + punkteSpieler1);

            } else if (spielerAnDerReihe.equals(spieler2)){

                punkteSpieler2 = punkteSpieler2 + total;
                punkte2TV.setText(spieler2 + ": " + punkteSpieler2);
            }
        } else {
            entaelt1.setText("Du hast eine 1 gewürfelt");
        }

        gewuerfelteZahlen.setText("Gewürfelte Zahlen: " + gewuerfelt[1] +
                " " + gewuerfelt[2] +
                " " + gewuerfelt[3] +
                " " + gewuerfelt[4] +
                " " + gewuerfelt[0]);

    }
}