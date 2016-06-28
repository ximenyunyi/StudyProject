package com.yyty.hao.studyproject.activity;


import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.yyty.hao.studyproject.R;
import com.yyty.hao.studyproject.abstracts.ABaseActivity;

public class AnimationActivity extends ABaseActivity implements View.OnClickListener {

    private  Button bt;

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

        bt = (Button) this.findViewById(R.id.bt);
    }

    @Override
    public void setClickListener() {
        bt.setOnClickListener(this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.act_animation;
    }

    @Override
    public void initTitleData() {
        topView.setTitleText(getString(R.string.animation_str));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt:
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.sc);
                animation.setFillAfter(true);
                bt.startAnimation(animation);
                TranslateAnimation tra=new TranslateAnimation(0,110,0,300);
                tra.setDuration(1000);
                tra.setFillAfter(true);
                bt.startAnimation(tra);
                break;
        }
    }
}
