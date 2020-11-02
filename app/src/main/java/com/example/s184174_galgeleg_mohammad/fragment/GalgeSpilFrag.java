package com.example.s184174_galgeleg_mohammad.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.example.s184174_galgeleg_mohammad.Context;
import com.example.s184174_galgeleg_mohammad.MainActivity;
import com.example.s184174_galgeleg_mohammad.R;
import com.example.s184174_galgeleg_mohammad.states.TabtState;
import com.example.s184174_galgeleg_mohammad.states.VundetState;

public class GalgeSpilFrag extends Fragment implements View.OnClickListener {
    private Context logik;
    private MainActivity main;
    private GalgeBilled galgeBilled = new GalgeBilled();
    private TextView besked;
    private TextView ordet;
    private EditText input;



    View rod;
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {
        rod = i.inflate(R.layout.galgespil, container, false);
        getFragmentManager().beginTransaction().add(R.id.FrameLayout, galgeBilled).addToBackStack(null).commit();

        main = (MainActivity) getActivity();
        logik = main.getContext();

        besked = rod.findViewById(R.id.Besked);
        besked.setText(R.string.Besked);

        ordet = rod.findViewById(R.id.Ordet);
        ordet.setText(logik.getSynligtOrd());

        input = rod.findViewById(R.id.InputBogstav);
        Button gaet = rod.findViewById(R.id.Gæt);
        gaet.setOnClickListener(this);


        return rod;
    }

    public void onClick(View v) {
        if (v == rod.findViewById(R.id.Gæt)) {
            logik.gætBogstav(input.getText().toString());
            ordet.setText(logik.getSynligtOrd());
            input.setText("");
            galgeBilled.UpdatePicture(logik.getAntalForkerteBogstaver());
            if (logik.erSpilletVundet()) {
                ordet.setText(logik.getSynligtOrd());
                besked.setText(R.string.vundet);
                logik.setCurrentState(new VundetState());
                logik.onEnterState(logik);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getFragmentManager().beginTransaction().replace(R.id.MainFrameLayout, new VundetFrag()).addToBackStack(null).commit();
                    }
                }, 1000);
            } else if (logik.erSpilletTabt()) {
                ordet.setText(logik.getSynligtOrd());
                besked.setText(R.string.tabt);
                logik.setCurrentState(new TabtState());
                logik.onEnterState(logik);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getFragmentManager().beginTransaction().replace(R.id.MainFrameLayout, new TabtFrag()).addToBackStack(null).commit();
                    }
                }, 1000);
            }
        }
    }
}
