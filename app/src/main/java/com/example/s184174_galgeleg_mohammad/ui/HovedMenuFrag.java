package com.example.s184174_galgeleg_mohammad.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.s184174_galgeleg_mohammad.Context;
import com.example.s184174_galgeleg_mohammad.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HovedMenuFrag extends Fragment implements View.OnClickListener {
    Button spilknap, leaderboardknap;
    View rod;
    private Context logik;
    private MainActivity main;
    Executor bgThread = Executors.newSingleThreadExecutor(); // håndtag til en baggrundstråd
    Handler uiThread = new Handler(Looper.getMainLooper());  // håndtag til forgrundstråden

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {

        rod = i.inflate(R.layout.hovedmenufrag, container, false);
        main = (MainActivity) getActivity();
        logik = main.getContext();

        spilknap = rod.findViewById(R.id.NytSpil);
        spilknap.setOnClickListener(this);

        leaderboardknap = rod.findViewById(R.id.Leaderboard);
        leaderboardknap.setOnClickListener(this);

        return rod;
    }


    @Override
    public void onClick(View v) {
        if (v == rod.findViewById(R.id.NytSpil)){
            bgThread.execute(()->{
                logik.setCurrentState("SpilState");
                uiThread.post(()->{
                    logik.startNytSpil();
                    getFragmentManager().beginTransaction().replace(R.id.MainFrameLayout, new GalgeSpilFrag()).addToBackStack(null).commit();
                });
            });
        } else if (v == rod.findViewById(R.id.Leaderboard)) {
            logik.setCurrentState("leaderboardstate");
            getFragmentManager().beginTransaction().replace(R.id.MainFrameLayout, new LeaderboardFrag()).addToBackStack(null).commit();
        }
    }
}
