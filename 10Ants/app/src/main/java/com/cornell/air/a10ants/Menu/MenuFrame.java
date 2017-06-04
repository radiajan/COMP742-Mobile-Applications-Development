package com.cornell.air.a10ants.Menu;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.cornell.air.a10ants.DAL.PropertyDAL;
import com.cornell.air.a10ants.Fragments.AboutFragment;
import com.cornell.air.a10ants.Fragments.ChatFragment;
import com.cornell.air.a10ants.Fragments.OverviewFragment;
import com.cornell.air.a10ants.Fragments.ReportFragment;
import com.cornell.air.a10ants.R;
import com.cornell.air.a10ants.View.Login;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.OnMenuTabClickListener;

public class MenuFrame extends AppCompatActivity {
    //Google sign in
    private static int SIGN_IN_REQUEST_CODE = 1;
    FrameLayout container_menu;

    //FIREBASE instance variables
    public static final String ANONYMOUS = "anonymous";
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private String mUsername;
    private String mPhotoUrl;

    //BOTTOMBAR instance variables
    BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_menu);

        container_menu = (FrameLayout)findViewById(R.id.container_menu);
        //Check if not sign-in then navigate Signin page
        if(FirebaseAuth.getInstance().getCurrentUser() == null)
        {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setProviders(AuthUI.GOOGLE_PROVIDER).build(),SIGN_IN_REQUEST_CODE);
        }
        else
        {
            Snackbar.make(container_menu,"Welcome "+FirebaseAuth.getInstance().getCurrentUser().getEmail(),Snackbar.LENGTH_SHORT).show();
        }

        //Initialize Auth
        //InitializeFirebaseAuthentication();

        //Attach context to Bottombar
        mBottomBar = BottomBar.attach(this, savedInstanceState);

        //Create the BottomBar menu items
        setItemsFromMenu();

        //Adds badge to Bottombar menu item
        /*BottomBarBadge unread;
        unread = mBottomBar.makeBadgeForTabAt(2,"#FF0000",5);
        unread.show();*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_sign_out)
        {
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Snackbar.make(container_menu,"You have been signed out.", Snackbar.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login,menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SIGN_IN_REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                Snackbar.make(container_menu,"Successfully signed in.Welcome!", Snackbar.LENGTH_SHORT).show();
            }
            else{
                Snackbar.make(container_menu,"We couldn't sign you in.Please try again later", Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the menu bottom bar state
        mBottomBar.onSaveInstanceState(outState);
    }

    /**
     * Create menu items in the Bottombar component
     */
    private void setItemsFromMenu(){
        mBottomBar.setItemsFromMenu(R.menu.menu_main, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                //Redirects to Overview menu
                if (menuItemId == R.id.menuOverview) {
                    OverviewFragment o = new OverviewFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, o).commit();

                    PropertyDAL propertyDAL = new PropertyDAL();
                    propertyDAL.createTenantLayout(FirebaseAuth.getInstance().getCurrentUser().getEmail(), getFragmentManager());
                }
                //Redirects to Report menu
                else if (menuItemId == R.id.menuReport) {
                    ReportFragment rep = new ReportFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, rep).commit();
                }
                //Redirects to Chat menu
                else if (menuItemId == R.id.menuChat) {
                    ChatFragment c = new ChatFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, c).commit();
                }
                //Redirects to About menu
                else if (menuItemId == R.id.menuAbout) {
                    AboutFragment a = new AboutFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, a).commit();
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.menuOverview) {
                    // The user reselected item number one, scroll your content to top.
                }
            }
        });
    }

    /**
     * Initialize fire base authentication components
     */
    private void InitializeFirebaseAuthentication(){
        mUsername = ANONYMOUS;
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if(mFirebaseUser == null)
        {
            //Not Signed in, launch the Sign In Activity
            startActivity(new Intent(this, Login.class));
            finish();
            return;
        }
        else{
            //Set name to variable
            mUsername = mFirebaseUser.getDisplayName();

            //Set photo to variable
            if(mFirebaseUser.getPhotoUrl() != null){
                mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
            }
            Log.i("User name: ", mUsername);
        }
    }
}
