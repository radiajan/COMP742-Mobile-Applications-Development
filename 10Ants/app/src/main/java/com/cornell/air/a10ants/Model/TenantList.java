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
 * Created by massami on 29/05/2017.
 */

public class TenantList extends ArrayAdapter<Tenant> {
    private Activity context;
    private List<Tenant> tenantList;

    public TenantList(Activity context, List<Tenant> tenantList){
        super(context, R.layout.custom_list_tenant, tenantList);
        this.context = context;
        this.tenantList = tenantList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();

        View ListViewItem = inflater.inflate(R.layout.custom_list_tenant, null, true);

        TextView tvName = (TextView) ListViewItem.findViewById(R.id.tvName);

        Tenant tenant = tenantList.get(position);

        tvName.setText(tenant.getName());

        return ListViewItem;
    }
}
