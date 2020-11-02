package com.example.s184174_galgeleg_mohammad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.s184174_galgeleg_mohammad.fragment.HovedMenuFrag;

public class MainActivity extends AppCompatActivity{
    private HovedMenuFrag hovedMenuFrag = new HovedMenuFrag();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = new Context(this);

        getSupportFragmentManager().beginTransaction().add(R.id.MainFrameLayout, hovedMenuFrag).addToBackStack(null).commit();
    }

    public Context getContext() {
        return context;
    }

}