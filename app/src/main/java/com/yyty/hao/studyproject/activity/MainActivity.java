package com.yyty.hao.studyproject.activity;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yyty.hao.studyproject.R;
import com.yyty.hao.studyproject.abstracts.ABaseActivity;
import com.yyty.hao.studyproject.adapter.MainAdapter;
import com.yyty.hao.studyproject.view.TopView;

public class MainActivity extends ABaseActivity implements AdapterView.OnItemClickListener {
    //this is a listView write by Gao
    private ListView lv;
    //this is a adapter
    private MainAdapter adapter;


    @Override
    public void initView() {
        topView = (TopView) this.findViewById(R.id.top_view);
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
        super.initAdapterData();
        data.add("设计模式");
        data.add("UI控件");
        data.add("JNI");
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
        }
    }
}
