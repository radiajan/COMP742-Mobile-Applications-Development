package com.cornell.air.a10ants.Menu;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cornell.air.a10ants.Fragments.AboutFragment;
import com.cornell.air.a10ants.Fragments.ChatFragment;
import com.cornell.air.a10ants.Fragments.OverviewFragment;
import com.cornell.air.a10ants.Fragments.ReminderFragment;
import com.cornell.air.a10ants.Fragments.ReportFragment;
import com.cornell.air.a10ants.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.OnMenuTabClickListener;

public class MainActivity extends AppCompatActivity {

    BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomBar = BottomBar.attach(this, savedInstanceState);

        mBottomBar.setItemsFromMenu(R.menu.menu_main, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                //Redirects to Overview menu
                if (menuItemId == R.id.menuOverview) {
                    OverviewFragment o = new OverviewFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, o).commit();
                }
                //Redirects to About menu
                else if (menuItemId == R.id.menuAbout) {
                    AboutFragment a = new AboutFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, a).commit();
                }
                //Redirects to Chat menu
                else if (menuItemId == R.id.menuChat) {
                    ChatFragment c = new ChatFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, c).commit();
                }
                //Redirects to Report menu
                else if (menuItemId == R.id.menuReport) {
                    ReportFragment rep = new ReportFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, rep).commit();
                }
                //Redirects to Reminder menu
                else if (menuItemId == R.id.menuReminder) {
                    ReminderFragment rem = new ReminderFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, rem).commit();
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.menuOverview) {
                    // The user reselected item number one, scroll your content to top.
                }
            }
        });

        mBottomBar.mapColorForTab(0, "#F44336");
        mBottomBar.mapColorForTab(1, "#9C27B0");
        mBottomBar.mapColorForTab(2, "#03A9F4");
        mBottomBar.mapColorForTab(3, "#79554B");
        mBottomBar.mapColorForTab(4, "#FF6F00");

        BottomBarBadge unread;
        unread = mBottomBar.makeBadgeForTabAt(3,"#FF0000",5);
        unread.show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the menu bottom bar state
        mBottomBar.onSaveInstanceState(outState);
    }
}
