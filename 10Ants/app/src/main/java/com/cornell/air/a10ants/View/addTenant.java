package com.cornell.air.a10ants.View;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cornell.air.a10ants.DAL.TenantDAL;
import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.Model.Tenant;
import com.cornell.air.a10ants.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by root on 8/05/17.
 */

public class addTenant extends AppCompatActivity {
    //Reference to the property database
    DatabaseReference databaseTenants;

    //Instance variables
    Calendar myCalendar = Calendar.getInstance();

    //Reference of the controllers
    EditText etDateofBirth;
    EditText etName;
    EditText etPhone;
    EditText etEmail;

    //Class instance
    Tenant tenant;
    TenantDAL tenantDAL;

    //Variable instance
    String propertyId;
    String tenantId;
    String tenantName;
    String tenantEmail;
    int tenantPhone;
    String tenantDateOfBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_tenant);

        //Set the calendar object to the EditText control
        setCalendarEvent();

        //Get data from intent object
        getIntentData();

        //Find controls in layout
        findControls();

        //Set values
        setControlData();
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

        etDateofBirth.setText(sdf.format(myCalendar.getTime()));
    }

    /**
     * Include Tenant in database
     * @param view
     */
    public void addTenant(View view){
        //Get intent object
        Intent intent = getIntent();
        propertyId = intent.getStringExtra("propertyId");

        //Set the values to the properties
        tenant = new Tenant();

        //Check if the user is editing the property
        if(tenantId != null)
            tenant.setId(tenantId);

        tenant.setName(((EditText) findViewById(R.id.etName)).getText().toString());
        tenant.setEmail(((EditText) findViewById(R.id.etEmail)).getText().toString());
        tenant.setPhone(Integer.parseInt(((EditText) findViewById(R.id.etPhone)).getText().toString()));
        tenant.setDateOfBirth(((EditText) findViewById(R.id.etDateofBirth)).getText().toString());
        tenant.setPropertyId(propertyId);

        //Create the instance of the DAO object
        tenantDAL = new TenantDAL();

        //Add the expense values
        boolean isSaved;

        //Add or Edit tenant
        if(tenantId == null) {
            isSaved = tenantDAL.addTenant(tenant);
        }else{
            isSaved = tenantDAL.editTenant(tenant);
        }

        //Display message
        if(isSaved) {
            Toast.makeText(this, "Tenant saved successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(this, "Tenant data not saved", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Set the calendar event to the EditText
     */
    private void setCalendarEvent(){
        etDateofBirth = (EditText) findViewById(R.id.etDateofBirth);
        etDateofBirth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(addTenant.this, date, myCalendar
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
        tenantId = intent.getStringExtra("tenantId");
        tenantName = intent.getStringExtra("tenantName");
        tenantEmail = intent.getStringExtra("tenantEmail");
        tenantPhone = intent.getIntExtra("tenantPhone", 0);
        tenantDateOfBirth = intent.getStringExtra("tenantDateOfBirth");
    }

    /**
     * Find the controls in the tenant layout
     */
    private void findControls(){
        etName = (EditText)findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhone =(EditText)findViewById(R.id.etPhone);
        etDateofBirth = (EditText)findViewById(R.id.etDateofBirth);
    }

    /**
     * Set the data to the controls
     */
    private void setControlData(){
        etName.setText(tenantName);
        etEmail.setText(tenantEmail);
        etPhone.setText(""+tenantPhone);
        etDateofBirth.setText(tenantDateOfBirth);
    }
}
