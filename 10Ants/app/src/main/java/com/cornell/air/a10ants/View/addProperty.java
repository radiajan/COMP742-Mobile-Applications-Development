package com.cornell.air.a10ants.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.cornell.air.a10ants.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by root on 8/05/17.
 */

public class addProperty extends AppCompatActivity implements ValueEventListener {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRootReference = firebaseDatabase.getReference();
    private DatabaseReference mName = mRootReference.child("name");
    private DatabaseReference mAddress = mRootReference.child("address");
    private DatabaseReference mDescription = mRootReference.child("description");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_property);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    @Override
    protected void onStart(){
        super.onStart();
        mName.addValueEventListener(this);
        mAddress.addValueEventListener(this);
        mDescription.addValueEventListener(this);
    }
}
