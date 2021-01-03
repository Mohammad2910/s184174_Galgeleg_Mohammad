package com.example.s184174_galgeleg_mohammad.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import com.example.s184174_galgeleg_mohammad.logik_funktionalitet.Context;
import com.example.s184174_galgeleg_mohammad.R;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HovedMenuFrag extends Fragment implements View.OnClickListener {
    Button spilknap, leaderboardknap;
    View rod;
    private Context logik;
    private MainActivity main;
    private ProgressBar progressBar;
    Executor bgThread = Executors.newSingleThreadExecutor(); // håndtag til en baggrundstråd
    Handler uiThread = new Handler(Looper.getMainLooper());  // håndtag til forgrundstråden

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {

        rod = i.inflate(R.layout.frag_hovedmenu, container, false);

        // Da der gøres brug af state-pattern så skal vores UI bruge context-klassen til funktionaliteten
        // Men man kan kun have en context-klasse hvis man vil holde styr på states og derfor kalder jeg på MainActivity's context objekt.
        main = (MainActivity) getActivity();
        logik = main.getContext();

        spilknap = rod.findViewById(R.id.NytSpil);
        spilknap.setOnClickListener(this);

        leaderboardknap = rod.findViewById(R.id.Leaderboard);
        leaderboardknap.setOnClickListener(this);

        progressBar = rod.findViewById(R.id.progressBar);

        // Fået inspiration til følgende kode fra disse to sider:
        // https://developer.android.com/guide/navigation/navigation-custom-back  - For at kunne få styr på back knappen
        // https://www.geeksforgeeks.org/android-alert-dialog-box-and-how-to-create-it/ - For at vise en alert
        OnBackPressedCallback callback = new OnBackPressedCallback(true /*enabled by default*/) {
            @Override
            public void handleOnBackPressed() {
                // AlertDialog Builder class
                AlertDialog.Builder builder = new AlertDialog.Builder(main);
                // Set the message show for the Alert time
                builder.setMessage("Vil du forlade appen?");
                builder.setTitle("Advarsel!");
                // Set Cancelable false, for when the user clicks on the outside of the Dialog Box then it will remain show
                builder.setCancelable(false);

                builder.setPositiveButton("Ja",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });
                builder.setNegativeButton("Nej", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();

                // Show the Alert Dialog box
                alertDialog.show(); } };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        return rod;
    }


    @Override
    public void onClick(View v) {
        if (v == rod.findViewById(R.id.NytSpil)){
            spilknap.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(main, "Starter nyt spil", Toast.LENGTH_SHORT).show();
            // Starter spillet med ord fra regneark med sværhedsgrad 2 og skifter til spille fragment
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

//    private void progressBar(){
//        //Gør progressbaren synlig igen
//        progressBar.setVisibility(View.VISIBLE);
//
//        //Timer til progressbaren
//        final Timer t = new Timer();
//        TimerTask tt = new TimerTask() {
//            @Override
//            public void run() {
//                counter++;
//                progressBar.setProgress(counter);
//
//                if (counter == 100){
//                    t.cancel();
//                }
//            }
//        };
//        t.schedule(tt,0,100);
//    }

}
