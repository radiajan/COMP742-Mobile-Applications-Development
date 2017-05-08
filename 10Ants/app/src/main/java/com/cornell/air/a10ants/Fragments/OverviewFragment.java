package com.cornell.air.a10ants.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.R;
import com.cornell.air.a10ants.View.PropertyDetails;

/**
 * Created by root on 7/05/17.
 */

public class OverviewFragment extends Fragment {

    int[] IMAGES = {R.drawable.images, R.drawable.images2, R.drawable.images3, R.drawable.images4};
    String[] NAMES = {"image1", "image2", "image3", "image4"};
    String[] DESCRIPTION = {"house", "house", "apartment", "apartment"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.overview, container, false);

        //Finds the control in the View
        ListView listViewProperty = (ListView) view.findViewById(R.id.listViewProperty);

        //Instance of Adapter class
        CustomAdapter customAdapter = new CustomAdapter();

        //Executes the adapter to create the list
        listViewProperty.setAdapter(customAdapter);

        //Adds OnClick event
        listViewProperty.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Property prop = new Property();
                Intent intent = new Intent(view.getContext(), PropertyDetails.class);

                prop.setName("Name");
                prop.setAddress("8 Airedale Street");
                prop.setDescription("This is a description");

                intent.putExtra("Property", prop);
                startActivity(intent);
            }
        });

        //Create tabs for the Overview
        CreateTab(view);

        //Return View to be injected
        return view;
    }

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
            view = getActivity().getLayoutInflater().inflate(R.layout.custom_list_view, null);

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