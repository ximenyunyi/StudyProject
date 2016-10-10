package com.yyty.hao.studyproject.activity;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yyty.hao.studyproject.R;
import com.yyty.hao.studyproject.abstracts.ABaseActivity;
import com.yyty.hao.studyproject.adapter.MainAdapter;
import com.yyty.hao.studyproject.view.TopView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ABaseActivity implements AdapterView.OnItemClickListener {

    private ListView lv;

    private MainAdapter adapter;

    private List<String> data=new ArrayList<String>();

    @Override
    public void initView() {
        lv = (ListView) this.findViewById(R.id.lv);
        
    }

    @Override
    public void initData()  {
    }

    @Override
    public void setClickListener() {
        lv.setOnItemClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_main;
    }

    @Override
    public void initTitleData() {
        topView.setTitleText(getString(R.string.homepage));
        topView.setIvBackVisibility(View.GONE);
    }

    @Override
    public void initAdapterData() {

        data.add("设计模式");
        data.add("UI控件");
        data.add("JNI");
        data.add("基本知识");
        data.add("动画");
        adapter = new MainAdapter(this, data);
        lv.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                startActivity(new Intent(this,PatternActivity.class));
                break;
            case 1:
                startActivity(new Intent(this,UIWidgetActivity.class));
                break;
            case 2:
                startActivity(new Intent(this,JniActivity.class));
                break;
            case 3:
                 startActivity(new Intent(this,CommonKnowledgeActivity.class));
                break;
            case 4:
                startActivity(new Intent(this,AnimationActivity.class));
                break;
        }
        overridePendingTransition(R.anim.tr_in,R.anim.tr_out);
    }
}
