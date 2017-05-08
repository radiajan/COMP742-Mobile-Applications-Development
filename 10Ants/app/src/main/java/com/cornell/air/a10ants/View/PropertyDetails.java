package com.cornell.air.a10ants.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.cornell.air.a10ants.R;

/**
 * Created by root on 8/05/17.
 */

public class PropertyDetails extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_details);

        toolbar = (Toolbar)findViewById(R.id.tbrTenant);
        toolbar.setTitle("Tenants");

        //Set Title to toolbars
        startToolBar();
    }

    private void startToolBar(){
        //Set title to the TENANT toolbar
        Toolbar tbrTenant = (Toolbar) findViewById(R.id.tbrTenant);

        //setSupportActionBar(tbrTenant);
        //getSupportActionBar().setTitle("Tenants");
       /* tbrTenants.setSubtitle("This is a subtitle");
        tbrTenants.setLogo(R.drawable.ic_chat_black_24dp);*/

        //Set title to the EXPENSES toolbar
        /*Toolbar tbrExpenses = (Toolbar) findViewById(R.id.tbrExpenses);
        setSupportActionBar(tbrExpenses);
        tbrExpenses.setTitle("Expenses");*/
    }
}
