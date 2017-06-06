package com.cornell.air.a10ants.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cornell.air.a10ants.Model.UserProfile;
import com.cornell.air.a10ants.R;
import com.cornell.air.a10ants.ReportAttachment.AttachExpense;
import com.cornell.air.a10ants.ReportAttachment.AttachReceipt;
import com.cornell.air.a10ants.ReportAttachment.ReportTenant;
import com.cornell.air.a10ants.View.ReportIssueLandlord;

/**
 * Created by root on 8/05/17.
 */

public class FragmentReportTenant extends Fragment {
    //Instance
    Intent intent;

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.f_report_tenant, container, false);

        Button btnReceipt = (Button) view.findViewById(R.id.btnReceipt);
        btnReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AttachReceipt.class);
                startActivity(intent);
            }
        });

        Button btnExpense = (Button) view.findViewById(R.id.btnExpense);
        btnExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AttachExpense.class);
                startActivity(intent);
            }
        });

        Button btnReport = (Button) view.findViewById(R.id.btnReport);
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //User is a tenant
                if(UserProfile.getUserProfile() == "tenant")
                    intent = new Intent(v.getContext(), ReportTenant.class);
                else
                    intent = new Intent(v.getContext(), ReportIssueLandlord.class);

                startActivity(intent);
            }
        });

        //Return View to be injected
        return view;
    }
}
