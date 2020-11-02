package com.example.s184174_galgeleg_mohammad.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.s184174_galgeleg_mohammad.R;

public class VundetFrag extends Fragment {
    View rod;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {

        rod = i.inflate(R.layout.vundet, container, false);

        TextView textView = rod.findViewById(R.id.vundet);

        return rod;
    }
}
