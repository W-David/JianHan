package com.example.jianhan.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.example.jianhan.R;
import com.jaeger.library.StatusBarUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        StatusBarUtil.setTransparent(this);
    }

    @Override
    protected void onResume() {
        countDownTimer.cancel();
        countDownTimer.start();
        super.onResume();
    }

    @Override
    protected void onStop() {
        countDownTimer.cancel();
        super.onStop();
    }

    CountDownTimer countDownTimer = new CountDownTimer(500, 500) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(-1,R.anim.activity_exit_alpha);
            finish();
        }
    };

}
