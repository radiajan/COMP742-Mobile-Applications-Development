package com.cornell.air.a10ants.View;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cornell.air.a10ants.DAL.ExpenseDAL;
import com.cornell.air.a10ants.Model.Expense;
import com.cornell.air.a10ants.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by adrian on 06/06/17.
 */

public class addExpense extends AppCompatActivity {
    //Class instance
    ExpenseDAL expenseDAL;
    Expense expense;

    //Instance variables
    Calendar myCalendar = Calendar.getInstance();

    //Instance controls
    EditText etAmount;
    Spinner  spExpense;
    EditText etPaidTo;
    EditText etPaidOn;

    //Variable Instance
    String propertyId;
    String expenseId;
    String expenseAmount;
    String expenseExpense;
    String expensePaidTo;
    String expensePaidOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expenses);

        //Set the calendar object to the EditText control
        setCalendarEvent();

        //Get data from intent object
        getIntentData();

        //Find controls in layout
        findControls();

        //Set values
        setControlData();
    }

    /**
     * Shows the calendar android widget
     */
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
        String myFormat = "MM/dd/yy"; //In which you need put to here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etPaidOn.setText(sdf.format(myCalendar.getTime()));
    }

    /**
     * Add expense values
     * @param view
     */
    public void addExpense(View view){
        //Set the values to the properties
        expense = new Expense();

        //Check if the user is editing the property
        if(expenseId != null)
            expense.setId(expenseId);

        expense.setAmount(((EditText)findViewById(R.id.etAmount)).getText().toString());
        expense.setExpense(((Spinner)findViewById(R.id.spCategory)).getSelectedItem().toString());
        expense.setPaidTo(((EditText)findViewById(R.id.etPaidTo)).getText().toString());
        expense.setPaidOn(((EditText)findViewById(R.id.etPaidOn)).getText().toString());
        expense.setPropertyId(propertyId);

        //Create the instance of the DAO object
        expenseDAL = new ExpenseDAL();

        //Add the expense values
        boolean isSaved;

        //Add or edit expense
        if(expenseId == null)
            isSaved = expenseDAL.addExpense(expense);
        else
            isSaved = expenseDAL.editExpense(expense);

        //Display message
        if(isSaved) {
            Toast.makeText(this, "Expense saved successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(this, "Expense data not saved", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Set the calendar event to the EditText
     */
    private void setCalendarEvent(){
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

    /**
     * Get data from intent object
     */
    private void getIntentData(){
        //Get intent object
        Intent intent = getIntent();
        propertyId = intent.getStringExtra("propertyId");
        expenseId = intent.getStringExtra("expenseId");
        expenseAmount = intent.getStringExtra("expenseAmount");
        expenseExpense = intent.getStringExtra("expenseExpense");
        expensePaidTo = intent.getStringExtra("expensePaidTo");
        expensePaidOn = intent.getStringExtra("expensePaidOn");
    }

    /**
     * Find the controls in the expense layout
     */
    private void findControls(){
        etAmount = (EditText)findViewById(R.id.etAmount);
        spExpense = (Spinner)findViewById(R.id.spCategory);
        etPaidTo =(EditText)findViewById(R.id.etPaidTo);
        etPaidOn = (EditText)findViewById(R.id.etPaidOn);
    }

    /**
     * Set the data to the controls
     */
    private void setControlData(){
        etAmount.setText(""+expenseAmount);
        etPaidTo.setText(expensePaidTo);
        etPaidOn.setText(expensePaidOn);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.expense_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spExpense.setAdapter(adapter);
        if (expenseExpense!=null) {
            int spinnerPosition = adapter.getPosition(expenseExpense);
            spExpense.setSelection(spinnerPosition);
        }
    }
}
