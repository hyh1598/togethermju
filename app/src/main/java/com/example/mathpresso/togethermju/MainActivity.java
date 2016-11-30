package com.example.mathpresso.togethermju;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.mathpresso.togethermju.core.AppController;
import com.example.mathpresso.togethermju.edit.InterestEditActivity;
import com.example.mathpresso.togethermju.edit.PasswordEditActivity;
import com.example.mathpresso.togethermju.edit.UserEditActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static final String[] TAB_TITLES = {
            "NOTICE",
            "FAVORITE",
            "GROUP"
    };
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView mNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeLayout();
    }

    private void initializeLayout(){
        mNavigationView = (NavigationView)findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View view = LayoutInflater.from(this).inflate(R.layout.drawer_header, null);

        TextView emailTextView = (TextView)view.findViewById(R.id.email_text_view);
        TextView nameTextView = (TextView)view.findViewById(R.id.name_text_view);

        emailTextView.setText("이메일: \n" + AppController.user.getEmail());
        nameTextView.setText("이름: \n" + AppController.user.getName());

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.addHeaderView(view);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new simpleAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_setting:
                break;
            case R.id.menu_edit:
                startActivity(new Intent(this, UserEditActivity.class));
                break;
            case R.id.menu_edit_password:
                startActivity(new Intent(this, PasswordEditActivity.class));
                break;
            case R.id.menu_edit_interest:
                startActivity(new Intent(this, InterestEditActivity.class));
                break;
        }
        return false;
    }

    private class simpleAdapter extends FragmentPagerAdapter {
        public simpleAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return TAB_TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new NoticeFragment();
                case 1:
                    return new FavoriteFragment();
                case 2:
                    return new GroupFragment();
            }

            throw new IllegalStateException("There's no fragment for position " + position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TAB_TITLES[position];
        }
    }

}
