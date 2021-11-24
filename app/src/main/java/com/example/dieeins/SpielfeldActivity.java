package com.example.dieeins;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dieeins.model.User;
import com.orm.SugarContext;

import java.util.Random;

/**
 * Klasse SpielfeldActivity
 * Hier ist die Activity für die Spiellogik festgelegt
 */
public class SpielfeldActivity extends AppCompatActivity implements SensorEventListener{

    int[] wuerfel = new int[] {1,2,3,4,5,6};
    int[] gewuerfelt = new int[5];

    int punkteSpieler1;
    int punkteSpieler2;

    Handler handler;
    int interval= 1000;

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

    private int anzWuerfeSP1;
    private int anzWuerfeSP2;

    int startSpieler = 1;
    float acceleration;
    int total;


    /**
     * Überschriebene onCreate Methode
     * Hier wird definiert, was zum Aufruf der Seite gemacht wird
     *
     * Spieler und Punktestand sind ersichtlich
     * Es wird angezeigt, welcher Spieler an der Reihe ist
     *
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spielfeld);

        //Benötigt für DB Zugriff mit Sugar
        SugarContext.init(this);

        //TYPE_ACCELEROMETER Sensor hinzugefügt für das Schütteln
        mgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        temp = mgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        tempTxt = (TextView) findViewById(R.id.textView10);
        //gewuerfelteZahlen = (TextView) findViewById(R.id.textView11);
        entaelt1 = (TextView) findViewById(R.id.textView34);


        handler = new Handler();


        Bundle bundle = getIntent().getExtras();
        spieler1 = bundle.getString("spieler1");
        spieler2 = bundle.getString("spieler2");
        punkteTitelTV = (TextView) findViewById(R.id.textView89);


        punkte1TV = (TextView) findViewById(R.id.textView90);
        punkte2TV = (TextView) findViewById(R.id.textView91);
        //Punktestände werden auf 0 gesetzt
        punkte1TV.setText(spieler1 + ": " + punkteSpieler1);
        punkte2TV.setText(spieler2 + ": " + punkteSpieler2);

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
        //Überprüfung, ob eine Beschleunigung von mind. 6.9m/s2 (acceleration) stattgefunden hat

        if(acceleration != sensorEvent.values[0]) {
            acceleration = sensorEvent.values[0];

            if (acceleration > 7) {
                spielerWechsel();
                wuerfeln();
            }
        }
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

        total = 0;

        //Zähler für Anzahl Würfe
        if (startSpieler == 1) {
            anzWuerfeSP1 = anzWuerfeSP1 + 1;
        } else if (startSpieler == 0){
            anzWuerfeSP2 = anzWuerfeSP2 + 1;
        }

        //fünf mal Würfel
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


            Log.d("---------------------", String.valueOf(total));

            //Addiert Punkte für Spieler1
            if (startSpieler == 1) {
                punkteSpieler1 = punkteSpieler1 + total;
                punkte1TV.setText(spieler1 + ": " + punkteSpieler1);
                entaelt1.setText(spieler1 + " hat " + total + " Punkte erhalten.");
                total = 0;

            } //Addiert Punkte für Spieler2
            else if (startSpieler == 0){
                punkteSpieler2 = punkteSpieler2 + total;
                punkte2TV.setText(spieler2 + ": " + punkteSpieler2);
                entaelt1.setText(spieler2 + " hat " + total + " Punkte erhalten.");
                total = 0;
            }
        }//Keine Punkte wenn 1 gewürfelt wurde
        else {

            if (startSpieler == 1) {
                entaelt1.setText( spieler1 + " hat eine 1 gewürfelt  :(");
            }
            else if (startSpieler == 0) {
                entaelt1.setText( spieler2 + " hat eine 1 gewürfelt  :(");
            }
        }

        //Überprüfung, ob Spiel schon ferting
        if (punkteSpieler1 >= 100 || punkteSpieler2 >= 100){
            Intent intent = new Intent(new Intent(SpielfeldActivity.this, GewinnerActivity.class));
            Bundle bundle = new Bundle();
            //Spieler1 = Sieger
            if (punkteSpieler1 >= 100) {
                User neuerUser1 = new User(spieler1, anzWuerfeSP1);
                neuerUser1.save();
                bundle.putString("gewinner", spieler1);
                bundle.putInt("score", anzWuerfeSP1);
            }//Spieler2 = Sieger
            else if (punkteSpieler2 >= 100){
                User neuerUser2 = new User(spieler2, anzWuerfeSP2);
                neuerUser2.save();
                bundle.putString("gewinner", spieler2);
                bundle.putInt("score", anzWuerfeSP2);
            }
            intent.putExtras(bundle);
            startActivity(intent);
        }
    /*
        gewuerfelteZahlen.setText("Gewürfelte Zahlen: " + gewuerfelt[1] +
                " " + gewuerfelt[2] +
                " " + gewuerfelt[3] +
                " " + gewuerfelt[4] +
                " " + gewuerfelt[0]);
    */
    }
}