package com.example.s184174_galgeleg_mohammad.ui;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import com.example.s184174_galgeleg_mohammad.logik_funktionalitet.Context;
import com.example.s184174_galgeleg_mohammad.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TabtFrag extends Fragment implements View.OnClickListener {
    private View rod;
    private Context logik;
    private MainActivity main;
    private MediaPlayer mediaPlayer;
    private Executor bgThread = Executors.newSingleThreadExecutor(); // håndtag til en baggrundstråd
    private Handler uiThread = new Handler(Looper.getMainLooper());  // håndtag til forgrundstråden

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {

        rod = i.inflate(R.layout.frag_tabt, container, false);

        main = (MainActivity) getActivity();
        logik = main.getContext();
        mediaPlayer = MediaPlayer.create(main, R.raw.laugh);
        mediaPlayer.start();

        TextView textView = rod.findViewById(R.id.tabt);

        String ord = logik.getPrefs().getString("DetRigtigeOrd", "Ordet findes ikke :(");
        TextView ordet = rod.findViewById(R.id.OrdetTabt);
        ordet.setText("Ordet var: " + ord);

        Button nytSpil = rod.findViewById(R.id.nytspiltabt);
        nytSpil.setOnClickListener(this);

        Button hjem = rod.findViewById(R.id.hjemtabt);
        hjem.setOnClickListener(this);

        // Fået inspiration til følgende kode fra denne side:
        // https://developer.android.com/guide/navigation/navigation-custom-back  - For at kunne holde styr på back knappen
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
        mediaPlayer.stop();
        if(v == rod.findViewById(R.id.nytspiltabt)) {
            bgThread.execute(()->{
                logik.setCurrentState("SpilState");
                uiThread.post(()->{
                    logik.startNytSpil();
                    Toast.makeText(main, "Starter nyt spil", Toast.LENGTH_SHORT).show();
                    getFragmentManager().beginTransaction().replace(R.id.MainFrameLayout, new GalgeSpilFrag()).addToBackStack(null).commit();
                });
            });
        } else if ( v == rod.findViewById(R.id.hjemtabt)) {
            logik.setCurrentState("HovedMenuState");
            getFragmentManager().beginTransaction().replace(R.id.MainFrameLayout, new HovedMenuFrag()).commit();
        }
    }
}
