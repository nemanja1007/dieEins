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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dieeins.model.User;
import com.orm.SugarContext;

import java.util.Arrays;
import java.util.Random;

/**
 * Klasse SpielfeldActivity
 * Hier ist die Activity für die Spiellogik festgelegt
 */
public class SpielfeldActivity extends AppCompatActivity implements SensorEventListener{

    int[] wuerfel = new int[] {1,2,3,4,5,6};
    int[] gewuerfelt = new int[5];

    int punkteSpieler1 = 0;
    int punkteSpieler2 = 0;

    private String SpielerDran;

    private SensorManager mgr;
    private Sensor temp;
    private TextView tempTxt;
    private TextView gewuerfelteZahlen;
    private TextView spielerAnDerReihe;
    private TextView spieler1Name;
    private TextView spieler2Name;
    private TextView punkte1;
    private TextView punkte2;

    private String spieler1;
    private String spieler2;

    int startSpieler = 1;
    float acceleration;

    /**
     * Überschriebene onCreate Methode
     * Hier wird definiert, was zum Aufruf der Seite gemacht wird
     *
     * Spieler und Punktestand sind ersichtlich
     * Es wird angezeigt, welcher Spieler an der Reihe ist
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spielfeld);

        //Benötigt für DB Zugriff mit Sugar
        SugarContext.init(this);

        mgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);

        //TYPE_ACCELEROMETER Sensor hinzugefügt für das Schütteln
        temp = mgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        tempTxt = (TextView) findViewById(R.id.textView10);
        gewuerfelteZahlen = (TextView) findViewById(R.id.textView11);



        Bundle bundle = getIntent().getExtras();
        spieler1 = bundle.getString("spieler1");
        spieler2 = bundle.getString("spieler2");
        spieler1Name = (TextView) findViewById(R.id.textView15);
        spieler2Name = (TextView) findViewById(R.id.textView16);
        spieler1Name.setText(spieler1);
        spieler2Name.setText(spieler2);

        punkte1 = (TextView) findViewById(R.id.textView13);
        punkte2 = (TextView) findViewById(R.id.textView14);

        punkte1.setText("punkte: "+punkteSpieler1);
        punkte2.setText("punkte: "+punkteSpieler2);

        spielerAnDerReihe = (TextView) findViewById(R.id.textView9);
        spielerAnDerReihe.setText(spieler1 + " ist an der Reihe.");

    }

    /**
     * Methode getRandom, welche zufällige Zahl aus mitgegebenen Array zurückgibt
     * @param array
     * @return array[rnd]
     */
    public static int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    /**
     * Überschriebene onResume Methode, welche den Listener SENSOR_ACCELEROMETER
     * registriert für das Schütteln beim Würfeln
     */
    @Override
    protected void onResume() {
        mgr.registerListener(this, temp, SensorManager.SENSOR_ACCELEROMETER);
        super.onResume();


    }

    /**
     * Überschriebene onPause Methode, welche den Listener SENSOR_ACCELEROMETER
     * wieder entfernt
     */
    @Override
    protected void onPause() {
        mgr.unregisterListener(this, temp);
        super.onPause();


    }

    /**
     * Überschriebene onSensorChanged Methode, welche Bewegungen erkennt
     * Hier wird der Spielerwechsel und das Würfeln aufgerufen
     * @param sensorEvent
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //Überprüfung, ob eine Beschleunigung von mind. 6.5m/s2 (acceleration) stattgefunden hat
        if(acceleration != sensorEvent.values[0]){
            acceleration = sensorEvent.values[0];
            if(acceleration > 6.5) {
                spielerWechsel();
                wuerfeln();
            }
        }
        tempTxt.setText("Schüttel durchgeführt "+acceleration);
    }

    /**
     * spielerWechsel-Methode
     * Hier findet der Spielerwechsel statt
     */
    public void spielerWechsel() {
        if (startSpieler == 1) {
            spielerAnDerReihe.setText(spieler1 + " ist an der Reihe.");
            startSpieler = 0;
        } else {
            spielerAnDerReihe.setText(spieler2 + " ist an der Reihe.");
            startSpieler = 1;
        }
    }

    /**
     * Implementierte Methode onAccuracyChanged
     * Wird nicht bei uns benutzt
     * @param sensor
     * @param i
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //nicht gebraucht
    }


    /**
     * wuerfeln-Methode
     * Hier werden die 5 Würfel gewürfelt und das Total zusammengezählt
     * Je nach Würfel, wird das korrekte Bild angezeigt
     * Überprüfung, ob eine 1 dabei ist, findet auch hier statt-->falls ja: keine Punkte
     *
     */
    public void wuerfeln(){

        int total = 0;

        //fünf mal Würfeln
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


        //Würfel 1
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

        //Würfel 2
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

        //Würfel 3
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

        //Würfel 4
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

        //Würfel 5
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

        //Überprüfung, ob eine 1 enthalten ist im Gewürfeltem
        for (int i = 0; i < 5 ; i++ ){
            if (gewuerfelt[i] == 1){
                enthaeltEins = true;
            }
        }

        if (enthaeltEins != true){
            punkteSpieler1 = punkteSpieler1 + total;
        }

        gewuerfelteZahlen.setText("Gewürfelte Zahlen: " + gewuerfelt[1] +
                " " + gewuerfelt[2] +
                " " + gewuerfelt[3] +
                " " + gewuerfelt[4] +
                " " + gewuerfelt[0]);

    }
}