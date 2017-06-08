package com.cornell.air.a10ants.DAL;

import android.app.Activity;
import android.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

import com.cornell.air.a10ants.Model.Attach;
import com.cornell.air.a10ants.Model.ChatMessage;
import com.cornell.air.a10ants.Model.Expense;
import com.cornell.air.a10ants.Model.ExpenseList;
import com.cornell.air.a10ants.Model.Report;
import com.cornell.air.a10ants.Model.ReportList;
import com.cornell.air.a10ants.Model.UserProfile;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by massami on 7/06/2017.
 */

public class ReportDAL {
    //Variable initialization
    DatabaseReference database;
    Activity activity;
    ListView list;
    List<Report> listReport;;

    public ReportDAL(){
        database = FirebaseDatabase.getInstance().getReference("report-issue");
    }

    public ReportDAL(Activity activity, ListView list, List<Report> listReport){
        //Load the data base
        database = FirebaseDatabase.getInstance().getReference("report-issue");
        this.activity = activity;
        this.list = list;
        this.listReport = listReport;
    }

    /**
     * Add the validations of the controls
     * @param report
     */
    public boolean addReport(Report report)
    {
        try{
            if(isFieldEmpty(report))
            {
                //Creates a unique id
                report.setId(database.push().getKey());

                //Includes item in database
                database.child(report.getId()).setValue(report);

                //Successfull
                return true;
            }
            else
            {
                //Empty field
                return false;
            }
        }catch(Exception e){
            Log.e("Error: ",e.getMessage());
        }

        return true;
    }

    /**
     * List of the availables reports
     * @param propertyId filter variable
     */
    public void listReport(String propertyId){
        //Fetch data for the expenses
        database.orderByChild("propertyId").equalTo(propertyId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Clears previous data
                listReport.clear();

                //Add property to the list
                for (DataSnapshot reportSnapshot : dataSnapshot.getChildren()){
                    Report report = reportSnapshot.getValue(Report.class);
                    listReport.add(report);
                }

                ReportList adapter = new ReportList(activity, listReport);
                list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Check if the field if empty
     * @param report object to be validated
     * @return validation
     */
    private boolean isFieldEmpty(Report report){
        if(!TextUtils.isEmpty(report.getTitle()) && !TextUtils.isEmpty(report.getDescription()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
