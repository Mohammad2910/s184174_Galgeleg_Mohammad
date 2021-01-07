package com.example.s184174_galgeleg_mohammad.logik_funktionalitet;

public class StateFactory {


    public IState getState(String stateType) {
        if (stateType == null) {
            return null;
        }

        if(stateType.equalsIgnoreCase("spilstate")) {
            return new SpilState();
        } else if (stateType.equalsIgnoreCase("hovedmenustate")) {
            return new HovedMenuState();
        } else if (stateType.equalsIgnoreCase("vundetstate")) {
            return new VundetState();
        } else if (stateType.equalsIgnoreCase("tabtstate")) {
            return new TabtState();
        } else if (stateType.equalsIgnoreCase("leaderboardstate")) {
            return new LeaderboardState();
        }

        return null;
    }
}
