package com.cornell.air.a10ants.DAL;

import android.app.Activity;
import android.app.FragmentManager;
import android.util.Log;
import android.widget.ListView;

import com.cornell.air.a10ants.Fragments.FragmentChatTenant;
import com.cornell.air.a10ants.Fragments.FragmentReportLandlord;
import com.cornell.air.a10ants.Fragments.FragmentReportTenant;
import com.cornell.air.a10ants.Model.ChatMessage;
import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.Model.PropertyList;
import com.cornell.air.a10ants.Model.Report;
import com.cornell.air.a10ants.Model.ReportList;
import com.cornell.air.a10ants.Model.Tenant;
import com.cornell.air.a10ants.Model.UserProfile;
import com.cornell.air.a10ants.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by massami on 6/06/2017.
 */

public class ReportDAL {
    //Reference to the expense database
    DatabaseReference database;
    private FirebaseListAdapter<ChatMessage> adapter;
    String emailDAL;
    FragmentManager fm;
    List<Report> listReport;
    Activity activityReport;
    ListView listReportDisplay;

    public ReportDAL(){
        //Load the data base
        database = FirebaseDatabase.getInstance().getReference("receipts").child(UserProfile.getPropertyId().toString()+"-receipts");
    }

    public ReportDAL(Activity activity, ListView listReportDisplay, List<Report> listReport){
        database = FirebaseDatabase.getInstance().getReference("receipts").child(UserProfile.getPropertyId() + "-receipts");
        this.activityReport = activity;
        this.listReportDisplay = listReportDisplay;
        this.listReport = listReport;
    }

    /**
     * Creates the layout of the chat for the landlord
     * @param email
     * @param fmr
     */
    public void createReportTenantLayout(String email, FragmentManager fmr)
    {
        //Set value to variables
        database = FirebaseDatabase.getInstance().getReference("tenants");
        fm = fmr;
        emailDAL = email;

        database.orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Add property to the list
                for (DataSnapshot propertySnapshot : dataSnapshot.getChildren()) {
                    Property property = propertySnapshot.getValue(Property.class);

                    //Its the tenant
                    if(property.getEmail().equals(emailDAL)) {
                        FragmentReportTenant c = new FragmentReportTenant();
                        fm.beginTransaction().replace(R.id.frame, c).commit();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Add the validations of the controls
     * @param tenant
     */
    public void addReport(Report report)
    {
        try{
            //Creates a unique id
            report.setId(database.push().getKey());

            //Includes item in database
            database.child(report.getId()).setValue(report);
        }catch(Exception e){
            Log.e("Error: ",e.getMessage());
        }
    }

    /**
     * List the properties
     */
    public void listReceipt(String propertyId) {
        database.orderByChild("propertyId").equalTo(propertyId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Clears previous data
                listReport.clear();

                //Add report to the list
                for (DataSnapshot reportSnapshot : dataSnapshot.getChildren()) {
                    Report report = reportSnapshot.getValue(Report.class);
                    listReport.add(report);
                }

                ReportList adapter = new ReportList(activityReport, listReport);
                listReportDisplay.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
