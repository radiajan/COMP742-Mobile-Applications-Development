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

import com.cornell.air.a10ants.DAL.ExpenseDAL;
import com.cornell.air.a10ants.DAL.PropertyDAL;
import com.cornell.air.a10ants.Model.Expense;
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
    //Class instance
    PropertyDAL propertyDAL;
    Property property;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_property);
    }

    /**
     * Button event
     * @param view
     */
    public void addProperty(View view){
        //Set the values to the properties
        property = new Property();
        property.setName(((EditText)findViewById(R.id.etName)).getText().toString());
        property.setAddress(((EditText)findViewById(R.id.etAddress)).getText().toString());
        property.setDescription(((EditText)findViewById(R.id.etDescription)).getText().toString());
        property.setType(((Spinner)findViewById(R.id.spType)).getSelectedItem().toString());

        //Create the instance of the DAO object
        propertyDAL = new PropertyDAL();

        //Add the property values
        boolean isSaved = propertyDAL.addProperty(property);

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
