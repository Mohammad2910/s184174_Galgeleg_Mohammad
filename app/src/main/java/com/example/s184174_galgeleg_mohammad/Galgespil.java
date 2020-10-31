package com.example.s184174_galgeleg_mohammad;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Galgespil extends AppCompatActivity implements View.OnClickListener {
    private Galgelogik galgelogik = new Galgelogik();
    private EditText input;
    private TextView ord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galgespil);

        TextView besked = findViewById(R.id.Besked);

        ImageView galge = findViewById(R.id.Galge);
        Button gaet = findViewById(R.id.button);
        input = findViewById(R.id.InputBogstav);
        ord = findViewById(R.id.Ordet);


        besked.setText(R.string.Besked);
        ord.setText(galgelogik.getSynligtOrd());
        galge.setImageResource(R.drawable.galge);
        gaet.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        galgelogik.gætBogstav(input.getText().toString());
        ord.setText(galgelogik.getSynligtOrd());
        input.setText("");
    }
}
