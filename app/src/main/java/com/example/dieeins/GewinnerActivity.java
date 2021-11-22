package com.example.dieeins;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/**
 * Klasse GewinnerActivity
 * Hier ist die Activity für die Endseite (Gewinner-Seite) festgelegt
 */
public class GewinnerActivity extends AppCompatActivity {

    /**
     * Überschriebene onCreate Methode
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