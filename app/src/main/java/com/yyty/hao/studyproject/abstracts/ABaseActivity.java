package com.yyty.hao.studyproject.abstracts;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;


import com.yyty.hao.studyproject.R;
import com.yyty.hao.studyproject.view.TopView;


/**
 * activity基类
 */
public abstract class ABaseActivity extends Activity {

    public TopView topView;

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        setContentView(getLayoutId());
        initView();
        initTitleData();
        initAdapterData();
        initData();
        setClickListener();


    }

    /**
     * 初始化控件
     */
    public  abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 设置监听事件
     */
    public abstract void setClickListener();

    /**
     * 初始化适配器数据
     */
    public void initAdapterData(){};

    /**
     * @return布局ID
     */
    public abstract int getLayoutId();

    /**
     * 初始化标题栏数据
     */
    public abstract void initTitleData();

    private void initActionBar(){
        //设置头部菜单
        actionBar = getActionBar();
        if(actionBar != null){
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(R.layout.layout_title);
            topView = (TopView) actionBar.getCustomView().findViewById(R.id.top_view);
            topView.setOnBackClickListern(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    overridePendingTransition(R.anim.tr_back_in,R.anim.tr_back_out);
                }
            });
        }
    }

}
