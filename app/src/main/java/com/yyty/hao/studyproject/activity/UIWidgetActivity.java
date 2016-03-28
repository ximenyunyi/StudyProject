package com.yyty.hao.studyproject.activity;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yyty.hao.studyproject.R;
import com.yyty.hao.studyproject.abstracts.ABaseActivity;
import com.yyty.hao.studyproject.adapter.MainAdapter;
import com.yyty.hao.studyproject.view.TopView;

public class UIWidgetActivity extends ABaseActivity implements AdapterView.OnItemClickListener {

    private ListView lv;

    private MainAdapter adapter;

    @Override
    public void initView() {
        lv= (ListView) this.findViewById(R.id.lv);
        topView= (TopView) this.findViewById(R.id.top_view);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setClickListener() {
        lv.setOnItemClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_uiwidget;
    }

    @Override
    public void initTitleData() {
        topView.setTitleText(getString(R.string.UIWidget));
    }

    @Override
    public void initAdapterData() {
        super.initAdapterData();
        data.add("刻录表");
        MainAdapter adapter = new MainAdapter(this,data);
        lv.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:

                break;
        }
    }
}
