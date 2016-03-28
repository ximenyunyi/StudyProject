package com.yyty.hao.studyproject.activity;


import com.yyty.hao.studyproject.R;
import com.yyty.hao.studyproject.abstracts.ABaseActivity;
import com.yyty.hao.studyproject.view.TopView;

/**
 * 设计模式
 */
public class PatternActivity extends ABaseActivity {

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
        return R.layout.act_pattern;
    }

    @Override
    public void initTitleData() {
        topView.setTitleText(getString(R.string.patternStr));
    }
}
