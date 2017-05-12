package com.cornell.air.a10ants.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.cornell.air.a10ants.R;

/**
 * Created by root on 8/05/17.
 */

public class PropertyDetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_details);

        Toolbar tbrTenant = (Toolbar) findViewById(R.id.tbrTenant); // Attaching the layout to the toolbar object
        setSupportActionBar(tbrTenant);

        Toolbar tbrExpense = (Toolbar) findViewById(R.id.tbrExpense); // Attaching the layout to the toolbar object
        setSupportActionBar(tbrExpense);

        //setupToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAdd:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void setupToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        // Hide the title
        getSupportActionBar().setTitle(null);

        // Set onClickListener to customView
        ImageView imgAdd = (ImageView) findViewById(R.id.menuAdd);
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
                Intent intent = new Intent(v.getContext(), Expense.class);
                startActivity(intent);
            }
        });
    }
}
