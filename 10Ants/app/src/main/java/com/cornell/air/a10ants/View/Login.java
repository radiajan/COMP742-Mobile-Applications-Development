package com.cornell.air.a10ants.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cornell.air.a10ants.Menu.MenuFrame;
import com.cornell.air.a10ants.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login extends AppCompatActivity{

    //Instance variables
    private static int SIGN_IN_REQUEST_CODE = 1;
    FrameLayout container_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //container_menu = (FrameLayout)findViewById(R.id.container_menu);
        handleAuthentication();
        //setContentView(R.layout.container_menu);
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_sign_out)
        {
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getBaseContext(), "User signed out!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }*/

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login,menu);
        return super.onCreateOptionsMenu(menu);
        //return true;
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
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
            Intent intent = new Intent(getApplicationContext() ,MenuFrame.class);
            startActivity(intent);
        } else {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setProviders(AuthUI.GOOGLE_PROVIDER).build(),SIGN_IN_REQUEST_CODE);
        }
    }
}
