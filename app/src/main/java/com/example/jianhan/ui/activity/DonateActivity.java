package com.example.jianhan.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.jianhan.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DonateActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        ButterKnife.bind(this);
        setToolbar(toolbar);
        setSupportActionBarTitle("Donate");
    }
}