package com.cornell.air.a10ants.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cornell.air.a10ants.DAL.PropertyDAL;
import com.cornell.air.a10ants.DAL.TenantDAL;
import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by massami on 5/06/2017.
 */

public class OverviewTenant extends Fragment{
    //Declare variables
    TenantDAL tenantDAL;
    View viewArg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_overview_tenant, container, false);
        viewArg = view;

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();

        //Instantiate properties
        //propertyDAL = new PropertyDAL(getActivity(), listViewPropertyLandlord, listProperty);

        //Fill the listview
        //propertyDAL.listProperty(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        tenantDAL = new TenantDAL();
        tenantDAL.getProperty(FirebaseAuth.getInstance().getCurrentUser().getEmail(), viewArg);

    }

}
