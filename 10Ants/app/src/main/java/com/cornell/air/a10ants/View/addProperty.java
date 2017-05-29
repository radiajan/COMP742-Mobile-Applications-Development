package com.cornell.air.a10ants.View;

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

import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by root on 8/05/17.
 */

public class addProperty extends AppCompatActivity{

    //Reference to the property database
    DatabaseReference databaseProperty;

    //Reference of the controllers
    EditText etName;
    EditText etAddress;
    EditText etDescription;
    Spinner spType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_property);

        //Create instance of the property node
        databaseProperty = FirebaseDatabase.getInstance().getReference("properties");

        //Find controls reference in layout
        etName = (EditText) findViewById(R.id.etName);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etDescription = (EditText) findViewById(R.id.etDescription);
        spType  = (Spinner)findViewById(R.id.spType);
    }

    /**
     *
     * @param view
     */
    public void addProperty(View view){
        String name = etName.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String type = spType.getSelectedItem().toString();

        if(!TextUtils.isEmpty(name)){
            //Creates a unique id
            String id = databaseProperty.push().getKey();

            //Create a new property child object
            Property property = new Property(id, name, address, description, type);

            //Includes item in database
            databaseProperty.child(id).setValue(property);

            //Display message, included successfully
            Toast.makeText(this, "Property added", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();
        }
    }
}
