package com.vcalazas.formasgeomtricas.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vcalazas.formasgeomtricas.R;
import com.vcalazas.formasgeomtricas.interfaces.FragmentHosted;
import com.vcalazas.formasgeomtricas.interfaces.OnListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements FragmentHosted {

    private OnListener onListener;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void setListener(OnListener onListener) {
        HomeFragment.this.onListener = onListener;
    }
}
