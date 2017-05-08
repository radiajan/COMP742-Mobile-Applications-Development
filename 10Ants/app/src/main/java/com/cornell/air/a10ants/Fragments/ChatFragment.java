package com.cornell.air.a10ants.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cornell.air.a10ants.R;

/**
 * Created by root on 8/05/17.
 */

public class ChatFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_chat, container, false);

        //Return View to be injected
        return view;
    }
}
