package com.cornell.air.a10ants.View;

import android.content.EntityIterator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cornell.air.a10ants.Model.Expense;
import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by root on 8/05/17.
 */

public class addExpense extends AppCompatActivity {
    //Reference to the expense database
    DatabaseReference databaseExpense;

    //Reference of the controllers
    EditText etAmount;
    Spinner spCategory;
    EditText etPayTo;
    EditText etPaidOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expenses);

        //Get intent object
        Intent intent = getIntent();
        String propertyId = intent.getStringExtra("propertyId");

        //Create instance
        //
        // of the property node
        databaseExpense = FirebaseDatabase.getInstance().getReference("expenses").child(propertyId);

        etAmount = (EditText) findViewById(R.id.etAmount);
        spCategory = (Spinner) findViewById(R.id.spCategory);
        etPayTo = (EditText) findViewById(R.id.etPayTo);
        etPaidOn = (EditText)findViewById(R.id.etPaidOn);
    }

    /**
     * Add the validations of the controls
     * @param view
     */
    public void addExpense(View view){
        String amount = etAmount.getText().toString().trim();
        String payTo = etPayTo.getText().toString().trim();
        String paidOn = etPaidOn.getText().toString().trim();
        String exp = spCategory.getSelectedItem().toString();

        if(!TextUtils.isEmpty(amount)){
            //Creates a unique id
            String id = databaseExpense.push().getKey();

            //Create a new property child object
            Expense expense = new Expense(id, amount, exp, payTo, paidOn);

            //Includes item in database
            databaseExpense.child(id).setValue(expense);

            //Display message, included successfully
            Toast.makeText(this, "Expense added", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "You should enter a amount", Toast.LENGTH_LONG).show();
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
