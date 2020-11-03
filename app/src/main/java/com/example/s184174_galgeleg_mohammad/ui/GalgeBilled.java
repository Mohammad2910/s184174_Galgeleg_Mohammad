package com.example.s184174_galgeleg_mohammad.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.s184174_galgeleg_mohammad.R;

public class GalgeBilled extends Fragment {
    private ImageView imageView;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {

        View rod = i.inflate(R.layout.galgebilled, container, false);
        imageView = rod.findViewById(R.id.FragmentImage);
        //imageView.setImageResource(R.drawable.galge);
        return rod;
    }


    public void UpdatePicture (int antalForkerteBogstaver) {
        switch (antalForkerteBogstaver) {
            case 1:
                imageView.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.forkert6);
                break;

        }
    }
}
