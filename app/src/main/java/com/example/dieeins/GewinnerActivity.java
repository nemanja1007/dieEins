package com.example.dieeins;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


        /**
        * Klasse GewinnerActivity
        * Hier ist die Activity für die Endseite (Gewinner-Seite) festgelegt
        */
public class GewinnerActivity extends AppCompatActivity {

    private TextView ausgabeGewinner;
    private TextView ausgabeScore;

    /** Überschriebene onCreate Methode
     * Hier wird definiert, was zum Aufruf der Seite gemacht wird
     *
     * Der Gewinner des Spiels wird angezeigt
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gewinner);
        Button btn = (Button)findViewById(R.id.button4);
        Bundle bundle = getIntent().getExtras();
        String gewinner = bundle.getString("gewinner");
        int score = bundle.getInt("score");
        ausgabeGewinner = (TextView) findViewById(R.id.textview12);
        ausgabeScore = (TextView) findViewById(R.id.textView8);
        ausgabeGewinner.setText(gewinner + " hat gewonnen!");
        ausgabeScore.setText("Du hast " + score + " Würfe benötigt.");
        btn.setOnClickListener(new View.OnClickListener() {
            /**
             * Überschriebene onClick Methode
             * Leitet zurück zum Anfang mit "Nochmals Spielen"
             * @param v
             */
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GewinnerActivity.this, MainActivity.class));
            }
        });
    }
}