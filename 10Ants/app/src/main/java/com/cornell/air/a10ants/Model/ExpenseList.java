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

public class ExpenseList extends ArrayAdapter<Expense> {

    private Activity context;
    private List<Expense> expenseList;

    public ExpenseList(Activity context, List<Expense> expenseList){
        super(context, R.layout.custom_list_expense, expenseList);
        this.context = context;
        this.expenseList = expenseList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();

        View ListViewItem = inflater.inflate(R.layout.custom_list_expense, null, true);

        TextView tvName = (TextView) ListViewItem.findViewById(R.id.tvName);
        TextView tvDescription = (TextView) ListViewItem.findViewById(R.id.tvExpense);

        Expense expense = expenseList.get(position);

        tvName.setText(expense.getExpense());
        tvDescription.setText("$" + expense.getAmount());

        return ListViewItem;
    }
}
