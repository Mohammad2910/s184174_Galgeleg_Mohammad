package com.example.s184174_galgeleg_mohammad.states;

import com.example.s184174_galgeleg_mohammad.Context;
import com.example.s184174_galgeleg_mohammad.State;

import java.util.ArrayList;

public class StateAdapter implements State {
    @Override
    public void onEnterState(Context context) {
    }

    @Override
    public ArrayList<String> getBrugteBogstaver() {
        return null;
    }

    @Override
    public String getSynligtOrd() {
        return null;
    }

    @Override
    public String getOrdet() {
        return null;
    }

    @Override
    public int getAntalForkerteBogstaver() {
        return 0;
    }

    @Override
    public boolean erSidsteBogstavKorrekt() {
        return false;
    }

    @Override
    public boolean erSpilletVundet() {
        return false;
    }

    @Override
    public boolean erSpilletTabt() {
        return false;
    }

    @Override
    public boolean erSpilletSlut() {
        return false;
    }

    @Override
    public void startNytSpil() {
    }

    @Override
    public void opdaterSynligtOrd() {
    }

    @Override
    public void gætBogstav(String bogstav) {
    }

    @Override
    public void logStatus() {
    }

    @Override
    public int getPoint(){
        return 0;
    }

    @Override
    public void AddToList (String navn, int point, ArrayList<String> leaderboard) {
    }


}
