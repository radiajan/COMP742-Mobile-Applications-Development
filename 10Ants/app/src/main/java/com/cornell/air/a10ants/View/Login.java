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

import com.cornell.air.a10ants.DAL.LoginDAL;
import com.cornell.air.a10ants.Menu.MenuFrame;
import com.cornell.air.a10ants.Model.Property;
import com.cornell.air.a10ants.Model.Tenant;
import com.cornell.air.a10ants.Model.UserProfile;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

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
