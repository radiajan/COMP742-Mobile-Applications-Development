package com.cornell.air.a10ants.ReportAttachment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;

/**
 * Created by ivy on 6/6/2017.
 */



import com.cornell.air.a10ants.R;

import java.text.SimpleDateFormat;

public class ReceiptPhoto extends AppCompatActivity {

    Button btnTakePhoto;
    ImageView imgTakePhoto;
    TextView txtdate;
    private static final int CAM_REQUEST = 1313;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_details);

        btnTakePhoto = (Button) findViewById(R.id.button1);
        imgTakePhoto = (ImageView) findViewById(R.id.imageview1);
        btnTakePhoto.setOnClickListener(new btnTakePhotoClicker());
        txtdate = (TextView) findViewById(R.id.txtDate);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAM_REQUEST) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imgTakePhoto.setImageBitmap(thumbnail);
            long date = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyyy h:mm a");
            String dateString = sdf.format(date);
            txtdate.setText(dateString);


        }
    }

    class btnTakePhotoClicker implements Button.OnClickListener

    {

        @Override
        public void onClick(View v) {
            //TODO Auto-generated method stub
            Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraintent, CAM_REQUEST);



        }
    }
}


