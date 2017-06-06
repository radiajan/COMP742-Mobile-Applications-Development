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

import com.cornell.air.a10ants.DAL.ExpenseDAL;
import com.cornell.air.a10ants.DAL.PropertyDAL;
import com.cornell.air.a10ants.DAL.ReportDAL;
import com.cornell.air.a10ants.DAL.TenantDAL;
import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.Model.Report;
import com.cornell.air.a10ants.Model.UserProfile;
import com.cornell.air.a10ants.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by massami on 7/06/2017.
 */

public class ReportIssueLandlord extends AppCompatActivity{
    ReportDAL reportDAL;
    Report report;
    ListView lvIssue;
    List<Report> listReport;
    String propertyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_issue);

        //Instantiate list
        listReport = new ArrayList<>();

        //Finds ListView in layout
        lvIssue = (ListView) findViewById(R.id.lvIssue);

        //Adds OnClick event
        lvIssue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Report report = listReport.get(position);

                Intent intent = new Intent(view.getContext(), ReportDetails.class);
                intent.putExtra("title", report.getTitle());
                intent.putExtra("description", report.getDescription());

                startActivity(intent);
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
        reportDAL = new ReportDAL(this, lvIssue, listReport);

        //Fill the listview
        reportDAL.listReport(UserProfile.getPropertyId());
    }
}
