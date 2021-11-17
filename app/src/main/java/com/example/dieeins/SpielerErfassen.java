package com.example.dieeins;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SpielerErfassen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spielererfassen);
        Button btn = (Button)findViewById(R.id.buttonSpielerErfassen);
        TextView spieler1 = (TextView)findViewById(R.id.plain_text_input);
        TextView spieler2 = (TextView)findViewById(R.id.plain_text_input2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp1 = spieler1.getText().toString();
                String temp2 = spieler2.getText().toString();
                Intent intent = new Intent(new Intent(SpielerErfassen.this, SpielfeldActivity.class));
                Bundle bundle = new Bundle();
                bundle.putString("spieler1", temp1);
                bundle.putString("spieler2", temp2);
                intent.putExtras(bundle);
                startActivity(intent);


            }
        });
    }
}