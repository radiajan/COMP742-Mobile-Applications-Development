package com.cornell.air.a10ants.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cornell.air.a10ants.DAL.ChatMessageDAL;
import com.cornell.air.a10ants.Model.ChatMessage;
import com.cornell.air.a10ants.Model.UserProfile;
import com.cornell.air.a10ants.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;
import hani.momanii.supernova_emoji_library.Helper.EmojiconTextView;

/**
 * Created by root on 8/05/17.
 */

public class FragmentChatTenant extends Fragment{
    private FirebaseListAdapter<ChatMessage> adapter;
    RelativeLayout activity_main;

    //Add Emojicon
    EmojiconEditText emojiconEditText;
    ImageView submitButton;

    //Class reference
    ChatMessageDAL chatMessageDAL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.main_chat_tenant, container, false);
        activity_main = (RelativeLayout)view.findViewById(R.id.activity_main);

        displayChatMessage(view);

        //Add Emoji
        submitButton = (ImageView)view.findViewById(R.id.submit_button);
        emojiconEditText = (EmojiconEditText)view.findViewById(R.id.emojicon_edit_text);
        /*emojIconActions = new EmojIconActions(getContext(),activity_main,emojiButton,emojiconEditText);
        emojIconActions.ShowEmojicon();*/
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chatMessageDAL = new ChatMessageDAL();
                chatMessageDAL.addMessage(emojiconEditText.getText().toString());
                emojiconEditText.setText("");
                emojiconEditText.requestFocus();
            }
        });

        //Return View to be injected
        return view;
    }

    /**
     * Load the list of messages in the layout
     * @param view contains the controls to be loaded
     */
    private void displayChatMessage(View view) {

        ListView listOfMessage = (ListView)view.findViewById(R.id.list_of_message);
        adapter = new FirebaseListAdapter<ChatMessage>(getActivity(), ChatMessage.class, R.layout.custom_list_chat, FirebaseDatabase.getInstance().getReference("messages").child(UserProfile.getPropertyId().toString()+"-messages"))
        {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {

                //Get references to the views of list_item.xml
                TextView messageText, messageUser, messageTime;
                messageText = (EmojiconTextView) v.findViewById(R.id.message_text);
                messageUser = (TextView) v.findViewById(R.id.message_user);
                messageTime = (TextView) v.findViewById(R.id.message_time);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessageTime()));

            }
        };
        listOfMessage.setAdapter(adapter);
        listOfMessage.setSelection(listOfMessage.getAdapter().getCount()-1);

    }
}
