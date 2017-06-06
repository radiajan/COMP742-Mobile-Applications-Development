package com.cornell.air.a10ants.DAL;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

import com.cornell.air.a10ants.Model.Expense;
import com.cornell.air.a10ants.Model.ExpenseList;
import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.Model.PropertyList;
import com.cornell.air.a10ants.View.PropertyDetails;
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

public class ExpenseDAL {
    //Reference to the expense database
    DatabaseReference database;
    Activity activity;
    ListView list;
    List<Expense> listExpense;

    public ExpenseDAL(String propertyId){
        //Load the data base
        database = FirebaseDatabase.getInstance().getReference("expenses");
    }

    public ExpenseDAL(String propertyId, Activity activity, ListView list, List<Expense> listExpense){
        //Load the data base
        database = FirebaseDatabase.getInstance().getReference("expenses");
        this.activity = activity;
        this.list = list;
        this.listExpense = listExpense;
    }

    /**
     * Edit the validations of the controls
     * @param expense
     */
    public boolean editExpense(Expense expense)
    {
        try{
            if(isFieldEmpty(expense))
            {
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
     * delete the expense
     * @param id
     */
    public void deleteExpense(String id){
        database.child(id).removeValue();
    }

    /**
     * list the expenses
     */
    public void listExpense(){
        //Fetch data for the expenses
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Clears previous data
                listExpense.clear();

                //Add property to the list
                for (DataSnapshot expenseSnapshot : dataSnapshot.getChildren()){
                    Expense expense = expenseSnapshot.getValue(Expense.class);
                    listExpense.add(expense);
                }

                ExpenseList adapter = new ExpenseList(activity, listExpense);
                list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Check if the field if empty
     * @param expense
     * @return validation
     */
    private boolean isFieldEmpty(Expense expense){
        if(!TextUtils.isEmpty(expense.getExpense()) &&
           !TextUtils.isEmpty(expense.getPaidOn()) &&
           !TextUtils.isEmpty(expense.getPaidTo()) &&
           !TextUtils.isEmpty(expense.getAmount()))
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
