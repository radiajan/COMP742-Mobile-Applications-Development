package com.cornell.air.a10ants.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.cornell.air.a10ants.DAL.LoginDAL;
import com.cornell.air.a10ants.Menu.MenuFrame;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by adrian on 01/06/17.
 */

public class Login extends AppCompatActivity{

    //Instance variables
    private static int SIGN_IN_REQUEST_CODE = 1;
    LoginDAL loginDAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Instance of DAL object
        loginDAL = new LoginDAL();

        //Check user authentication
        handleAuthentication();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                //Loads user profile
                loginDAL.setUserProfile();

                Toast.makeText(this, "User signed in!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext() ,MenuFrame.class);
                finish();
                startActivity(intent);
            } else {
                // Something didn't work out. Let the user know and wait for them to sign in again
            }
        }
    }

    private void handleAuthentication() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            //User is signed in already. You're good to go!
            Toast.makeText(this, "User signed in!", Toast.LENGTH_SHORT).show();

            //Loads user profile
            loginDAL.setUserProfile();

            Intent intent = new Intent(getApplicationContext() ,MenuFrame.class);
            startActivity(intent);
        } else {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setProviders(AuthUI.GOOGLE_PROVIDER).build(),SIGN_IN_REQUEST_CODE);
        }
    }

    @Override
    public void onStop(){
        super.onStop();

        finish();
    }
}
