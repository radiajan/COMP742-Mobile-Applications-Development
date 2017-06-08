package com.cornell.air.a10ants.DAL;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

import com.cornell.air.a10ants.Fragments.FragmentOverviewTenant;
import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.Model.PropertyList;
import com.cornell.air.a10ants.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by massami on 29/05/2017.
 */

public class PropertyDAL {
    //Variable initialization
    DatabaseReference database;
    List<Property> listProperty;
    Activity activityProperty;
    ListView listPropertyDisplay;
    String emailDAL;
    FragmentManager fm;


    public PropertyDAL(){
        //Create instance of the property node
        database = FirebaseDatabase.getInstance().getReference("properties");
    }

    public PropertyDAL(Activity activity, ListView listPropertyDisplay, List<Property> listProperty){
        database = FirebaseDatabase.getInstance().getReference("properties");
        this.activityProperty = activity;
        this.listPropertyDisplay = listPropertyDisplay;
        this.listProperty = listProperty;
    }

    /**
     * Add property information
     * @param property object to be included
     * @return condition
     */
    public boolean addProperty(Property property){
        try {
            if (isFieldEmpty(property))
            {
                //Creates a unique id
                property.setId(database.push().getKey());

                //Includes item in database
                database.child(property.getId()).setValue(property);

                //Display message, included successfully
                return true;
            } else {
                return false;
            }
        }catch(Exception e){
            Log.e("Error: ", e.getMessage());
        }

        return true;
    }

    /**
     * Edit property information
     * @param property object to be changed
     * @return condition
     */
    public boolean editProperty(Property property){
        try {
            if (isFieldEmpty(property))
            {
                //Edit item in database
                database.child(property.getId()).setValue(property);

                //Display message, included successfully
                return true;
            } else {
                return false;
            }
        }catch(Exception e){
            Log.e("Error: ", e.getMessage());
        }

        return true;
    }

    /**
     * List of available properties
     * @param email filter variable
     */
    public void listProperty(String email) {
        database.orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Clears previous data
                listProperty.clear();

                //Add property to the list
                for (DataSnapshot propertySnapshot : dataSnapshot.getChildren()) {
                    Property property = propertySnapshot.getValue(Property.class);
                    listProperty.add(property);
                }

                PropertyList adapter = new PropertyList(activityProperty, listProperty);
                listPropertyDisplay.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Delete property
     * @param id to be deleted
     */
    public void deleteProperty(String id){
        database.child(id).removeValue();
    }

    /**
     * Chek if the fields are empty
     * @param property object to be validated
     * @return condition
     */
    private boolean isFieldEmpty(Property property){
        if (!TextUtils.isEmpty(property.getName()) &&
            !TextUtils.isEmpty(property.getAddress()) &&
            !TextUtils.isEmpty(property.getDescription()) &&
            !TextUtils.isEmpty(property.getType()))
        {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Create the layout of the tenant
     * @param email email of the tenant
     * @param fmr Fragment Manager object
     */
    public void createTenantLayout(String email, FragmentManager fmr)
    {
        //Set value to variables
        database = FirebaseDatabase.getInstance().getReference("tenants");
        fm = fmr;
        emailDAL = email;

        database.orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Add property to the list
                for (DataSnapshot propertySnapshot : dataSnapshot.getChildren()) {
                    Property property = propertySnapshot.getValue(Property.class);

                    //Its the tenant
                    if(property.getEmail().equals(emailDAL)) {
                        FragmentOverviewTenant t = new FragmentOverviewTenant();
                        fm.beginTransaction().replace(R.id.frame, t).commit();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
