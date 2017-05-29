package com.cornell.air.a10ants.DAL;

import android.text.TextUtils;
import android.util.Log;

import com.cornell.air.a10ants.Model.Expense;
import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.Model.PropertyList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by massami on 29/05/2017.
 */

public class ExpenseDAL {
    //Reference to the expense database
    DatabaseReference database;

    public ExpenseDAL(String propertyId){
        //Load the data base
        database = FirebaseDatabase.getInstance().getReference("expenses").child(propertyId);
    }

    /**
     * Add the validations of the controls
     * @param expense
     */
    public boolean addExpense(Expense expense)
    {
        try{
            if(isFieldEmpty(expense))
            {
                //Creates a unique id
                expense.setId(database.push().getKey());

                //Includes item in database
                database.child(expense.getId()).setValue(expense);

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
    private boolean isFieldEmpty(Expense expense){
        if(!TextUtils.isEmpty(expense.getAmount()) &&
           !TextUtils.isEmpty(expense.getExpense()) &&
           !TextUtils.isEmpty(expense.getPaidOn()) &&
           !TextUtils.isEmpty(expense.getPayTo()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     *
     * TODO: edit and delete
     * Separate the expense VIEW and the expense class
     * Check the validation
     * add listing method based on property, tenant
     * Javadocs
     */
}
