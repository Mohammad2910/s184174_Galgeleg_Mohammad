package com.example.s184174_galgeleg_mohammad.logik_funktionalitet;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.s184174_galgeleg_mohammad.ui.MainActivity;

import java.util.ArrayList;

public class Context {

    private StateFactory stateFactory = new StateFactory();
    private SharedPreferences prefs;
    private MainActivity ui;
    private State currentState;
    private ArrayList<String> leaderboard = new ArrayList<String>();

    public Context(MainActivity UI) {
        ui = UI;
        prefs = PreferenceManager.getDefaultSharedPreferences(UI);

        // Sætter nogle random score ind i listen, så leaderboard ikke er tom til at starte med.
        leaderboard.add("Hans: 12");
        leaderboard.add("John: 20");
        leaderboard.add("Hernandez: 15");
        leaderboard.add("Mohammad: 35");

        currentState = new HovedMenuState();
        currentState.onEnterState(this);
    }

    public void setCurrentState(String state){
        currentState = stateFactory.getState(state);
        currentState.onEnterState(this);
    }

    public State getCurrentState(){
        return currentState;
    }

    public SharedPreferences getPrefs () {
        return prefs;
    }

    public ArrayList<String> getLeaderboard () {
        return leaderboard;
    }

    public void onEnterState(Context context){
        currentState.onEnterState(context);
    }
    public ArrayList<String> getBrugteBogstaver(){return currentState.getBrugteBogstaver();}
    public String getSynligtOrd(){ return currentState.getSynligtOrd();}
    public String getOrdet(){return currentState.getOrdet();}
    public int getPoint(){ return currentState.getPoint();}
    public int getAntalForkerteBogstaver(){return currentState.getAntalForkerteBogstaver();}
    public boolean erSidsteBogstavKorrekt(){return currentState.erSidsteBogstavKorrekt();}
    public boolean erSpilletVundet(){return currentState.erSpilletVundet();}
    public boolean erSpilletTabt(){return currentState.erSpilletTabt();}
    public boolean erSpilletSlut(){return currentState.erSpilletSlut();}
    public void startNytSpil(){currentState.startNytSpil();}
    public void opdaterSynligtOrd(){currentState.opdaterSynligtOrd();}
    public void gætBogstav(String bogstav){currentState.gætBogstav(bogstav);}
    public void logStatus(){currentState.logStatus();}
    public void AddToList (String navn, int point, ArrayList<String> leaderboard) {currentState.AddToList(navn, point, leaderboard);}

}
