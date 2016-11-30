package com.example.mathpresso.togethermju;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.mathpresso.togethermju.adapter.ListViewAdapter;
import com.example.mathpresso.togethermju.model.Group;

public class GoupDetailsActivity extends AppCompatActivity{
    ListView listview;
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goup_details);

        Intent intent = getIntent();
        Group group = (Group) intent.getSerializableExtra("notice");

    }


    }

