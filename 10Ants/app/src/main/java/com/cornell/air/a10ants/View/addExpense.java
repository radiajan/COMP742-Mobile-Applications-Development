package com.cornell.air.a10ants.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cornell.air.a10ants.DAL.ExpenseDAL;
import com.cornell.air.a10ants.Model.Expense;
import com.cornell.air.a10ants.R;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by root on 8/05/17.
 */

public class addExpense extends AppCompatActivity {
    //Class instance
    ExpenseDAL expenseDAL;
    Expense expense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expenses);
    }

    /**
     * Add expense values
     * @param view
     */
    public void addExpense(View view){
        //Get intent object
        Intent intent = getIntent();
        String propertyId = intent.getStringExtra("propertyId");

        //Set the values to the properties
        expense = new Expense();
        expense.setAmount(((EditText)findViewById(R.id.etAmount)).getText().toString());
        expense.setExpense(((Spinner)findViewById(R.id.spCategory)).getSelectedItem().toString());
        expense.setPayTo(((EditText)findViewById(R.id.etPayTo)).getText().toString());
        expense.setPaidOn(((EditText)findViewById(R.id.etPaidOn)).getText().toString());

        //Create the instance of the DAO object
        expenseDAL = new ExpenseDAL(propertyId);

        //Add the expense values
        boolean isSaved = expenseDAL.addExpense(expense);

        //Display message
        if(isSaved) {
            Toast.makeText(this, "Expense saved successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(this, "Expense unsuccessfull", Toast.LENGTH_SHORT).show();
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
