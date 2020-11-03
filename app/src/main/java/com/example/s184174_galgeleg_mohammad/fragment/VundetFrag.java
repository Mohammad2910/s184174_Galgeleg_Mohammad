package com.example.s184174_galgeleg_mohammad.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.s184174_galgeleg_mohammad.Context;
import com.example.s184174_galgeleg_mohammad.MainActivity;
import com.example.s184174_galgeleg_mohammad.R;
import com.example.s184174_galgeleg_mohammad.states.HovedMenuState;
import com.example.s184174_galgeleg_mohammad.states.SpilState;
import com.example.s184174_galgeleg_mohammad.states.VundetState;

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

        rod = i.inflate(R.layout.vundet, container, false);
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
            if (spillernavn.getText().toString().equals("")) {
                Toast.makeText(main, "Advarsel: Indtast et navn", Toast.LENGTH_SHORT).show();
            } else {
                logik.setCurrentState("leaderboardstate");
                logik.AddToList(spillernavn.getText().toString(), point, logik.getLeaderboard());
                Toast.makeText(main, "Gemt", Toast.LENGTH_SHORT).show();
                logik.setCurrentState("vundetstate");
            }
        }
    }
}
