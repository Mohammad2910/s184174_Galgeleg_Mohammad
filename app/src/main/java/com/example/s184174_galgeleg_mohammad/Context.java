package com.example.s184174_galgeleg_mohammad;

public class Context {

    private State state;


    public void Context(){
        state = null;
    }

    public void setState(State state){
        this.state = state;
    }

    public State getState(){
        return state;
    }
}
