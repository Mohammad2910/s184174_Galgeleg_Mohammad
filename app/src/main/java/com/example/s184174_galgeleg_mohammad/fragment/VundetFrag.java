package com.example.s184174_galgeleg_mohammad.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.s184174_galgeleg_mohammad.Context;
import com.example.s184174_galgeleg_mohammad.MainActivity;
import com.example.s184174_galgeleg_mohammad.R;
import com.example.s184174_galgeleg_mohammad.states.HovedMenuState;
import com.example.s184174_galgeleg_mohammad.states.SpilState;
import com.example.s184174_galgeleg_mohammad.states.VundetState;

public class VundetFrag extends Fragment implements View.OnClickListener {
    View rod;
    private Context logik;
    private MainActivity main;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {

        rod = i.inflate(R.layout.vundet, container, false);

        main = (MainActivity) getActivity();
        logik = main.getContext();

        TextView textView = rod.findViewById(R.id.vundet);

        Button nytSpil = rod.findViewById(R.id.nytspil);
        nytSpil.setOnClickListener(this);

        Button hjem = rod.findViewById(R.id.hjem);
        hjem.setOnClickListener(this);

        return rod;
    }

    @Override
    public void onClick(View v) {
        if(v == rod.findViewById(R.id.nytspil)) {
            logik.setCurrentState("SpilState");
            getFragmentManager().beginTransaction().replace(R.id.MainFrameLayout, new GalgeSpilFrag()).commit();
        } else if ( v == rod.findViewById(R.id.hjem)) {
            logik.setCurrentState("HovedMenuState");
            getFragmentManager().beginTransaction().replace(R.id.MainFrameLayout, new HovedMenuFrag()).commit();
        }
    }
}
