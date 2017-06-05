package com.cornell.air.a10ants.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.cornell.air.a10ants.DAL.ExpenseDAL;
import com.cornell.air.a10ants.DAL.PropertyDAL;
import com.cornell.air.a10ants.DAL.TenantDAL;
import com.cornell.air.a10ants.Model.Expense;
import com.cornell.air.a10ants.Model.ExpenseList;
import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.Model.PropertyList;
import com.cornell.air.a10ants.Model.Tenant;
import com.cornell.air.a10ants.Model.TenantList;
import com.cornell.air.a10ants.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 8/05/17.
 */

public class PropertyDetails extends AppCompatActivity {

    //Instance
    DatabaseReference databaseExpense;
    DatabaseReference databaseTenant;
    List<Expense> listExpense;
    List<Tenant> listTenant;
    ListView lvTenant;
    ListView lvExpense;
    Tenant tenant;
    Expense expense;
    TenantDAL tenantDAL;
    ExpenseDAL expenseDAL;
    String propertyId;
    String propertyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_details);

        //Retrive data from Intent object
        Intent intent = getIntent();
        propertyId = intent.getStringExtra("propertyId");
        propertyName = intent.getStringExtra("propertyName");

        //Get the database reference
        databaseExpense = FirebaseDatabase.getInstance().getReference("expenses").child(propertyId);
        databaseTenant = FirebaseDatabase.getInstance().getReference("tenants").child(propertyId);

        //Instantiate list
        listExpense = new ArrayList<>();
        listTenant = new ArrayList<>();

        //Create the event to redirect to ADD EXPENSE layout
        Button btnAddExpense = (Button) findViewById(R.id.btnAddExpense);
        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), addExpense.class);
                intent.putExtra("propertyId", propertyId);
                intent.putExtra("propertyName", propertyName);

                startActivity(intent);
            }
        });

        //Create the event to redirect to ADD TENANT layout
        Button btnAddTenant = (Button) findViewById(R.id.btnAddTenant);
        btnAddTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), addTenant.class);
                intent.putExtra("propertyId", propertyId);
                intent.putExtra("propertyName", propertyName);

                startActivity(intent);
            }
        });

        //Finds ListView in layout
        lvTenant = (ListView) findViewById(R.id.lvTenant);
        lvExpense = (ListView) findViewById(R.id.lvExpense);

        //Create popup menu
        lvTenant.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int pos, long id) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(PropertyDetails.this, lvTenant);

                //Get the selected property
                tenant = listTenant.get(pos);

                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        //Delete selected item
                        if(item.getTitle().equals("Delete")) {
                            tenantDAL = new TenantDAL();
                            tenantDAL.deleteTenant(tenant.getId());
                        }
                        else if (item.getTitle().equals("Edit")) {
                            Intent intent = new Intent(getBaseContext(), addTenant.class);
                            intent.putExtra("propertyId", propertyId);
                            intent.putExtra("tenantId", tenant.getId());
                            intent.putExtra("tenantName", tenant.getName());
                            intent.putExtra("tenantEmail", tenant.getEmail());
                            intent.putExtra("tenantPhone", tenant.getPhone());
                            intent.putExtra("tenantDateOfBirth", tenant.getDateOfBirth());

                            startActivity(intent);
                        }
                        return true;
                    }
                });
                popup.show();//showing popup menu
                return true;
            }
        });

        //Create popup menu
        lvExpense.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int pos, long id) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(PropertyDetails.this, lvExpense);

                //Get the selected property
                expense = listExpense.get(pos);

                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        //Delete selected item
                        if(item.getTitle().equals("Delete")) {
                            expenseDAL = new ExpenseDAL(propertyId);
                            expenseDAL.deleteExpense(expense.getId());
                        }
                        else if (item.getTitle().equals("Edit")) {
                            Intent intent = new Intent(getBaseContext(), addExpense.class);
                            intent.putExtra("propertyId", propertyId);
                            intent.putExtra("expenseId", expense.getId());
                            intent.putExtra("expenseAmount", expense.getAmount());
                            intent.putExtra("expenseExpense", expense.getExpense());
                            intent.putExtra("expensePaidOn", expense.getPaidOn());
                            intent.putExtra("expensePaidTo", expense.getPaidTo());

                            startActivity(intent);
                        }
                        return true;
                    }
                });
                popup.show();//showing popup menu
                return true;
            }
        });
    }

    /**
     * Loads the list of the tenant and the landlord
     */
    @Override
    public void onStart()
    {
        super.onStart();

        //Instantiate properties
        expenseDAL = new ExpenseDAL(propertyId,PropertyDetails.this, lvExpense, listExpense);
        //Fill the listview
        expenseDAL.listExpense();

        //Instantiate properties
        tenantDAL = new TenantDAL(propertyId,PropertyDetails.this, lvTenant, listTenant);
        //Fill the listview
        tenantDAL.listTenant();
    }
}
