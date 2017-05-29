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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_tenant);

        //Get intent object
        Intent intent = getIntent();
        String propertyId = intent.getStringExtra("propertyId");

        //Create instance of the property node
        databaseTenants = FirebaseDatabase.getInstance().getReference("tenants").child(propertyId);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etDateofBirth = (EditText)findViewById(R.id.etDateofBirth);
        spProperty = (Spinner)findViewById(R.id.spProperty);
    }

    /**
     * Include Tenant in database
     * @param view
     */
    public void addTenant(View view){
        String name = etName.getText().toString().trim();
        String email = etName.getText().toString().trim();
        int phone =  Integer.parseInt(etPhone.getText().toString());
        String dateOfBirth = etDateofBirth.getText().toString().trim();
        String type = spProperty.getSelectedItem().toString();

        if(!TextUtils.isEmpty(name)){
            //Creates a unique id
            String id = databaseTenants.push().getKey();

            //Create a new property child object
            Tenant tenant = new Tenant(id, name, email, phone, dateOfBirth, type);

            //Includes item in database
            databaseTenants.child(id).setValue(tenant);

            //Display message, included successfully
            Toast.makeText(this, "Tenant added", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();
        }
    }
}
