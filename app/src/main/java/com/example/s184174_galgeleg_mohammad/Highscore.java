package com.example.s184174_galgeleg_mohammad;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Highscore extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);

        String[] a = {"a","nb", "d", "sup bitch"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.list_element, R.id.listElement, a){
            @Override
            public View getView(int position, View cachedView, ViewGroup parent ){
                View view = super.getView(position, cachedView, parent);
                TextView text = view.findViewById(R.id.listElement);

                return view;
            }
        };

        ListView listView = findViewById(R.id.Listview);
        listView.setAdapter(arrayAdapter);
    }

}
