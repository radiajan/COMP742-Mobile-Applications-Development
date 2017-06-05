package com.cornell.air.a10ants.DAL;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.cornell.air.a10ants.Model.Expense;
import com.cornell.air.a10ants.Model.ExpenseList;
import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.Model.PropertyList;
import com.cornell.air.a10ants.Model.Tenant;
import com.cornell.air.a10ants.Model.TenantList;
import com.cornell.air.a10ants.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by massami on 29/05/2017.
 */

public class TenantDAL {
    //Reference to the expense database
    DatabaseReference database;
    Activity activity;
    ListView list;
    List<Tenant> listTenant;
    List<Property> listProperty;
    Activity activityProperty;
    ListView listPropertyDisplay;
    String propertyId;
    View viewTenant;

    public TenantDAL(){
        //Load the data base
        database = FirebaseDatabase.getInstance().getReference("tenants");
    }

    public TenantDAL(String propertyId, Activity activity, ListView list, List<Tenant> listTenant){
        //Load the data base
        database = FirebaseDatabase.getInstance().getReference("tenants");
        this.activity = activity;
        this.list = list;
        this.listTenant = listTenant;
        this.propertyId = propertyId;
    }

    public TenantDAL(Activity activity, ListView listPropertyDisplay, List<Property> listProperty){
        database = FirebaseDatabase.getInstance().getReference("properties");
        this.activityProperty = activity;
        this.listPropertyDisplay = listPropertyDisplay;
        this.listProperty = listProperty;
    }

    /**
     * Add the validations of the controls
     * @param tenant
     */
    public boolean addTenant(Tenant tenant)
    {
        try{
            if(isFieldEmpty(tenant))
            {
                //Creates a unique id
                tenant.setId(database.push().getKey());

                //Includes item in database
                database.child(tenant.getId()).setValue(tenant);

                //Successfull
                return true;
            }
            else
            {
                //Empty field
                return false;
            }
        }catch(Exception e){
            Log.e("Error: ",e.getMessage());
        }

        return true;
    }

    /**
     * Add the validations of the controls
     * @param tenant
     */
    public boolean editTenant(Tenant tenant)
    {
        try{
            if(isFieldEmpty(tenant))
            {
                //Includes item in database
                database.child(tenant.getId()).setValue(tenant);

                //Successfull
                return true;
            }
            else
            {
                //Empty field
                return false;
            }
        }catch(Exception e){
            Log.e("Error: ",e.getMessage());
        }

        return true;
    }

    /**
     * Delete tenant
     * @param id
     */
    public void deleteTenant(String id){
        database.child(id).removeValue();
    }

    /**
     * list the expenses
     */
    public void listTenant(){
        //Fetch data for the expenses
        database.orderByChild("propertyId").equalTo(propertyId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Clears previous data
                listTenant.clear();

                //Add property to the list
                for (DataSnapshot expenseSnapshot : dataSnapshot.getChildren()){
                    Tenant tenant = expenseSnapshot.getValue(Tenant.class);
                    listTenant.add(tenant);
                }

                TenantList adapter = new TenantList(activity, listTenant);
                list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getProperty(String email,View view){
        //Set the view content
        viewTenant = view;

        database.orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot tenantSnap : dataSnapshot.getChildren()) {
                    Tenant tenant = tenantSnap.getValue(Tenant.class);

                    FirebaseDatabase.getInstance().getReference("properties").orderByKey().equalTo(tenant.getPropertyId()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot propertySnap : dataSnapshot.getChildren()) {
                                Property property = propertySnap.getValue(Property.class);
                                ((TextView) viewTenant.findViewById(R.id.tvOverviewName)).setText("Name: " + property.getName());
                                ((TextView) viewTenant.findViewById(R.id.tvOverviewDescription )).setText("Description: " + property.getDescription());
                                ((TextView) viewTenant.findViewById(R.id.tvOverviewAddress)).setText("Address: " + property.getAddress());
                                ((TextView) viewTenant.findViewById(R.id.tvOverviewEmail)).setText("Email: " + property.getEmail());
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

    /**
     * Check if the field if empty
     * @return validation
     */
    private boolean isFieldEmpty(Tenant tenant){
        if(!TextUtils.isEmpty(tenant.getName()) &&
                !TextUtils.isEmpty(tenant.getDateOfBirth()) &&
                !TextUtils.isEmpty(tenant.getEmail()) &&
                tenant.getPhone() != 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
