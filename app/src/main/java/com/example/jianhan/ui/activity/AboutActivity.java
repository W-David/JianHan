package com.example.jianhan.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jianhan.R;
import com.example.jianhan.app.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class AboutActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.toolbar_about)
    Toolbar  toolbar;
    @BindView(R.id.scroll_about)
    ScrollView scrollView;
    @BindViews({R.id.tv_card_about_2_shop,R.id.tv_card_about_2_email,R.id.tv_card_about_2_git_hub,R.id.tv_card_about_2_website})
    List<TextView>  tvList;
    @BindView(R.id.fab_about_share)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        setToolbar(toolbar);
        setSupportActionBarTitle("About");
        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        initView();
    }

    private void initView(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_about_card_show);
        scrollView.startAnimation(animation);
        for(TextView textView:tvList) textView.setOnClickListener(this);
        fab.setOnClickListener(this);
        AlphaAnimation alpha = new AlphaAnimation(0.0f,1.0f);
        alpha.setDuration(300);
        alpha.setStartOffset(600);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch(v.getId()){
            case R.id.tv_card_about_2_shop:
                Toast.makeText(this,"未上架应用商店",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_card_about_2_email:
                intent.setAction(Intent.ACTION_SEND);
                intent.setData(Uri.parse(Constants.About.EMAIL));
                intent.putExtra(Intent.EXTRA_SUBJECT,"GOOGLE:JianHan");
                try{ startActivity(intent); }
                catch(Exception e) { Toast.makeText(this, "你好像没有安装邮箱软件哦", Toast.LENGTH_SHORT).show(); }
                break;
            case  R.id.tv_card_about_2_git_hub:
                intent.setData(Uri.parse(Constants.About.GITHUB));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                break;
            case R.id.tv_card_about_2_website:
                intent.setData(Uri.parse(Constants.About.MY_WEBSITE));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                break;
            case R.id.fab_about_share:
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, Constants.About.SHARE_CONTENT);
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent,"分享给"));
                break;
            default:
                break;
        }
    }
}
