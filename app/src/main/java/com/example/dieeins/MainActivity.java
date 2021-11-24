package com.example.dieeins;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dieeins.model.User;
import com.orm.SugarContext;

import java.util.List;

/**
 * Klasse MainActivity
 * Hier ist die Activity für die Startseite festgelegt
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Überschriebene onCreate Methode
     * Hier wird definiert, was zum Aufruf der Seite gemacht wird
     *
     * Es werden 3 User erstellt. Der Highscore wird gesucht und dargestellt.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView highscore = (TextView)findViewById(R.id.textView3);

        //Benötigt für DB Zugriff mit Sugar
        SugarContext.init(this);

        //3 Benutzer hinzufügen
        User user1 = new User("Marvin", 17);
        user1.save();

        int bestScore = 1000;
        long bestScoreId = 1;

        //Highscore aus DB suchen
        List<User> users= User.listAll(User.class);
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getScore() < bestScore){
                bestScore = users.get(i).getScore();
                bestScoreId = users.get(i).getId();
            }
        }

        //Darstellung des Highscores
        User user = User.findById(User.class, bestScoreId);
        highscore.setText("Highscore: " + user.getName() + ", "+user.getScore());


        Button btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            /**
             * Überschriebene onClick Methode
             * Hier wird die nächste Seite aufgerufen für die Spielererfassung beim Klick des Buttons "Spielen"
             * @param v
             */
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SpielerErfassenActivity.class));
            }
        });

        Button btn= (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            /**
             * Überschriebene onClick Methode
             * Hier wird die Spielregeln-Seite aufgerufen beim Klick des Buttons "Spielregeln"
             * @param v
             */
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SpielregelnActivity.class));
            }
        });
    }
}