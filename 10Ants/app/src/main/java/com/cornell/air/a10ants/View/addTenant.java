package com.cornell.air.a10ants.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cornell.air.a10ants.DAL.TenantDAL;
import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.Model.Tenant;
import com.cornell.air.a10ants.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by root on 8/05/17.
 */

public class addTenant extends AppCompatActivity {
    //Reference to the property database
    DatabaseReference databaseTenants;

    //Reference of the controllers
    EditText etName;
    EditText etEmail;
    EditText etPhone;
    EditText etDateofBirth;
    Spinner spProperty;

    //Class instance
    Tenant tenant;
    TenantDAL tenantDAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_tenant);
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
