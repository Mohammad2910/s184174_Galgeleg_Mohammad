package com.example.s184174_galgeleg_mohammad.states;

import com.example.s184174_galgeleg_mohammad.Context;
import com.example.s184174_galgeleg_mohammad.State;

public class StartState implements State {
    @Override
    public void onClickgaet(Context context) {
            System.out.println("Dette er er Start-State (første state)");

            context.setState(this);
    }
}
