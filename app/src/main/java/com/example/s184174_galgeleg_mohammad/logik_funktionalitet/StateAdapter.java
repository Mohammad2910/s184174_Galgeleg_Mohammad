package com.example.s184174_galgeleg_mohammad.logik_funktionalitet;

import java.util.ArrayList;

public class StateAdapter implements IState {
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

    @Override
    public void RemoveFromList(int position, ArrayList<String> leaderboard) {
    }


}
