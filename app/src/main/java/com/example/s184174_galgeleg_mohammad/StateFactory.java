package com.example.s184174_galgeleg_mohammad;

import com.example.s184174_galgeleg_mohammad.states.HovedMenuState;
import com.example.s184174_galgeleg_mohammad.states.SpilState;
import com.example.s184174_galgeleg_mohammad.states.TabtState;
import com.example.s184174_galgeleg_mohammad.states.VundetState;
import com.google.android.material.tabs.TabLayout;

public class StateFactory {


    public State getState(String stateType) {
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
        }

        return null;
    }
}
