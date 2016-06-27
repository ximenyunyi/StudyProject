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

public class JniActivity extends ABaseActivity implements AdapterView.OnItemClickListener {

    /**
     * 适配器数据源
     */
    public List<String> data=new ArrayList<String>();

    private ListView lv;

    private MainAdapter adapter;

    @Override
    public void initView() {
        lv= (ListView) this.findViewById(R.id.lv);
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
        return R.layout.act_jni;
    }

    @Override
    public void initTitleData() {
        topView.setTitleText("JNI");
    }

    @Override
    public void initAdapterData() {

        data.add("FirstJni");
        MainAdapter adapter = new MainAdapter(this,data);
        lv.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                startActivity(new Intent(this,FirstJniActivity.class));
                break;
        }
    }
}
