package com.cornell.air.a10ants.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.Model.PropertyList;
import com.cornell.air.a10ants.R;
import com.cornell.air.a10ants.View.PropertyDetails;
import com.cornell.air.a10ants.View.addProperty;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 7/05/17.
 */

public class OverviewFragment extends Fragment {

    int[] IMAGES = {R.drawable.images, R.drawable.images2, R.drawable.images3, R.drawable.images4};
    String[] NAMES = {"image1", "image2", "image3", "image4"};
    String[] DESCRIPTION = {"house", "house", "apartment", "apartment"};

    //Reference to the property database
    DatabaseReference databaseProperty;
    ListView listViewPropertyLandlord;
    List<Property> listProperty;

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        Button b = (Button) getView().findViewById(R.id.btnAddPropertyLandlord);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), addProperty.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.f_overview, container, false);

        //Find control
        databaseProperty = FirebaseDatabase.getInstance().getReference("properties");
        listViewPropertyLandlord = (ListView) view.findViewById(R.id.listViewPropertyLandlord);
        listProperty = new ArrayList<>();

        //Adds OnClick event
        listViewPropertyLandlord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Property property = listProperty.get(position);

                Intent intent = new Intent(view.getContext(), PropertyDetails.class);
                intent.putExtra("propertyId", property.getId());
                intent.putExtra("propertyName", property.getName());

                startActivity(intent);
            }
        });

        //Populate and add event to ListView to landlord
        startListViewTenant(view);

        //Create tabs for the Overview
        CreateTab(view);

        //Return View to be injected
        return view;
    }

    //Create Tab name
    private void CreateTab(View view){
        TabHost tabs = (TabHost)view.findViewById(R.id.tabOverview);
        tabs.setup();

        //Create a tab for Landlords
        TabHost.TabSpec landlordTab = tabs.newTabSpec("tabLandlord");
        landlordTab.setContent(R.id.tabLandlord);
        landlordTab.setIndicator("Landlord");
        tabs.addTab(landlordTab);

        //Create a tab for Tenants
        TabHost.TabSpec tenantTab = tabs.newTabSpec("tabTenant");
        tenantTab.setContent(R.id.tabTenant);
        tenantTab.setIndicator("Tenant");
        tabs.addTab(tenantTab);
    }

    //Start ListView Event to Landlords
    private void startListViewLandlord(View view, List<Property> listProperty)
    {
       /* //Finds the control in the View
        ListView listViewPropertyLandlord = (ListView) view.findViewById(R.id.listViewPropertyLandlord);

        //Adds OnClick event
        listViewPropertyLandlord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Property property = listProperty.get(position);

                Intent intent = new Intent(view.getContext(), PropertyDetails.class);

                startActivity(intent);
            }
        });*/
    }

    //Start ListView Event to Tenants
    private void startListViewTenant(View view)
    {
        //Finds the control in the View
        ListView listViewPropertyLandlord = (ListView) view.findViewById(R.id.listViewPropertyTenant);

        //Instance of Adapter class
        CustomAdapter customAdapter = new CustomAdapter();

        //Executes the adapter to create the list
        listViewPropertyLandlord.setAdapter(customAdapter);

        //Adds OnClick event
        listViewPropertyLandlord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Property prop = new Property();
                Intent intent = new Intent(view.getContext(), PropertyDetails.class);

                startActivity(intent);
            }
        });
    }

    /**
     * Loads the list of the tenant and the landlord
     */
    @Override
    public void onStart()
    {
        super.onStart();

        databaseProperty.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Clears previous data
                listProperty.clear();

                //Add property to the list
                for (DataSnapshot propertySnapshot : dataSnapshot.getChildren()){
                    Property property = propertySnapshot.getValue(Property.class);

                    listProperty.add(property);
                }

                PropertyList adapter = new PropertyList(getActivity(), listProperty);
                listViewPropertyLandlord.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAdd:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount(){
            return IMAGES.length;
        }

        @Override
        public Object getItem(int i){
            return null;
        }

        @Override
        public long getItemId(int i){
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup){
            view = getActivity().getLayoutInflater().inflate(R.layout.custom_list_property, null);

            ImageView propImage = (ImageView) view.findViewById(R.id.propImage);
            TextView tvName = (TextView) view.findViewById(R.id.tvName);
            TextView tvDescription = (TextView) view.findViewById(R.id.tvDescription);

            propImage.setImageResource(IMAGES[i]);
            tvName.setText(NAMES[i]);
            tvDescription.setText(DESCRIPTION[i]);

            return view;
        }
    }
}
