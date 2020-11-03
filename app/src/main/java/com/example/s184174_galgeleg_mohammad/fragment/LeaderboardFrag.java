package com.example.s184174_galgeleg_mohammad.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.s184174_galgeleg_mohammad.Context;
import com.example.s184174_galgeleg_mohammad.MainActivity;
import com.example.s184174_galgeleg_mohammad.R;

import java.util.ArrayList;

public class LeaderboardFrag extends Fragment {
    View rod;
    private Context logik;
    private MainActivity main;
    ListView leaderboardliste;


    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {

        rod = i.inflate(R.layout.leaderboard, container, false);
        leaderboardliste = rod.findViewById(R.id.Leaderboard);

        main = (MainActivity) getActivity();
        logik = main.getContext();


        ArrayList<String> a = main.getList();

        ArrayAdapter leaderboardAdapter = new ArrayAdapter(getActivity(), R.layout.list_element, R.id.listElement, a){

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
