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
import android.widget.PopupMenu;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.cornell.air.a10ants.DAL.PropertyDAL;
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

    //Reference to the property database
    DatabaseReference databaseProperty;
    ListView listViewPropertyLandlord;
    List<Property> listProperty;
    Property property;

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

        //Create popup menu
        listViewPropertyLandlord.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int pos, long id) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(getActivity(), listViewPropertyLandlord);

                //Get the selected property
                property = listProperty.get(pos);

                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        //Delete selected item
                        if(item.getTitle().equals("Delete")) {
                            databaseProperty.child(property.getId()).removeValue();
                        }
                        return true;
                    }
                });
                popup.show();//showing popup menu
                return true;
            }
        });


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
                listProperty = new ArrayList<>();;

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
}
