package com.cornell.air.a10ants.Model;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cornell.air.a10ants.R;

import java.util.List;

/**
 * Created by massami on 29/05/2017.
 */

public class PropertyList extends ArrayAdapter<Property> {

    private Activity context;
    private List<Property> propertyList;

    public PropertyList(Activity context, List<Property> propertyList){
        super(context, R.layout.custom_list_property, propertyList);
        this.context = context;
        this.propertyList = propertyList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();

        View ListViewItem = inflater.inflate(R.layout.custom_list_property, null, true);

        ImageView propImage = (ImageView) ListViewItem.findViewById(R.id.propImage);
        TextView tvName = (TextView) ListViewItem.findViewById(R.id.tvName);
        TextView tvDescription = (TextView) ListViewItem.findViewById(R.id.tvDescription);

        Property property = propertyList.get(position);

        tvName.setText(property.getName());
        tvDescription.setText(property.getDescription());

        return ListViewItem;
    }

}
