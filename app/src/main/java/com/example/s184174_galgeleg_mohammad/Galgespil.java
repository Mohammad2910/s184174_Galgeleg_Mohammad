package com.example.s184174_galgeleg_mohammad;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Galgespil extends AppCompatActivity implements View.OnClickListener {
    private Galgelogik galgelogik = new Galgelogik();
    private GalgeBilled galgeBilled = new GalgeBilled();
    private TextView besked;
    private TextView ordet;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galgespil);

        getSupportFragmentManager().beginTransaction().add(R.id.FrameLayout, galgeBilled).addToBackStack(null).commit();

        besked = findViewById(R.id.Besked);
        besked.setText(R.string.Besked);

        ordet = findViewById(R.id.Ordet);
        ordet.setText(galgelogik.getSynligtOrd());

        input = findViewById(R.id.InputBogstav);
        Button gaet = findViewById(R.id.Gæt);
        gaet.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == findViewById(R.id.Gæt)) {
            galgelogik.gætBogstav(input.getText().toString());
            if (galgelogik.erSpilletVundet()) {
                ordet.setText(galgelogik.getSynligtOrd());
                besked.setText(R.string.vundet);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent i=new Intent(Galgespil.this,Resultat.class);
                        startActivity(i);
                    }
                }, 2000);
            }
            ordet.setText(galgelogik.getSynligtOrd());
            input.setText("");
            galgeBilled.UpdatePicture(galgelogik.getAntalForkerteBogstaver());
        }

    }
}
