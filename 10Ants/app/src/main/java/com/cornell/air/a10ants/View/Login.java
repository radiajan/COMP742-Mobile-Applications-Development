package com.cornell.air.a10ants.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cornell.air.a10ants.Menu.MenuFrame;
import com.cornell.air.a10ants.R;

/**
 * Created by root on 11/05/17.
 */

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void loginGoogle(View view){
        Intent intent = new Intent(view.getContext(), MenuFrame.class);
        startActivity(intent);
    }


}
