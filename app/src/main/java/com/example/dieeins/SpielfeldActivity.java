package com.example.dieeins;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SpielfeldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spielfeld);

        Bundle bundle = getIntent().getExtras();
        String spieler1 = bundle.getString("spieler1");
        String spieler2 = bundle.getString("spieler2");
        TextView spieler1Name = (TextView) findViewById(R.id.textView15);
        TextView spieler2Name = (TextView) findViewById(R.id.textView16);
        spieler1Name.setText(spieler1);
        spieler2Name.setText(spieler2);

        TextView spielerAnDerReihe = (TextView) findViewById(R.id.textView9);
        spielerAnDerReihe.setText(spieler1 + " ist an der Reihe.");



    }
}