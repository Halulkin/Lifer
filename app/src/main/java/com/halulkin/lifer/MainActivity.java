package com.halulkin.lifer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.halulkin.lifer.CreatorsActivity.NewTargetActivity;
import com.halulkin.lifer.flowingdrawer_core.ElasticDrawer;
import com.halulkin.lifer.flowingdrawer_core.FlowingDrawer;

public class MainActivity extends AppCompatActivity {

    public FlowingDrawer mDrawer;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private Menu menu;

    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);

        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);

        appBarLayout = findViewById(R.id.appBarLayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setupToolbar();
        setupMenu();
        setupChecker();
    }

    public void expandToolbar() {
        appBarLayout.setExpanded(true, true);
    }

    protected void setupToolbar() {

        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_menu_white);
//        toolbar.setNavigationIcon(R.drawable.ic_fingerprint_blue);

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

        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == ElasticDrawer.STATE_CLOSED) {
                    Log.i("MainActivity", "Drawer STATE_CLOSED");
                }
            }

            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {
                Log.i("MainActivity", "openRatio=" + openRatio + " ,offsetPixels=" + offsetPixels);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create_new:
                Intent intent = new Intent(MainActivity.this, NewTargetActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupChecker() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    // Collapsed
                    toolbar.setNavigationIcon(R.drawable.ic_fingerprint_white);
//                    toolbar.getMenu().getItem(0).setIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_add_new_white));

                } else if (verticalOffset == 0) {
                    // Expanded
                    toolbar.setNavigationIcon(R.drawable.ic_fingerprint_blue);
//                    toolbar.getMenu().getItem(0).setIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_add_new_white));
                } else {
                    // Somewhere in between
                    toolbar.setNavigationIcon(R.drawable.ic_fingerprint_blue);
//                    toolbar.getMenu().getItem(0).setIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_add_new_white));
                }
            }
        });
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
