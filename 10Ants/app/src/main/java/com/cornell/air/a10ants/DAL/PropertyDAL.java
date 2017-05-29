package com.cornell.air.a10ants.DAL;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.Model.PropertyList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by massami on 29/05/2017.
 */

public class PropertyDAL {
    //Instance
    DatabaseReference database;
    List<Property> listProperty;
    Activity activityProperty;

    public PropertyDAL(){
        //Create instance of the property node
        database = FirebaseDatabase.getInstance().getReference("properties");
    }

    public PropertyDAL(Activity activity){
        database = FirebaseDatabase.getInstance().getReference("properties");
        this.activityProperty = activity;
    }

    /**
     * Add property data
     * @param property
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
     * Chek if the fields are empty
     * @param property
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
}
