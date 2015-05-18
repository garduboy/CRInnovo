package com.ivec.crinnovo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivec.crinnovo.util.ExpandableListAdapter;
import com.ivec.crinnovo.views.AddPatientFragment;
import com.ivec.crinnovo.views.PatientFragment;
import com.ivec.crinnovo.views.ProjectFragment;
import com.ivec.crinnovo.views.ProtocolFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private Toolbar mMainToolbar;
    private CharSequence mTitle;
    private DrawerLayout mDrawerLayout;
    private ExpandableListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = getString(R.string.select_protocol);

        mMainToolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(mMainToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ExpandableListView) findViewById(R.id.left_drawer);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.string.app_name,  /* "open drawer" description */
                R.string.select_protocol  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //Dynamically add the scores fragment
        FragmentTransaction ft;
        ProtocolFragment protocols = new ProtocolFragment();
        ft = getFragmentManager().beginTransaction();
        //Should be add, let's find out where do we need to remove it when the configuration changes
        ft.replace(R.id.mainContent, protocols, mTitle.toString());
        ft.commit();
        setTitle(mTitle);

        setupNavigationDrawer();

    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        SpannableString s = new SpannableString(mTitle);
        getSupportActionBar().setTitle(s);
    }

    public void setToolbarDrawable(Drawable logo) {
        ((ImageView)mMainToolbar.findViewById(R.id.toolbar_logo)).setImageDrawable(logo);
    }

    public void setToolbarTitle(CharSequence title) {
        ((TextView)mMainToolbar.findViewById(R.id.toolbar_title)).setText(title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setupNavigationDrawer()
    {
        try
        {
            String[] navItems = getResources().getStringArray(R.array.action_list);
            ArrayList<String> headers = new ArrayList<String>();
            HashMap<String, List<Object>> listDataChild = new HashMap<String, List<Object>>();

            for(int i = 0; i < navItems.length; i++)
            {
                String navItem = navItems[i];
                List<Object> subItems = new ArrayList<Object>();
                headers.add(navItem);
                listDataChild.put(navItem, subItems);
            }

            // Set the adapter for the list view
            mDrawerList.setAdapter(new ExpandableListAdapter(this, headers, listDataChild));
            mDrawerList.setGroupIndicator(null);

            // Set the list's click listener
            mDrawerList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id) {
                    selectItem(groupPosition, childPosition, id);
                    return true;
                }
            });

            mDrawerList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    selectItem(groupPosition, -1, 0);
                    return false;
                }
            });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void selectItem(int groupPosition, int childPosition, long id) {
        FragmentTransaction ft;
        switch(groupPosition) {
            case 0:
                //Protocols
                ((ImageView)mMainToolbar.findViewById(R.id.toolbar_logo)).setImageDrawable(null);
                ((TextView)mMainToolbar.findViewById(R.id.toolbar_title)).setText("");
                ft = getFragmentManager().beginTransaction();
                ProtocolFragment protocols = new ProtocolFragment();
                ft.replace(R.id.mainContent, protocols, "Select Protocol");
                ft.commit();
                setTitle("Select Protocol");
                break;
            case 1:
                //Projects
        }
        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(groupPosition, true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}
