package com.example.s184174_galgeleg_mohammad.states;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.s184174_galgeleg_mohammad.MainActivity;

public class Alert {

    public void AlertBesked(MainActivity main){// Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(main);

        // Set the message show for the Alert time
        builder.setMessage("Vil du afslutte spillet?");

        // Set Alert Title
        builder.setTitle("Advarsel!");

        // Set Cancelable false
        // for when the user clicks on the outside
        // the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name
        // OnClickListener method is use of
        // DialogInterface interface.

        builder.setPositiveButton("Ja",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        // Set the Negative button with No name
        // OnClickListener method is use
        // of DialogInterface interface.
        builder.setNegativeButton("Nej", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 // If user click no
                 // then dialog box is canceled.
                 dialog.cancel();
             }
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();}
}
