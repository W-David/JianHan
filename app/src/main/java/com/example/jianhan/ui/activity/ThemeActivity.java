package com.example.jianhan.ui.activity;

import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.jianhan.R;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThemeActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        StatusBarUtil.setTransparent(this);
        ButterKnife.bind(this);
        setToolbar(toolbar);
        setSupportActionBarTitle("更换主题");
    }
}
