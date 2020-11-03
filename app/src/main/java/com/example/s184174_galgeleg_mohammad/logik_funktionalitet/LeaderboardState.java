package com.example.s184174_galgeleg_mohammad.logik_funktionalitet;

import com.example.s184174_galgeleg_mohammad.Context;

import java.util.ArrayList;

public class LeaderboardState extends StateAdapter {

    @Override
    public void onEnterState(Context context) {

    }

    public void AddToList (String navn, int point, ArrayList<String> leaderboard) {
        Integer i = point;
        leaderboard.add(navn + ": " + i.toString());
    }

}
