package com.yyty.hao.studyproject.activity;


import com.yyty.hao.studyproject.R;
import com.yyty.hao.studyproject.abstracts.ABaseActivity;
import com.yyty.hao.studyproject.interfaces.IPattern;
import com.yyty.hao.studyproject.pattern.ProxyPattern;
import com.yyty.hao.studyproject.view.TopView;

/**
 * 设计模式
 */
public class PatternActivity extends ABaseActivity {

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        IPattern  iPattern=new ProxyPattern();
        iPattern.show();
    }

    @Override
    public void setClickListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.act_pattern;
    }

    @Override
    public void initTitleData() {
        topView.setTitleText(getString(R.string.patternStr));
    }
}
