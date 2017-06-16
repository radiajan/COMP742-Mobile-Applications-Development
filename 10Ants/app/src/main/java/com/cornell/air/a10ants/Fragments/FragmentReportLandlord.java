package com.cornell.air.a10ants.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cornell.air.a10ants.DAL.PropertyDAL;
import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.Model.UserProfile;
import com.cornell.air.a10ants.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by massami on 6/06/2017.
 */

public class FragmentReportLandlord extends Fragment {
    //Reference to the property database
    DatabaseReference databaseProperty;
    ListView lvReport;
    List<Property> listProperty;
    PropertyDAL propertyDAL;
    String email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.f_report_landlord, container, false);

        //Get user email data
        email = UserProfile.getUserEmail();

        //Find control
        databaseProperty = FirebaseDatabase.getInstance().getReference("properties");
        lvReport = (ListView) view.findViewById(R.id.lvReport);
        listProperty = new ArrayList<>();

        //Adds OnClick event
        lvReport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Property property = listProperty.get(position);

                //Intent intent = new Intent(view.getContext(), FragmentChatTenant.class);
                UserProfile.setPropertyId(property.getId());

                FragmentReportTenant c = new FragmentReportTenant();
                getFragmentManager().beginTransaction().replace(R.id.frame, c).commit();
            }
        });

        //Return View to be injected
        return view;
    }

    /**
     * Loads the list of the tenant and the landlord
     */
    @Override
    public void onStart()
    {
        super.onStart();

        //Instantiate properties
        propertyDAL = new PropertyDAL(getActivity(), lvReport, listProperty);

        //Fill the listview
        propertyDAL.listProperty(UserProfile.getUserEmail());
    }
}
