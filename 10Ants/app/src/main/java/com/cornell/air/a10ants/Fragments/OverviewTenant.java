package com.cornell.air.a10ants.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cornell.air.a10ants.R;

/**
 * Created by massami on 5/06/2017.
 */

public class OverviewTenant extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_overview_tenant, container, false);
        return view;
    }
}
