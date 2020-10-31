package com.example.s184174_galgeleg_mohammad;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Galgespil extends AppCompatActivity {
    Galgelogik galgelogik = new Galgelogik();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galgespil);

        TextView besked = findViewById(R.id.Besked);
        TextView ord = findViewById(R.id.Ordet);

        besked.setText(R.string.Besked);
        ord.setText(galgelogik.getSynligtOrd());
    }

}
