package com.example.s184174_galgeleg_mohammad.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import com.example.s184174_galgeleg_mohammad.logik_funktionalitet.Context;
import com.example.s184174_galgeleg_mohammad.R;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class VundetFrag extends Fragment implements View.OnClickListener {
    View rod;
    private Context logik;
    private MainActivity main;
    EditText spillernavn;
    int point;
    int a = 0;
    Executor bgThread = Executors.newSingleThreadExecutor(); // håndtag til en baggrundstråd
    Handler uiThread = new Handler(Looper.getMainLooper());  // håndtag til forgrundstråden

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {

        rod = i.inflate(R.layout.frag_vundet, container, false);
        main = (MainActivity) getActivity();
        logik = main.getContext();

        TextView textView = rod.findViewById(R.id.vundet);

        int antal = logik.getPrefs().getInt("Antal", 10);
        TextView ordet = rod.findViewById(R.id.forkerte);
        ordet.setText("Antal forkerte: " + antal);

        point = logik.getPrefs().getInt("Point", -2);
        TextView pointText = rod.findViewById(R.id.point);
        pointText.setText("Antal Point: " + point);

        TextView scoreBesked = rod.findViewById(R.id.GemBesked);
        spillernavn = rod.findViewById(R.id.spillernavn);
        Button gemNavn = rod.findViewById(R.id.gemButton);
        gemNavn.setOnClickListener(this);

        Button nytSpil = rod.findViewById(R.id.nytspil);
        nytSpil.setOnClickListener(this);

        Button hjem = rod.findViewById(R.id.hjem);
        hjem.setOnClickListener(this);

        // Fået inspiration til følgende kode fra denne side:
        // https://developer.android.com/guide/navigation/navigation-custom-back  - For at kunne holde styr på back knappens funktion
        OnBackPressedCallback callback = new OnBackPressedCallback(true /*enabled by default*/) {
            @Override
            public void handleOnBackPressed() {
                Toast.makeText(main,"Går tilbage til hovedmenuen",Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getFragmentManager().beginTransaction().replace(R.id.MainFrameLayout, new HovedMenuFrag()).addToBackStack(null).commit();
                    }
                }, 1000);
            } };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        return rod;
    }

    @Override
    public void onClick(View v) {
        if(v == rod.findViewById(R.id.nytspil)) {
            bgThread.execute(()->{
                logik.setCurrentState("SpilState");
                uiThread.post(()->{
                    logik.startNytSpil();
                    Toast.makeText(main, "Starter nyt spil", Toast.LENGTH_SHORT).show();
                    getFragmentManager().beginTransaction().replace(R.id.MainFrameLayout, new GalgeSpilFrag()).addToBackStack(null).commit();
                });
            });
        } else if ( v == rod.findViewById(R.id.hjem)) {
            logik.setCurrentState("HovedMenuState");
            getFragmentManager().beginTransaction().replace(R.id.MainFrameLayout, new HovedMenuFrag()).commit();
        } else if (v == rod.findViewById(R.id.gemButton)) {
            // Laver et tjek for at der er skrevet mindst et bogstav under navn
            if (spillernavn.getText().toString().equals("")) {
                Toast.makeText(main, "Advarsel: Indtast et navn", Toast.LENGTH_SHORT).show();
            } else {
                // Skifter lige midlertidigt state, så jeg kan gemme navn og point med
                // leaderboardstate's metode på den globale leaderboard List (attribut i Context).
                logik.setCurrentState("leaderboardstate");
                logik.AddToList(spillernavn.getText().toString(), point, logik.getLeaderboard());
                Set <String> liste = new HashSet<>(logik.getLeaderboard());
                logik.getPrefs().edit().putStringSet("Score", liste).apply();
                Toast.makeText(main, "Gemt", Toast.LENGTH_SHORT).show();
                logik.setCurrentState("vundetstate");
            }
        }
    }
}
