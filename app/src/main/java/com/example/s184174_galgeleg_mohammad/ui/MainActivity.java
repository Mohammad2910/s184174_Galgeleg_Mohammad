package com.example.s184174_galgeleg_mohammad.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.s184174_galgeleg_mohammad.Context;
import com.example.s184174_galgeleg_mohammad.R;
import com.example.s184174_galgeleg_mohammad.ui.HovedMenuFrag;

public class MainActivity extends AppCompatActivity{
    private HovedMenuFrag hovedMenuFrag = new HovedMenuFrag();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = new Context(this);

        // Vi har en aktivitet som skifter fragmenter
        getSupportFragmentManager().beginTransaction().add(R.id.MainFrameLayout, hovedMenuFrag).addToBackStack(null).commit();
    }

    public Context getContext() {
        return context;
    }

}