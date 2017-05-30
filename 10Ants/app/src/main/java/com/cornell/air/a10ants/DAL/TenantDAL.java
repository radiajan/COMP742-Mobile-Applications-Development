package com.cornell.air.a10ants.DAL;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

import com.cornell.air.a10ants.Model.Expense;
import com.cornell.air.a10ants.Model.ExpenseList;
import com.cornell.air.a10ants.Model.Tenant;
import com.cornell.air.a10ants.Model.TenantList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

    public TenantDAL(String propertyId){
        //Load the data base
        database = FirebaseDatabase.getInstance().getReference("tenants").child(propertyId);
    }

    public TenantDAL(String propertyId, Activity activity, ListView list, List<Tenant> listTenant){
        //Load the data base
        database = FirebaseDatabase.getInstance().getReference("tenants").child(propertyId);
        this.activity = activity;
        this.list = list;
        this.listTenant = listTenant;
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
        database.addValueEventListener(new ValueEventListener() {
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

    /**
     * Check if the field if empty
     * @return validation
     */
    private boolean isFieldEmpty(Tenant tenant){
        if(!TextUtils.isEmpty(tenant.getName()) &&
                !TextUtils.isEmpty(tenant.getDateOfBirth()) &&
                !TextUtils.isEmpty(tenant.getEmail()) &&
                !TextUtils.isEmpty(tenant.getProperty()) &&
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
