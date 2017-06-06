package com.cornell.air.a10ants.ReportAttachment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import com.cornell.air.a10ants.R;


/**
 * Created by ivy on 6/6/2017.
 */





public class ReportText extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_text);
    }

    public void sendMessage(View v){

        EditText editMessage=(EditText)findViewById(R.id.chattxt);

        TextView textView = (TextView) findViewById(R.id.textView1);
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyyy h:mm a");
        String dateString = sdf.format(date);

        Button button = (Button)findViewById(R.id.sendbtn);


        //get text from edittext and convert it to string
        String messageString=editMessage.getText().toString();

        //set string from edittext to textview
        textView.setText(messageString);
        textView.append(" " + dateString);

        //clear edittext after sending text to message
        editMessage.setText("");

    }

}
