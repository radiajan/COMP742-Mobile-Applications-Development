package com.cornell.air.a10ants.Menu;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cornell.air.a10ants.Fragments.AboutFragment;
import com.cornell.air.a10ants.Fragments.ChatFragment;
import com.cornell.air.a10ants.Fragments.OverviewFragment;
import com.cornell.air.a10ants.Fragments.ReportFragment;
import com.cornell.air.a10ants.R;
import com.cornell.air.a10ants.View.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.OnMenuTabClickListener;

public class MenuFrame extends AppCompatActivity {

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

        //Initialize Auth
        InitializeFirebaseAuthentication();

        //Attach context to Bottombar
        mBottomBar = BottomBar.attach(this, savedInstanceState);

        //Create the BottomBar menu items
        setItemsFromMenu();

        //Adds badge to Bottombar menu item
        BottomBarBadge unread;
        unread = mBottomBar.makeBadgeForTabAt(2,"#FF0000",5);
        unread.show();
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
                //Redirects to Reminder menu
                /*else if (menuItemId == R.id.menuReminder) {
                    ReminderFragment rem = new ReminderFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, rem).commit();
                }*/
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
