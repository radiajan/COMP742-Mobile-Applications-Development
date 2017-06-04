package com.cornell.air.a10ants.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.cornell.air.a10ants.DAL.PropertyDAL;
import com.cornell.air.a10ants.Fragments.AboutFragment;
import com.cornell.air.a10ants.Fragments.ChatFragment;
import com.cornell.air.a10ants.Fragments.OverviewFragment;
import com.cornell.air.a10ants.Fragments.ReportFragment;
import com.cornell.air.a10ants.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

public class MenuFrame extends AppCompatActivity {
    //Google sign in
    private static int SIGN_IN_REQUEST_CODE = 1;
    FrameLayout container_menu;

    //FIREBASE instance variables

    //BOTTOMBAR instance variables
    BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        container_menu = (FrameLayout)findViewById(R.id.container_menu);
        handleAuthentication();
        setContentView(R.layout.container_menu);
        //Attach context to Bottombar
        mBottomBar = BottomBar.attach(this, savedInstanceState);

        //Create the BottomBar menu items
        setItemsFromMenu();

        //Adds badge to Bottombar menu item
        /*BottomBarBadge unread;
        unread = mBottomBar.makeBadgeForTabAt(2,"#FF0000",5);
        unread.show();*/
    }

    private void handleAuthentication() {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {

            //User is signed in already. You're good to go!
            Toast.makeText(this, "User signed in!", Toast.LENGTH_SHORT).show();

        } else {

            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setProviders(AuthUI.GOOGLE_PROVIDER).build(),SIGN_IN_REQUEST_CODE);

        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "User signed in!", Toast.LENGTH_SHORT).show();
            } else {
                // Something didn't work out. Let the user know and wait for them to sign in again
            }
        }
    }

    @Override
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login,menu);
        return super.onCreateOptionsMenu(menu);
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
}
