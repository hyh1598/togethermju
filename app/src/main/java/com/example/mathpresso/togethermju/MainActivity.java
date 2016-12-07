package com.example.mathpresso.togethermju;

import android.content.Intent;
import android.graphics.Bitmap;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathpresso.togethermju.Network.urlToImageProcessor;
import com.example.mathpresso.togethermju.core.AppController;

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
    TextView emailTextView ;
    TextView nameTextView ;
    ImageView imageView;
    MainImageLoadProcessor imageloader;

    @Override
    protected void onStart() {
        super.onStart();
        //User정보확인 없을 경우 , Login다시요청
        if(!AppController.UpdateUserinfo(AppController.getInstance())){
            //로그아웃
            AppController.getInstance().clearLocalStore();
            moveToLoginActivity();
        }else{
            Log.d("MAIN:NAME",AppController.user.getName());
            Log.d("MAIN:EMAIL",AppController.user.getEmail());
            Log.d("MAIN:RID",AppController.user.getRid());
            emailTextView.setText("이메일: \n" + AppController.user.getEmail());
            nameTextView.setText("이름: \n" + AppController.user.getName());

            imageloader.execute(AppController.user.getEmail());

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageloader = new MainImageLoadProcessor();
        initializeLayout();

    }

    private void initializeLayout() {
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //drawer Layout
        View view = LayoutInflater.from(this).inflate(R.layout.drawer_header, null);

        emailTextView = (TextView)view.findViewById(R.id.email_text_view);
        nameTextView = (TextView)view.findViewById(R.id.name_text_view);
        imageView = (ImageView)view.findViewById(R.id.user_imageView);


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
        switch (item.getItemId()) {
            case R.id.menu_item_setting:
                Toast.makeText(this, "move to setting activity", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_logout:
                AppController.getInstance().clearLocalStore();
                moveToLoginActivity();
                break;
        }
        return false;
    }

    private void moveToLoginActivity() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
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

    private class MainImageLoadProcessor extends urlToImageProcessor {
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap!=null){
                //upload image on AppController user instance
                AppController.user.setBitmap_pic(bitmap);
                Log.d("IMAGESTATUS","SUCCESS");
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
