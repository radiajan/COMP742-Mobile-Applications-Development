package com.cornell.air.a10ants.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cornell.air.a10ants.DAL.ChatMessageDAL;
import com.cornell.air.a10ants.DAL.PropertyDAL;
import com.cornell.air.a10ants.Model.ChatMessage;
import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.Model.UserProfile;
import com.cornell.air.a10ants.R;
import com.cornell.air.a10ants.View.PropertyDetails;
import com.cornell.air.a10ants.View.addProperty;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;
import hani.momanii.supernova_emoji_library.Helper.EmojiconTextView;

/**
 * Created by root on 8/05/17.
 */

public class FragmentChatLandlord extends Fragment{
    //Reference to the property database
    DatabaseReference databaseProperty;
    ListView lvChat;
    List<Property> listProperty;
    PropertyDAL propertyDAL;
    String email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.main_chat_landlord, container, false);

        //Get user email data
        if(FirebaseAuth.getInstance().getCurrentUser() != null)
            email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        //Find control
        databaseProperty = FirebaseDatabase.getInstance().getReference("properties");
        lvChat = (ListView) view.findViewById(R.id.lvChat);
        listProperty = new ArrayList<>();

        //Adds OnClick event
        lvChat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Property property = listProperty.get(position);

                //Intent intent = new Intent(view.getContext(), FragmentChatTenant.class);
                UserProfile.setPropertyId(property.getId());

                FragmentChatTenant c = new FragmentChatTenant();
                getFragmentManager().beginTransaction().replace(R.id.frame, c).commit();
                //intent.putExtra("propertyName", property.getName());

                //startActivity(intent);
            }
        });

        //Return View to be injected
        return view;
    }

    /**
     * Loads the list of the tenant and the landlord
     */
    @Override
    public void onStart()
    {
        super.onStart();

        //Instantiate properties
        propertyDAL = new PropertyDAL(getActivity(), lvChat, listProperty);

        //Fill the listview
        propertyDAL.listProperty(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }
}
