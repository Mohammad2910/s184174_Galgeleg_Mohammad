package com.example.s184174_galgeleg_mohammad.logik_funktionalitet;

import com.example.s184174_galgeleg_mohammad.Context;

import java.util.ArrayList;

public interface State {
    void onEnterState(Context context);
    ArrayList<String> getBrugteBogstaver();
    String getSynligtOrd();
    String getOrdet();
    int getPoint();
    int getAntalForkerteBogstaver();
    boolean erSidsteBogstavKorrekt();
    boolean erSpilletVundet();
    boolean erSpilletTabt();
    boolean erSpilletSlut();
    void startNytSpil();
    void opdaterSynligtOrd();
    void gætBogstav(String bogstav);
    void logStatus();

    void AddToList (String navn, int point, ArrayList<String> leaderboard);
    /* static String hentUrl(String url) throws IOException {
        return null;
    }
    void hentOrdFraDr() throws Exception;
    void hentOrdFraRegneark(String sværhedsgrader) throws Exception;*/

}
