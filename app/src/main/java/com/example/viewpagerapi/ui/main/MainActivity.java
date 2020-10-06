package com.example.viewpagerapi.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.viewpagerapi.R;
import com.example.viewpagerapi.adapters.FragmentAdapter;
import com.example.viewpagerapi.ui.home.HomeFragment;
import com.example.viewpagerapi.ui.profile.ProfileFragment;
import com.example.viewpagerapi.ui.users.UsersFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentArrayList = new ArrayList<>();
        fillFragment();
        bottomNavigationView = findViewById(R.id.bottom_nav);
        viewPager = findViewById(R.id.view_pager);

        viewPager.setAdapter(new FragmentAdapter(fragmentArrayList, getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home_page) {
                    viewPager.setCurrentItem(0);
                } else if (item.getItemId() == R.id.user_page) {
                    viewPager.setCurrentItem(1);
                } else if (item.getItemId() == R.id.profile_page) {
                    viewPager.setCurrentItem(2);
                }
                return true;
            }
        });
    }

    private void fillFragment() {
        fragmentArrayList.add(new HomeFragment());
        fragmentArrayList.add(new UsersFragment());
        fragmentArrayList.add(new ProfileFragment());
    }
}