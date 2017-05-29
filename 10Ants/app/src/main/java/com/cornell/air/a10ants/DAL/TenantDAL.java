package com.cornell.air.a10ants.DAL;

import android.text.TextUtils;
import android.util.Log;

import com.cornell.air.a10ants.Model.Expense;
import com.cornell.air.a10ants.Model.Tenant;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by massami on 29/05/2017.
 */

public class TenantDAL {
    //Reference to the expense database
    DatabaseReference database;

    public TenantDAL(String propertyId){
        //Load the data base
        database = FirebaseDatabase.getInstance().getReference("expenses").child(propertyId);
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
     * Check if the field if empty
     * @param expense
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
