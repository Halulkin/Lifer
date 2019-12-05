package com.halulkin.lifer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.halulkin.lifer.ScheduleFragment.ScheduleFragment;
import com.halulkin.lifer.TargetsFragment.TargetsFragment;
import com.squareup.picasso.Picasso;

import static com.halulkin.lifer.MainActivity.fragmentId;

public class MenuListFragment extends Fragment {

    private ImageView ivMenuUserProfilePhoto;
    private NavigationView vNavigation;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container,
                false);

        vNavigation = view.findViewById(R.id.vNavigation);
        vNavigation.setNavigationItemSelectedListener(menuItem -> {
            displaySelectedScreen(menuItem.getItemId());
            return true;
        });

        ivMenuUserProfilePhoto = vNavigation.getHeaderView(0).findViewById(R.id.profile_image);
        setupHeader();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vNavigation.getMenu().getItem(0).setChecked(true);

        displaySelectedScreen(R.id.nav_targets);
    }

    private void displaySelectedScreen(int itemId) {
        Fragment fragment = null;

        switch (itemId) {
            case R.id.nav_targets:
                fragment = new TargetsFragment();
                fragmentId = 1;
                break;

            case R.id.nav_schedule:
                fragment = new ScheduleFragment();
                fragmentId = 2;
                break;
        }

        if (fragment != null && getFragmentManager() != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_main_frame, fragment);
            ft.commit();

        }
    }

    private void setupHeader() {
        int avatarSize = getResources().getDimensionPixelSize(R.dimen.global_menu_avatar_size);
        String profilePhoto = getResources().getString(R.string.user_profile_photo);

        Picasso.get()
                .load(R.drawable.gaster)
                .placeholder(R.drawable.img_circle_placeholder)
                .resize(avatarSize, avatarSize)
                .centerCrop()
//                .transform(new CircleTransformation())
                .into(ivMenuUserProfilePhoto);
    }
}
