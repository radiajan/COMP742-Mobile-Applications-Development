package com.cornell.air.a10ants.Model;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cornell.air.a10ants.R;

import java.util.List;

/**
 * Created by massami on 6/06/2017.
 */

public class ReportList extends ArrayAdapter<Report>{
    private Activity context;
    private List<Report> reportList;

    public ReportList(Activity context, List<Report> reportList){
        super(context, R.layout.custom_list_expense, reportList);
        this.context = context;
        this.reportList = reportList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();

        View ListViewItem = inflater.inflate(R.layout.custom_list_tenant, null, true);

        TextView tvName = (TextView) ListViewItem.findViewById(R.id.tvName);

        Report report = reportList.get(position);

        tvName.setText(report.getName());

        return ListViewItem;
    }
}
