package com.example.jianhan.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jianhan.R;
import com.example.jianhan.model.bean.GamerSky;
import com.example.jianhan.model.bean.MoeImg;
import com.example.jianhan.ui.adapter.FragmentAdapter;
import com.example.jianhan.ui.fragment.CosPlayFragment;
import com.example.jianhan.ui.fragment.GamerSkyFragment;
import com.example.jianhan.ui.fragment.MoeImgFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.jaeger.library.StatusBarUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener ,
        View.OnClickListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.tab_layout_main)
    TabLayout tabLayout;
    @BindView(R.id.view_pager_main)
    ViewPager viewPager;

    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // set status bar transparent
        ButterKnife.bind(this);
        initView();
        requestPermission();
    }

    @SuppressLint("CheckResult")
    private void requestPermission(){
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(granted ->{
                    if(granted){
                        Toast.makeText(this,"可以使用App全部功能",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this,"功能受限",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView(){
        setSupportActionBar(toolbar);
        // drawer setting
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.nav_drawer_open,R.string.nav_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
//        View headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_main);
        // tab layout setting
        List<String> titles = Arrays.asList("动漫","角色扮演","游戏");
        viewPager.setOffscreenPageLimit(titles.size()-1);
        for(String title: titles){
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }
        fragments =  new ArrayList<>();
        fragments.add(new MoeImgFragment());
        fragments.add(new CosPlayFragment());
        fragments.add(new GamerSkyFragment());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),fragments,titles);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_search:
                Intent intentToQuery = new Intent(this, QueryActivity.class);
                startActivity(intentToQuery);
                break;
            case R.id.nav_download:
                break;
            case R.id.nav_favorite:
                break;
            case R.id.nav_tools:
                break;
            case R.id.nav_other:
                break;
            case R.id.nav_setting:
                break;
            case R.id.nav_about:
                Intent intentToAbout = new Intent(this,AboutActivity.class);
                startActivity(intentToAbout);
                break;
            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_menu_main_theme:
                Intent theme= new Intent(this,ThemeActivity.class);
                startActivity(theme);
                break;
            case R.id.action_menu_main_mode:
                break;
            case R.id.action_menu_main_donate:
                Intent donate = new Intent(this,DonateActivity.class);
                startActivity(donate);
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.closeDrawer(GravityCompat.START);
        else super.onBackPressed();
    }

    @Override
    public void onClick(View v) {

    }
}
