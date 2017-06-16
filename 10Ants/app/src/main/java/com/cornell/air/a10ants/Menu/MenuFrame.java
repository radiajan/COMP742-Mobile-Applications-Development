package com.cornell.air.a10ants.Menu;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.cornell.air.a10ants.DAL.ChatMessageDAL;
import com.cornell.air.a10ants.DAL.PropertyDAL;
import com.cornell.air.a10ants.DAL.AttachDAL;
import com.cornell.air.a10ants.Fragments.FragmentAbout;
import com.cornell.air.a10ants.Fragments.FragmentChatLandlord;
import com.cornell.air.a10ants.Fragments.FragmentOverviewLandlord;
import com.cornell.air.a10ants.Fragments.FragmentReportLandlord;
import com.cornell.air.a10ants.Model.UserProfile;
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

    //BOTTOMBAR instance variables
    BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        container_menu = (FrameLayout)findViewById(R.id.container_menu);
        setContentView(R.layout.container_menu);

        //Attach context to Bottombar
        mBottomBar = BottomBar.attach(this, savedInstanceState);

        //Create the BottomBar menu items
        setItemsFromMenu();
    }

    /**
     * execute the option to signout
     * @param item menu item
     * @return if user logout
     */
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

    /**
     * Creates the menu items
     * @param menu objecto to ve inflated
     * @return create menu successful
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Save the state of the menu item
     * @param outState object to save the menu option
     */
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
                    FragmentOverviewLandlord o = new FragmentOverviewLandlord();
                    getFragmentManager().beginTransaction().replace(R.id.frame, o).commit();

                    PropertyDAL propertyDAL = new PropertyDAL();
                    propertyDAL.createTenantLayout(UserProfile.getUserEmail(), getFragmentManager());
                }
                //Redirects to Attach menu
                else if (menuItemId == R.id.menuReport) {
                    FragmentReportLandlord rep = new FragmentReportLandlord();
                    getFragmentManager().beginTransaction().replace(R.id.frame, rep).commit();

                    AttachDAL attachDAL = new AttachDAL();
                    attachDAL.createReportTenantLayout(UserProfile.getUserEmail(), getFragmentManager());
                }
                //Redirects to Chat menu
                else if (menuItemId == R.id.menuChat) {
                    FragmentChatLandlord c = new FragmentChatLandlord();
                    getFragmentManager().beginTransaction().replace(R.id.frame, c).commit();

                    ChatMessageDAL chatMessageDAL = new ChatMessageDAL();
                    chatMessageDAL.createChatLandlordLayout(UserProfile.getUserEmail(), getFragmentManager());
                }
                //Redirects to About menu
                else if (menuItemId == R.id.menuAbout) {
                    FragmentAbout a = new FragmentAbout();
                    getFragmentManager().beginTransaction().replace(R.id.frame, a).commit();
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.menuOverview) {
                    // The user reselected item number one, scroll your content to top.
                }
                else if (menuItemId == R.id.menuChat){
                    FragmentChatLandlord c = new FragmentChatLandlord();
                    getFragmentManager().beginTransaction().addToBackStack("landlordChat").replace(R.id.frame, c).commit();

                    ChatMessageDAL chatMessageDAL = new ChatMessageDAL();
                    chatMessageDAL.createChatLandlordLayout(UserProfile.getUserEmail(), getFragmentManager());
                }
                else if (menuItemId == R.id.menuReport){
                    FragmentReportLandlord rep = new FragmentReportLandlord();
                    getFragmentManager().beginTransaction().replace(R.id.frame, rep).commit();

                    AttachDAL attachDAL = new AttachDAL();
                    attachDAL.createReportTenantLayout(UserProfile.getUserEmail(), getFragmentManager());
                }
            }
        });
    }
}
