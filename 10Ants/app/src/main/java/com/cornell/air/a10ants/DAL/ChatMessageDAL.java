package com.cornell.air.a10ants.DAL;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.cornell.air.a10ants.Fragments.FragmentChatLandlord;
import com.cornell.air.a10ants.Fragments.FragmentChatTenant;
import com.cornell.air.a10ants.Fragments.FragmentOverviewLandlord;
import com.cornell.air.a10ants.Fragments.FragmentOverviewTenant;
import com.cornell.air.a10ants.Model.ChatMessage;
import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.Model.UserProfile;
import com.cornell.air.a10ants.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import hani.momanii.supernova_emoji_library.Helper.EmojiconTextView;

/**
 * Created by massami on 30/05/2017.
 */

public class ChatMessageDAL {
    //Variables initialization
    DatabaseReference database;
    private FirebaseListAdapter<ChatMessage> adapter;
    String emailDAL;
    FragmentManager fm;

    public ChatMessageDAL(){
        //Load the data base
        database = FirebaseDatabase.getInstance().getReference("messages").child(UserProfile.getPropertyId().toString()+"-messages");
    }

    /**
     * Save message in database
     * @param text text to be saved
     */
    public void addMessage(String text){
        database.push().setValue(new ChatMessage(text, FirebaseAuth.getInstance().getCurrentUser().getDisplayName()));
    }

    /**
     * Creates the layout of the chat for the landlord
     * @param email email of the landlord
     * @param fmr Fragment Manager object
     */
    public void createChatLandlordLayout(String email, FragmentManager fmr)
    {
        //Set value to variables
        database = FirebaseDatabase.getInstance().getReference("tenants");
        fm = fmr;
        emailDAL = email;

        database.orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Add property to the list
                for (DataSnapshot propertySnapshot : dataSnapshot.getChildren()) {
                    Property property = propertySnapshot.getValue(Property.class);

                    //Its the tenant
                    if(property.getEmail().equals(emailDAL)) {
                        FragmentChatTenant c = new FragmentChatTenant();
                        fm.beginTransaction().replace(R.id.frame, c).commit();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
