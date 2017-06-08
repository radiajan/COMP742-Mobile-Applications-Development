package com.cornell.air.a10ants.DAL;

import android.app.Activity;
import android.app.FragmentManager;
import android.util.Log;
import android.widget.ListView;

import com.cornell.air.a10ants.Fragments.FragmentReportTenant;
import com.cornell.air.a10ants.Model.Attach;
import com.cornell.air.a10ants.Model.ChatMessage;
import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.Model.AttachList;
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

public class AttachDAL {
    //Variables initialization
    DatabaseReference database;
    private FirebaseListAdapter<ChatMessage> adapter;
    String emailDAL;
    FragmentManager fm;
    List<Attach> listAttach;
    Activity activityReport;
    ListView listReportDisplay;

    public AttachDAL(){

    }

    public AttachDAL(String type){
        if(type == "receipt") {
            database = FirebaseDatabase.getInstance().getReference("report-receipts").child(UserProfile.getPropertyId().toString() + "-receipts");
        }else {
            database = FirebaseDatabase.getInstance().getReference("report-expenses").child(UserProfile.getPropertyId().toString() + "-expenses");
        }
    }

    public AttachDAL(String type, Activity activity, ListView listReportDisplay, List<Attach> listAttach){
        if(type == "receipt")
            database = FirebaseDatabase.getInstance().getReference("report-receipts").child(UserProfile.getPropertyId() + "-receipts");
        else
            database = FirebaseDatabase.getInstance().getReference("report-expenses").child(UserProfile.getPropertyId() + "-expenses");

        this.activityReport = activity;
        this.listReportDisplay = listReportDisplay;
        this.listAttach = listAttach;
    }

    /**
     * Creates the layout of the chat for the landlord
     * @param email email of the landlord
     * @param fmr Fragment Manager object
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
     * Save the report information in the database
     * @param attach object with the information of the attached file
     */
    public void addReport(Attach attach)
    {
        try{
            //Creates a unique id
            attach.setId(database.push().getKey());

            //Includes item in database
            database.child(attach.getId()).setValue(attach);
        }catch(Exception e){
            Log.e("Error: ",e.getMessage());
        }
    }

    /**
     * Return the list of properties
     * @param propertyId filter variable
     */
    public void listReport(String propertyId) {
        database.orderByChild("propertyId").equalTo(propertyId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Clears previous data
                listAttach.clear();

                //Add report to the list
                for (DataSnapshot reportSnapshot : dataSnapshot.getChildren()) {
                    Attach attach = reportSnapshot.getValue(Attach.class);
                    listAttach.add(attach);
                }

                AttachList adapter = new AttachList(activityReport, listAttach);
                listReportDisplay.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
