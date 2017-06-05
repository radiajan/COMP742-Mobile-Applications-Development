package com.cornell.air.a10ants.DAL;

import android.app.Activity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.cornell.air.a10ants.Model.ChatMessage;
import com.cornell.air.a10ants.Model.UserProfile;
import com.cornell.air.a10ants.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import hani.momanii.supernova_emoji_library.Helper.EmojiconTextView;

/**
 * Created by massami on 30/05/2017.
 */

public class ChatMessageDAL {
    //Reference to the expense database
    DatabaseReference database;
    private FirebaseListAdapter<ChatMessage> adapter;

    public ChatMessageDAL(){
        //Load the data base
        database = FirebaseDatabase.getInstance().getReference("messages").child(UserProfile.getPropertyId().toString()+"-messages");
    }

    public ChatMessageDAL(String propertyId){
        //Load the data base
        database = FirebaseDatabase.getInstance().getReference("messages");
    }

    public void addMessage(String text){
        database.push().setValue(new ChatMessage(text, FirebaseAuth.getInstance().getCurrentUser().getDisplayName()));
    }
}
