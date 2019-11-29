package com.halulkin.lifer;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.halulkin.lifer.CreatorsActivity.NewScheduleActivity;
import com.halulkin.lifer.CreatorsActivity.NewTargetTemplate;
import com.halulkin.lifer.flowingdrawer_core.ElasticDrawer;
import com.halulkin.lifer.flowingdrawer_core.FlowingDrawer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    public FlowingDrawer mDrawer;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;

    @BindView(R.id.floating_action_button)
    FloatingActionButton floating_action_button;

    private ImageView collapsingToolbarImage, collapsingToolbarImage2, collapsingToolbarImage3, collapsingToolbarImage4, collapsingToolbarImage5;
    private Drawable menuItemIconDrawable, navigationIconDrawable;
    public static int fragmentId = 1;

    boolean isShow;
    int scrollRange = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mDrawer = findViewById(R.id.drawerLayout);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);

        appBarLayout = findViewById(R.id.appBarLayout);
        appBarLayout.addOnOffsetChangedListener(this);

        toolbar = findViewById(R.id.toolbar);
        collapsingToolbarImage = findViewById(R.id.expandedImage);
        collapsingToolbarImage2 = findViewById(R.id.expandedImage2);
        collapsingToolbarImage3 = findViewById(R.id.expandedImage3);
//        collapsingToolbarImage4 = findViewById(R.id.expandedImage4);
        collapsingToolbarImage5 = findViewById(R.id.expandedImage5);

        setupToolbar();
        setupMenu();

    }

    @OnClick(R.id.floating_action_button)
    public void setFloating_action_button() {
        if (fragmentId == 1) {
            Intent intent = new Intent(MainActivity.this, NewTargetTemplate.class);
            startActivity(intent);
        } else if (fragmentId == 2) {
            Intent intent = new Intent(MainActivity.this, NewScheduleActivity.class);
            startActivity(intent);
        }
    }

    public void expandToolbar() {
        appBarLayout.setExpanded(true, true);
    }

    protected void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menulist);
        toolbar.setNavigationOnClickListener(v -> mDrawer.toggleMenu());
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

    public void changeCollapsingToolbarImage(int imageNumber) {

        if (imageNumber == 1) {
            collapsingToolbarImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            collapsingToolbarImage.setImageResource(R.drawable._1);

            collapsingToolbarImage2.setScaleType(ImageView.ScaleType.CENTER_CROP);
            collapsingToolbarImage2.setImageResource(R.drawable._2);

            collapsingToolbarImage3.setScaleType(ImageView.ScaleType.CENTER_CROP);
            collapsingToolbarImage3.setImageResource(R.drawable._3);

//            collapsingToolbarImage4.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            collapsingToolbarImage4.setImageResource(R.drawable._4);

            collapsingToolbarImage5.setScaleType(ImageView.ScaleType.CENTER_CROP);
            collapsingToolbarImage5.setImageResource(R.drawable._5);

        } else {
            collapsingToolbarImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            collapsingToolbarImage.setImageResource(R.drawable.panoramic);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        menuItemIconDrawable = menu.findItem(R.id.action_create_new).getIcon();
//        navigationIconDrawable = toolbar.getNavigationIcon();
//
//        if (menuItemIconDrawable != null) {
//            menuItemIconDrawable.mutate();
//        }
//
//        return true;
//    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        if (scrollRange == -1) {
            scrollRange = appBarLayout.getTotalScrollRange();
        }
        if (scrollRange + offset == 0) {
            //collapse map
            //TODO: change share icon color - set white share icon
            isShow = true;
//            menuItemIconDrawable.setColorFilter(getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
//            navigationIconDrawable.setColorFilter(getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        } else if (isShow) {
            //expanded map
            //TODO: change share icon color - set dark share icon
            isShow = false;
//            menuItemIconDrawable.setColorFilter(getColor(R.color.style_color_primary), PorterDuff.Mode.SRC_ATOP);
//            navigationIconDrawable.setColorFilter(getColor(R.color.style_color_primary), PorterDuff.Mode.SRC_ATOP);
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
