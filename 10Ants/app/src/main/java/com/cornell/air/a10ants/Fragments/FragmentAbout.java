package com.cornell.air.a10ants.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cornell.air.a10ants.R;

/**
 * Created by root on 8/05/17.
 */

public class FragmentAbout extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.f_about, container, false);

        TextView tvEmail = (TextView) view.findViewById(R.id.tvEmail);
        tvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"10antstester@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "10Ants App Feedback");
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Email"));
            }
        });

        //Return View to be injected
        return view;
    }
}
