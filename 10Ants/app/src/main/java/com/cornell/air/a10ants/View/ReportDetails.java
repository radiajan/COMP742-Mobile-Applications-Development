package com.cornell.air.a10ants.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.cornell.air.a10ants.DAL.ExpenseDAL;
import com.cornell.air.a10ants.DAL.TenantDAL;
import com.cornell.air.a10ants.R;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by massami on 7/06/2017.
 */

public class ReportDetails extends AppCompatActivity {
    //Instance
    String title;
    String description;
    TextView tvTitleDisplay;
    TextView tvDescriptionDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_detail);

        //Retrive data from Intent object
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        description = intent.getStringExtra("description");

        //Find controls
        tvTitleDisplay = (TextView) findViewById(R.id.tvTitleDisplay);
        tvDescriptionDisplay = (TextView) findViewById(R.id.tvDescriptionDisplay);

        //Set values to controls
        tvTitleDisplay.setText(title);
        tvDescriptionDisplay.setText(description);
    }
}
