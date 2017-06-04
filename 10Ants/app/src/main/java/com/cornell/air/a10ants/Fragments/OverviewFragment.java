package com.cornell.air.a10ants.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.cornell.air.a10ants.DAL.TenantDAL;
import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.Model.PropertyList;
import com.cornell.air.a10ants.Model.Tenant;
import com.cornell.air.a10ants.R;
import com.cornell.air.a10ants.View.PropertyDetails;
import com.cornell.air.a10ants.View.addProperty;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    ListView listViewPropertyTenant;
    List<Property> listProperty;
    Property property;
    PropertyDAL propertyDAL;
    Tenant tenant;
    TenantDAL tenantDAL;
    String email;

    //Instance variables
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    String personEmail;

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

        //Get user email data
        if(FirebaseAuth.getInstance().getCurrentUser() != null)
            email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

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
                            propertyDAL = new PropertyDAL();
                            propertyDAL.deleteProperty(property.getId());
                        }
                        //Edit selected object
                        else if(item.getTitle().equals("Edit")) {
                            Intent intent = new Intent(getActivity().getBaseContext(), addProperty.class);
                            intent.putExtra("propertyId", property.getId());
                            intent.putExtra("propertyName", property.getName());
                            intent.putExtra("propertyAddress", property.getAddress());
                            intent.putExtra("propertyDescription", property.getDescription());
                            intent.putExtra("propertyType", property.getType());

                            startActivity(intent);
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

        //Instantiate properties
        propertyDAL = new PropertyDAL(getActivity(), listViewPropertyLandlord, listProperty);

        //Fill the listview
        propertyDAL.listProperty();

        //Instantiate properties
        //tenantDAL = new TenantDAL(getActivity(), listViewPropertyTenant, listProperty);

        //Fill the listview
        /*if(email != null)
            tenantDAL.listTenantProperty(email);*/
    }
}
