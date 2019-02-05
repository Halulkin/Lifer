package com.halulkin.lifer;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.halulkin.lifer.CreatorsActivity.NewTargetTemplate;
import com.halulkin.lifer.flowingdrawer_core.ElasticDrawer;
import com.halulkin.lifer.flowingdrawer_core.FlowingDrawer;

public class MainActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    public FlowingDrawer mDrawer;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private Drawable menuItemIconDrawable, navigationIconDrawable;

    boolean isShow;
    int scrollRange = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawer = findViewById(R.id.drawerLayout);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);

        appBarLayout = findViewById(R.id.appBarLayout);
        appBarLayout.addOnOffsetChangedListener(this);

        toolbar = findViewById(R.id.toolbar);

        setupToolbar();
        setupMenu();
    }

    public void expandToolbar() {
        appBarLayout.setExpanded(true, true);
    }

    protected void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_fingerprint_blue);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.toggleMenu();
            }
        });
    }

    private void setupMenu() {
        FragmentManager fm = getSupportFragmentManager();
        MenuListFragment mMenuFragment = (MenuListFragment) fm.findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuListFragment();
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
        }

//        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
//            @Override
//            public void onDrawerStateChange(int oldState, int newState) {
//                if (newState == ElasticDrawer.STATE_CLOSED) {
////                    Log.i("MainActivity", "Drawer STATE_CLOSED");
//                }
//            }
//
//            @Override
//            public void onDrawerSlide(float openRatio, int offsetPixels) {
//                Log.i("MainActivity", "openRatio=" + openRatio + " ,offsetPixels=" + offsetPixels);
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menuItemIconDrawable = menu.findItem(R.id.action_create_new).getIcon();
        navigationIconDrawable = toolbar.getNavigationIcon();

        if (menuItemIconDrawable != null) {
            menuItemIconDrawable.mutate();
        }
        if (navigationIconDrawable != null) {
            navigationIconDrawable.mutate();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create_new:
                Intent intent = new Intent(MainActivity.this, NewTargetTemplate.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        if (scrollRange == -1) {
            scrollRange = appBarLayout.getTotalScrollRange();
        }
        if (scrollRange + offset == 0) {
            //collapse map
            //TODO: change share icon color - set white share icon
            isShow = true;
            menuItemIconDrawable.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            navigationIconDrawable.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        } else if (isShow) {
            //expanded map
            //TODO: change share icon color - set dark share icon
            isShow = false;
            menuItemIconDrawable.setColorFilter(getResources().getColor(R.color.style_color_primary), PorterDuff.Mode.SRC_ATOP);
            navigationIconDrawable.setColorFilter(getResources().getColor(R.color.style_color_primary), PorterDuff.Mode.SRC_ATOP);
        }
    }

    public void closeDrawer() {
        mDrawer.closeMenu(true);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isMenuVisible()) {
            mDrawer.closeMenu();
        } else {
            super.onBackPressed();
        }
    }
}
