package com.cornell.air.a10ants.DAL;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.cornell.air.a10ants.Menu.MenuFrame;
import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.Model.Tenant;
import com.cornell.air.a10ants.Model.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by massami on 5/06/2017.
 */

public class LoginDAL {
    //Instance of objects
    DatabaseReference databaseTenant;
    DatabaseReference databaseProperty;

    public LoginDAL(){
        databaseTenant = FirebaseDatabase.getInstance().getReference("tenants");
        databaseProperty = FirebaseDatabase.getInstance().getReference("properties");
    }


    /**
     * Loads user profile
     */
    public void setUserProfile() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            databaseTenant.orderByChild("email").equalTo(FirebaseAuth.getInstance().getCurrentUser().getEmail()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    //Set User profile for landlord
                    UserProfile.setUserEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                    UserProfile.setUserName(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                    UserProfile.setUserProfile("landlord");
                    UserProfile.setPropertyId("");
                    UserProfile.setPropertyName("");

                    //Join json property table
                    for (DataSnapshot tenantSnap : dataSnapshot.getChildren()) {
                        Tenant tenant = tenantSnap.getValue(Tenant.class);

                        //Set User profile for tenants
                        UserProfile.setUserEmail(tenant.getEmail());
                        UserProfile.setUserName(tenant.getName());
                        UserProfile.setPropertyId(tenant.getPropertyId());
                        UserProfile.setUserProfile("tenant");

                        databaseTenant.orderByChild("id").equalTo(UserProfile.getPropertyId()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot propertySnap : dataSnapshot.getChildren()) {
                                    Property property = propertySnap.getValue(Property.class);
                                    UserProfile.setPropertyName(property.getName());
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
