package com.cornell.air.a10ants.ReportAttachment;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

import com.cornell.air.a10ants.DAL.ReportDAL;
import com.cornell.air.a10ants.Model.Report;
import com.cornell.air.a10ants.Model.UserProfile;
import com.cornell.air.a10ants.R;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by ivy on 5/30/2017.
 */

public class ReportTenant extends AppCompatActivity {
    //Instance
    Report report;
    ReportDAL reportDAL;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_tenant);
    }

    /**
     * Add report
     * @param view
     */
    public void submitMessage(View view){
        //Set the values to the properties
        report = new Report();

        //Set the values to the report object
        report.setUserName(UserProfile.getUserName());
        report.setEmail(UserProfile.getUserEmail());
        report.setPropertyId(UserProfile.getPropertyId());
        report.setDateSent(DateFormat.getDateTimeInstance().format(new Date()));
        report.setTitle(((EditText)findViewById(R.id.etTitle)).getText().toString());
        report.setDescription(((EditText)findViewById(R.id.etDescription)).getText().toString());

        //Create the instance of the DAO object
        reportDAL = new ReportDAL();

        //Add report values
        boolean isSaved;
        isSaved = reportDAL.addReport(report);

        //Display message
        if(isSaved) {
            Toast.makeText(this, "Report sent successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(this, "Report not sent", Toast.LENGTH_SHORT).show();
        }
    }
}










