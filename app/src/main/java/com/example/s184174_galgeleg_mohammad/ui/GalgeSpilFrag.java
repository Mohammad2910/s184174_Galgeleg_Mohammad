package com.example.s184174_galgeleg_mohammad.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import com.example.s184174_galgeleg_mohammad.logik_funktionalitet.Context;
import com.example.s184174_galgeleg_mohammad.R;


public class GalgeSpilFrag extends Fragment implements View.OnClickListener {
    private Context logik;
    private MainActivity main;
    private GalgeBilled galgeBilled = new GalgeBilled();
    private TextView besked;
    private TextView ordet;
    private EditText inputBogstav;
    private TextView brugteBogstaverBesked;
    private TextView brugteBogstaver;


    View rod;
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {
        rod = i.inflate(R.layout.frag_galgespil, container, false);
        getFragmentManager().beginTransaction().add(R.id.FrameLayout, galgeBilled).addToBackStack(null).commit();

        // Begrundelse for forklaret i HovedMenuFrag
        main = (MainActivity) getActivity();
        logik = main.getContext();

        besked = rod.findViewById(R.id.Besked);
        besked.setText(R.string.Besked);

        ordet = rod.findViewById(R.id.Ordet);
        ordet.setText(logik.getSynligtOrd());

        brugteBogstaverBesked = rod.findViewById(R.id.lukkedeBogstaverBesked);
        brugteBogstaver = rod.findViewById(R.id.lukkedeBogstaver);
        brugteBogstaver.setText("");

        inputBogstav = rod.findViewById(R.id.InputBogstav);
        Button gaet = rod.findViewById(R.id.Gæt);
        gaet.setOnClickListener(this);

        // Fået inspiration til følgende kode fra disse to sider:
        // https://developer.android.com/guide/navigation/navigation-custom-back  - For at kunne få styr på back knappen
        // https://www.geeksforgeeks.org/android-alert-dialog-box-and-how-to-create-it/ - For at vise en alert
        OnBackPressedCallback callback = new OnBackPressedCallback(true /*enabled by default*/) {
            @Override
            public void handleOnBackPressed() {
                // AlertDialog Builder class
                AlertDialog.Builder builder = new AlertDialog.Builder(main);
                // Set the message show for the Alert time
                builder.setMessage("Vil du afslutte spillet?");
                builder.setTitle("Advarsel!");
                // Set Cancelable false, for when the user clicks on the outside of the Dialog Box then it will remain show
                builder.setCancelable(false);

                builder.setPositiveButton("Ja",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getFragmentManager().beginTransaction().replace(R.id.MainFrameLayout, new HovedMenuFrag()).addToBackStack(null).commit();
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

    public void onClick(View v) {
        if (v == rod.findViewById(R.id.Gæt)) {
            // Fortæller context (logik) til at gøre det funktionelle
            logik.gætBogstav(inputBogstav.getText().toString());
            // Opdatere UI (de næste 4 linjer)
            ordet.setText(logik.getSynligtOrd());
            brugteBogstaver.setText(logik.getBrugteBogstaver().toString());
            inputBogstav.setText("");
            galgeBilled.UpdatePicture(logik.getAntalForkerteBogstaver());

            if (logik.erSpilletVundet()) {
                besked.setText(R.string.vundet);
                // Gemmer data lokalt (via context) da antal forkerte bogstaver og antal point er
                // ikke gemt når vi skifter state
                logik.getPrefs().edit().putInt("Antal", logik.getAntalForkerteBogstaver()).apply();
                logik.getPrefs().edit().putInt("Point", logik.getPoint()).apply();
                logik.setCurrentState("vundetstate");
                // Synes det var irriterende at den hoppede direkte til vinderskærmen og synes
                // derfor at det gav mening med en pause for at kunne se det endelige ord
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getFragmentManager().beginTransaction().replace(R.id.MainFrameLayout, new VundetFrag()).addToBackStack(null).commit();
                    }
                }, 1500);
            } else if (logik.erSpilletTabt()) {
                besked.setText(R.string.tabt);
                logik.getPrefs().edit().putString("DetRigtigeOrd", logik.getOrdet()).apply();
                logik.setCurrentState("tabtstate");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getFragmentManager().beginTransaction().replace(R.id.MainFrameLayout, new TabtFrag()).addToBackStack(null).commit();
                    }
                }, 1500);
            }
        }
    }


}
