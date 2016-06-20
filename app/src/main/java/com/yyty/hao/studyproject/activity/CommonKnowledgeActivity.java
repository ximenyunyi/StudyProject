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

public class CommonKnowledgeActivity extends ABaseActivity implements AdapterView.OnItemClickListener {

    private ListView lv;

    private MainAdapter adapter;

    private List<String> data=new ArrayList<String>();

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
        return R.layout.act_common_knowledge;
    }

    @Override
    public void initTitleData() {
        topView.setTitleText("常用知识");
    }

    @Override
    public void initAdapterData() {

        data.add("颜色矩阵");
        adapter = new MainAdapter(this, data);
        lv.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                startActivity(new Intent(this,MatrixActivity.class));
                break;
        }
    }
}
