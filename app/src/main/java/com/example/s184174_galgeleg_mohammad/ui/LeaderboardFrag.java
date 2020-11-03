package com.example.s184174_galgeleg_mohammad.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.s184174_galgeleg_mohammad.Context;
import com.example.s184174_galgeleg_mohammad.R;

public class LeaderboardFrag extends Fragment {
    View rod;
    private Context logik;
    private MainActivity main;
    ListView leaderboardliste;


    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {

        rod = i.inflate(R.layout.frag_leaderboard, container, false);
        leaderboardliste = rod.findViewById(R.id.Leaderboard);

        main = (MainActivity) getActivity();
        logik = main.getContext();


        ArrayAdapter leaderboardAdapter = new ArrayAdapter(getActivity(), R.layout.frag_list_element, R.id.listElement, logik.getLeaderboard()){

            @Override
            public View getView(int position, View cachedView, ViewGroup parent){
                View view = super.getView(position, cachedView, parent);
                TextView listView = view.findViewById(R.id.listElement);

                return view;
            }
        };

        leaderboardliste.setAdapter(leaderboardAdapter);

        return rod;
    }
}
