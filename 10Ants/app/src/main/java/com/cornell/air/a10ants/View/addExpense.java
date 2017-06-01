package com.cornell.air.a10ants.View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cornell.air.a10ants.DAL.ExpenseDAL;
import com.cornell.air.a10ants.Model.Expense;
import com.cornell.air.a10ants.R;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by root on 8/05/17.
 */

public class addExpense extends AppCompatActivity {
    //Class instance
    ExpenseDAL expenseDAL;
    Expense expense;

    //Instance variables
    Calendar myCalendar = Calendar.getInstance();

    //Instance controls
    EditText etPaidOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expenses);

        etPaidOn = (EditText) findViewById(R.id.etPaidOn);
        etPaidOn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(addExpense.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etPaidOn.setText(sdf.format(myCalendar.getTime()));
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
}
