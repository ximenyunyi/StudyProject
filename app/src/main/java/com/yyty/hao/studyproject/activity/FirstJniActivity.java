package com.yyty.hao.studyproject.activity;

import android.view.View;
import android.widget.Toast;

import com.yyty.hao.studyproject.R;
import com.yyty.hao.studyproject.abstracts.ABaseActivity;
import com.yyty.hao.studyproject.view.TopView;

public class FirstJniActivity extends ABaseActivity {

    private native String printJNI(String inputStr);
    static {
        System.loadLibrary("hello-jni");
    }


    @Override
    public void initView() {
        topView= (TopView) this.findViewById(R.id.top_view);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setClickListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.act_first_jni;
    }

    @Override
    public void initTitleData() {
        topView.setTitleText("FirstJNI");
    }

    public void show(View v){
        Toast.makeText(this,"ddd",Toast.LENGTH_LONG).show();
    }
}
