package com.cornell.air.a10ants.ReportAttachment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.cornell.air.a10ants.DAL.AttachDAL;
import com.cornell.air.a10ants.Model.Attach;
import com.cornell.air.a10ants.Model.UserProfile;
import com.cornell.air.a10ants.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ivy on 5/30/2017.
 */

public class AttachExpense extends AppCompatActivity{
    //Instance
    private static final int PICKFILE_RESULT_CODE = 1;
    private StorageReference storageReference;
    Uri filePath;
    AttachDAL attachDAL;
    Attach attach;
    ListView lvExpenses;
    List<Attach> listAttach;
    ImageView mImageView;
    boolean isDisplaying;
    Button buttonPick;
    String type;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attach_expenses);

        //Set storage reference
        storageReference = FirebaseStorage.getInstance().getReference();

        //Set the type of attach
        type = "expense";

        //Instance
        attachDAL = new AttachDAL(type);
        attach = new Attach();

        //Find the controls of the layout
        FindControls();

        //Create the events
        CreateEventControls();
    }

    /**
     * Loads the list of the tenant and the landlord
     */
    @Override
    public void onStart()
    {
        super.onStart();

        //Instantiate properties
        attachDAL = new AttachDAL(type, this, lvExpenses, listAttach);

        //Fill the listview
        attachDAL.listReport(UserProfile.getPropertyId());
    }

    /**
     * Custom event for back buttom
     */
    @Override
    public void onBackPressed() {
        if(isDisplaying)
        {
            mImageView.setImageResource(android.R.color.transparent);
            isDisplaying = false;
        }
        else
        {
            finish();
        }
    }

    /**
     * Save file to storage
     */
    private void uploadFile(){
        if(filePath != null) {
            //Progress bar
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");

            //Set current date
            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

            //Set the file reference
            StorageReference riversRef = storageReference.child(UserProfile.getPropertyId().toString() + "-expenses/" + currentDateTimeString + ".jpg");

            //Add attach info to database
            attach.setName(currentDateTimeString);
            attach.setPropertyId(UserProfile.getPropertyId());
            attachDAL.addReport(attach);

            //Upload file
            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "File uploaded", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            @SuppressWarnings("VisibleForTests")
                            double progress = (100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage(((int)progress) + "% Uploaded...");
                        }
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch(requestCode){
            case PICKFILE_RESULT_CODE:
                if(resultCode==RESULT_OK){
                    filePath = data.getData();
                    uploadFile();
                }
                break;

        }
    }

    /**
     * Display file
     * @param fileName
     */
    private void downloadFile(String fileName){
        final File localFile;
        final String nameFile = fileName;

        try {
            localFile = File.createTempFile("images", "jpg");
            FirebaseStorage.getInstance().getReferenceFromUrl("gs://ants-b4737.appspot.com/"+ UserProfile.getPropertyId() + "-expenses/").child(nameFile + ".jpg").getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>(){
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    mImageView.setImageBitmap(bitmap);
                    isDisplaying = true;
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.d("FAILURE",exception.getMessage());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the event of the controls of the layout
     */
    private void CreateEventControls(){
        //Set buttom event
        buttonPick.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,PICKFILE_RESULT_CODE);
            }});

        //Adds OnClick event
        lvExpenses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Attach attach = listAttach.get(position);
                downloadFile(attach.getName());
            }
        });
    }

    /**
     * Find the controls in layout
     */
    private void FindControls(){
        //Find controls in layout
        buttonPick = (Button)findViewById(R.id.buttonpick);
        mImageView = (ImageView)findViewById(R.id.image);
        lvExpenses = (ListView)findViewById(R.id.lvExpenses);
        listAttach = new ArrayList<>();
    }
}






