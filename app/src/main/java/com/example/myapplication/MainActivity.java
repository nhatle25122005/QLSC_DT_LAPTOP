package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.fragment.DichvuFragment;
import com.example.myapplication.fragment.HoadonFragment;
import com.example.myapplication.fragment.LinhkienFragment;
import com.example.myapplication.fragment.ThongkechitietFragment;
import com.example.myapplication.fragment.ThongketongquatFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar,
                R.string.nav_drawer_open,
                R.string.nav_drawer_close
        );
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Mặc định mở fragment đầu tiên
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, new DichvuFragment())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_dichvu);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        int id = item.getItemId();
        if (id == R.id.nav_dichvu) {
            selectedFragment = new DichvuFragment();
        } else if (id == R.id.nav_linhkien) {
            selectedFragment = new LinhkienFragment();
        } else if (id == R.id.nav_hoadon) {
            selectedFragment = new HoadonFragment();
        } else if (id == R.id.nav_thongketongquat) {
            selectedFragment = new ThongketongquatFragment();
        } else if (id == R.id.nav_thongkechitiet) {
            selectedFragment = new ThongkechitietFragment();
        }

        if (selectedFragment != null) {
            replaceFragment(selectedFragment);
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
}
