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

public class AttachList extends ArrayAdapter<Attach>{
    private Activity context;
    private List<Attach> attachList;

    public AttachList(Activity context, List<Attach> attachList){
        super(context, R.layout.custom_list_expense, attachList);
        this.context = context;
        this.attachList = attachList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();

        View ListViewItem = inflater.inflate(R.layout.custom_list_tenant, null, true);

        TextView tvName = (TextView) ListViewItem.findViewById(R.id.tvName);

        Attach attach = attachList.get(position);

        tvName.setText(attach.getName());

        return ListViewItem;
    }
}
