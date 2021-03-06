package com.yyty.hao.studyproject.activity;


import android.view.View;
import android.widget.Button;

import com.yyty.hao.studyproject.R;
import com.yyty.hao.studyproject.abstracts.ABaseActivity;
import com.yyty.hao.studyproject.jni.JniTest;
import com.yyty.hao.studyproject.view.RecordingDiskView;
import com.yyty.hao.studyproject.view.TopView;

/**
 * 刻度尺
 */
public class SweepGradientActivity extends ABaseActivity implements View.OnClickListener {

    private Button bt;

    private RecordingDiskView rdv;

    @Override
    public void initView() {

        bt = (Button) this.findViewById(R.id.bt);

        rdv = (RecordingDiskView) this.findViewById(R.id.rdv);
        JniTest jni=new JniTest();
        bt.setText(jni.test());
    }

    @Override
    public void initData() {

    }

    @Override
    public void setClickListener() {
        bt.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_sweep_gradient;
    }

    @Override
    public void initTitleData() {
        topView.setTitleText(getString(R.string.sweep_gradient));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt:
                rdv.setActualCount(100);
                break;
        }
    }
}
