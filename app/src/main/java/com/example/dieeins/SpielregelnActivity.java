package com.example.dieeins;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Klasse SpielregelnActivity
 * Activity-Klasse für die Darstellung der Spielregeln
 */
public class SpielregelnActivity extends AppCompatActivity {
    /**
     * Überschriebene onCreate Methode
     * Hier wird definiert, was zum Aufruf der Seite gemacht wird
     *
     * Die Spielregeln stehen auf der Seite
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spielregeln);

        Button btn = (Button)findViewById(R.id.button3);

        btn.setOnClickListener(new View.OnClickListener() {
            /**
             * Überschriebene onClick Methode
             * Leitet zurück zur Startseite beim Klick des Buttons "Zurück"
             * @param v
             */
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SpielregelnActivity.this, MainActivity.class));
            }
        });
    }
}