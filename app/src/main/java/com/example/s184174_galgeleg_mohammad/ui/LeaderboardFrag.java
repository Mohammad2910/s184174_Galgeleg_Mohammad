package com.example.s184174_galgeleg_mohammad.ui;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.s184174_galgeleg_mohammad.logik_funktionalitet.Context;
import com.example.s184174_galgeleg_mohammad.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeaderboardFrag extends Fragment implements AdapterView.OnItemClickListener {
    private View rod;
    private Context logik;
    private MainActivity main;
    private ListView leaderboardliste;
    private ArrayList<String> listen;
    private Set<String> tom = new HashSet<String>();
    private ArrayAdapter leaderboardAdapter;


    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {

        rod = i.inflate(R.layout.frag_leaderboard, container, false);
        leaderboardliste = rod.findViewById(R.id.Leaderboard);

        main = (MainActivity) getActivity();
        logik = main.getContext();
        listen = new ArrayList<String>(logik.getPrefs().getStringSet("Score", tom));




        leaderboardAdapter = new ArrayAdapter(getActivity(), R.layout.frag_list_element, R.id.listElement, listen){

            @Override
            public View getView(int position, View cachedView, ViewGroup parent){
                View view = super.getView(position, cachedView, parent);
                TextView listView = view.findViewById(R.id.listElement);
                ImageView slet = view.findViewById(R.id.slet);
                return view;
            }
        };

        leaderboardliste.setAdapter(leaderboardAdapter);
        leaderboardliste.setOnItemClickListener(this);

        return rod;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(main);
        // Set the message show for the Alert time
        builder.setMessage("Vil du slette scoren?");
        builder.setTitle("Obs!");
        // Set Cancelable false, for when the user clicks on the outside of the Dialog Box then it will remain show
        builder.setCancelable(false);

        builder.setPositiveButton("Ja",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logik.RemoveFromList(position, listen);
                leaderboardAdapter.notifyDataSetChanged();

                // Opdaterer preference manager, den skal nok være i LeaderboardState inde i RemoveFromList metoden.
                Set <String> ny =  new HashSet<>(listen);
                logik.getPrefs().edit().putStringSet("Score", ny).apply();            }
        });
        builder.setNegativeButton("Nej", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();
    }
}
