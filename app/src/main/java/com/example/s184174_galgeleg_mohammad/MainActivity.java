package com.example.s184174_galgeleg_mohammad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.s184174_galgeleg_mohammad.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button leaderboard;
    private Button StartSpil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StartSpil = findViewById(R.id.NytSpil);
        StartSpil.setOnClickListener(this);

        leaderboard = findViewById(R.id.Leaderboard);
        leaderboard.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view == findViewById(R.id.Leaderboard)){
            startActivity(new Intent(MainActivity.this, Highscore.class));
        } else if (view == findViewById(R.id.NytSpil)) {
            startActivity(new Intent(MainActivity.this, Galgespil.class));
        }
    }

}