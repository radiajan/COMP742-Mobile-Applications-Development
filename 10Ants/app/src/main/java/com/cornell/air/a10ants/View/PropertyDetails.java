package com.cornell.air.a10ants.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cornell.air.a10ants.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 8/05/17.
 */

public class PropertyDetails extends AppCompatActivity {

    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_details);

        /*Toolbar tbrTenant = (Toolbar) findViewById(R.id.tbrTenant); // Attaching the layout to the toolbar object
        setSupportActionBar(tbrTenant);

        Toolbar tbrExpense = (Toolbar) findViewById(R.id.tbrExpense); // Attaching the layout to the toolbar object
        setSupportActionBar(tbrExpense);*/

        //setupToolbar();

        //Finds ListView in layout
        ListView lvTenant = (ListView) findViewById(R.id.lvTenant);
        ListView lvExpense = (ListView) findViewById(R.id.lvExpense);

        //Dummy values for name
        String[] tenant = new String[] { "Rodrigo", "Rafael", "Anna"};
        final ArrayList<String> listTenant = new ArrayList<String>();

        for (int i = 0; i < tenant.length; ++i) {
            listTenant.add(tenant[i]);
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, listTenant);
        lvTenant.setAdapter(adapter);


        //Dummy values for expenses
        String[] expense = new String[] { "Water/Gas $100", "Electricity $50", "Internet $85"};
        final ArrayList<String> listExpense = new ArrayList<String>();

        for (int i = 0; i < expense.length; ++i) {
            listExpense.add(expense[i]);
        }

        final ArrayAdapter adapterExpense = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listExpense);
        lvExpense.setAdapter(adapterExpense);

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

    /**
     * Adds a listener event to the toolbar
     */
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

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
