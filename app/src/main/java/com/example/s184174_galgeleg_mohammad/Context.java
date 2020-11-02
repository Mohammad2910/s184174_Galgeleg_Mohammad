package com.example.s184174_galgeleg_mohammad;

import androidx.fragment.app.Fragment;

import com.example.s184174_galgeleg_mohammad.states.SpilState;

import java.util.ArrayList;

public class Context {


    private MainActivity ui;
    private State currentState;

    public Context(MainActivity UI) {
        ui = UI;
        currentState = new SpilState();
        currentState.onEnterState(this);
    }

    public void setCurrentState(State state){
        currentState = state;
        currentState.onEnterState(this);
    }

    public State getCurrentState(){
        return currentState;
    }

    public void onEnterState(Context context){
        currentState.onEnterState(context);
    }
    public ArrayList<String> getBrugteBogstaver(){return currentState.getBrugteBogstaver();}
    public String getSynligtOrd(){ return currentState.getSynligtOrd();}
    public String getOrdet(){return currentState.getOrdet();}
    public int getAntalForkerteBogstaver(){return currentState.getAntalForkerteBogstaver();}
    public boolean erSidsteBogstavKorrekt(){return currentState.erSidsteBogstavKorrekt();}
    public boolean erSpilletVundet(){return currentState.erSpilletVundet();}
    public boolean erSpilletTabt(){return currentState.erSpilletTabt();}
    public boolean erSpilletSlut(){return currentState.erSpilletSlut();}
    public void startNytSpil(){currentState.startNytSpil();}
    public void opdaterSynligtOrd(){currentState.opdaterSynligtOrd();}
    public void gætBogstav(String bogstav){currentState.gætBogstav(bogstav);}
    public void logStatus(){currentState.logStatus();}

}
