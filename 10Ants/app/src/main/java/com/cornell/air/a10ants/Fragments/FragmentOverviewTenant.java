package com.cornell.air.a10ants.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.cornell.air.a10ants.DAL.TenantDAL;
import com.cornell.air.a10ants.Model.Tenant;
import com.cornell.air.a10ants.Model.UserProfile;
import com.cornell.air.a10ants.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by massami on 5/06/2017.
 */

public class FragmentOverviewTenant extends Fragment{
    //Declare variables
    TenantDAL tenantDAL;
    ListView listViewTenant;
    List<Tenant> listTenant;
    View viewArg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_overview_tenant, container, false);
        viewArg = view;
        listViewTenant = (ListView)view.findViewById(R.id.listViewTenant);
        listTenant = new ArrayList<>();

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        tenantDAL = new TenantDAL();
        tenantDAL.getProperty(UserProfile.getUserEmail(), viewArg);

        //Instantiate properties
        tenantDAL = new TenantDAL(UserProfile.getPropertyId(),getActivity(), listViewTenant, listTenant);

        //Fill the listview
        tenantDAL.listTenant();
    }
}
