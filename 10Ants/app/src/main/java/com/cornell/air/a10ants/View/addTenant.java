package com.cornell.air.a10ants.View;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
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

    //Class instance
    Tenant tenant;
    TenantDAL tenantDAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_tenant);

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
        String propertyId = intent.getStringExtra("propertyId");

        //Set the values to the properties
        tenant = new Tenant();
        tenant.setName(((EditText) findViewById(R.id.etName)).getText().toString());
        tenant.setEmail(((EditText) findViewById(R.id.etEmail)).getText().toString());
        tenant.setPhone(Integer.parseInt(((EditText) findViewById(R.id.etPhone)).getText().toString()));
        tenant.setDateOfBirth(((EditText) findViewById(R.id.etDateofBirth)).getText().toString());
        tenant.setProperty(((Spinner)findViewById(R.id.spProperty)).getSelectedItem().toString());

        //Create the instance of the DAO object
        tenantDAL = new TenantDAL(propertyId);

        //Add the expense values
        boolean isSaved = tenantDAL.addTenant(tenant);

        //Display message
        if(isSaved) {
            Toast.makeText(this, "Tenant saved successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(this, "Tenant unsuccessfull", Toast.LENGTH_SHORT).show();
        }
    }
}
