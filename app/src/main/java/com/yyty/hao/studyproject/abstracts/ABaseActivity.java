package com.yyty.hao.studyproject.abstracts;

import android.app.Activity;
import android.os.Bundle;


import com.yyty.hao.studyproject.view.TopView;

import java.util.ArrayList;
import java.util.List;

/**
 * activity基类
 */
public abstract class ABaseActivity extends Activity {

    public TopView topView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

}
