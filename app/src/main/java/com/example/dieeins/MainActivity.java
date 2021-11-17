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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView highscore = (TextView)findViewById(R.id.textView3);

        SugarContext.init(this);

        User user1 = new User("Nemanja", 15);
        user1.save();
        User user2 = new User("Jahi", 6);
        user2.save();
        User user3 = new User("Marvin", 8);
        user3.save();

        int bestScore = 1000;
        long bestScoreId = 1;
        List<User> users= User.listAll(User.class);
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getScore() < bestScore){
                bestScore = users.get(i).getScore();
                bestScoreId = users.get(i).getId();
            }
        }

        User user = User.findById(User.class, bestScoreId);

        highscore.setText("Highscore: " + user.getName() + ", "+user.getScore());



        Button btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SpielerErfassen.class));
            }
        });

        Button btn= (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SpielregelnActivity.class));
            }
        });
    }
}
