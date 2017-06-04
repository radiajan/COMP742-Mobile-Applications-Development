package com.cornell.air.a10ants.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cornell.air.a10ants.DAL.ExpenseDAL;
import com.cornell.air.a10ants.DAL.PropertyDAL;
import com.cornell.air.a10ants.Model.Expense;
import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by root on 8/05/17.
 */

public class addProperty extends AppCompatActivity{
    //Class instance
    PropertyDAL propertyDAL;
    Property property;

    //Instance controls
    EditText etName;
    EditText etAddress;
    EditText etDescription;
    Spinner spType;

    //Variable instance
    String propertyId;
    String propertyName;
    String propertyAddress;
    String propertyDescription;
    String propertyType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_property);

        //Load variables with intent data
        getIntentData();

        //Find controls in layout
        findControls();

        //Set values
        setControlData();
    }

    /**
     * Button event
     * @param view
     */
    public void addProperty(View view){
        //Set the values to the properties
        property = new Property();

        //Check if the user is editing the property
        if(propertyId != null)
            property.setId(propertyId);

        property.setName(((EditText)findViewById(R.id.etName)).getText().toString());
        property.setAddress(((EditText)findViewById(R.id.etAddress)).getText().toString());
        property.setDescription(((EditText)findViewById(R.id.etDescription)).getText().toString());
        property.setType(((Spinner)findViewById(R.id.spType)).getSelectedItem().toString());
        property.setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        //Create the instance of the DAO object
        propertyDAL = new PropertyDAL();

        //Variable to check if data was saved
        boolean isSaved;

        //Add or edit data
        if(propertyId == null) {
            //Add the property values
            isSaved = propertyDAL.addProperty(property);
        } else {
            //Edit the property values
            isSaved = propertyDAL.editProperty(property);
        }

        //Display message
        if(isSaved) {
            Toast.makeText(this, "Property saved successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(this, "Property was not saved", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Get the data from the intent object
     */
    private void getIntentData(){
        //Get intent object
        Intent intent = getIntent();
        propertyId = intent.getStringExtra("propertyId");
        propertyName = intent.getStringExtra("propertyName");
        propertyAddress = intent.getStringExtra("propertyAddress");
        propertyDescription = intent.getStringExtra("propertyDescription");
        propertyType = intent.getStringExtra("propertyType");
    }

    /**
     * Find the controls in the property layout
     */
    private void findControls(){
        etName = (EditText)findViewById(R.id.etName);
        etAddress = (EditText)findViewById(R.id.etAddress);
        etDescription = (EditText)findViewById(R.id.etDescription);
        spType = (Spinner)findViewById(R.id.spType);
    }

    /**
     * Set the data to the controls
     */
    private void setControlData(){
        etName.setText(propertyName);
        etAddress.setText(propertyAddress);
        etDescription.setText(propertyDescription);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.property_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spType.setAdapter(adapter);
        if (propertyType != null) {
            int spinnerPosition = adapter.getPosition(propertyType);
            spType.setSelection(spinnerPosition);
        }
    }
}
