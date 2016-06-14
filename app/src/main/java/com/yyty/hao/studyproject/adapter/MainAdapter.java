package com.yyty.hao.studyproject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yyty.hao.studyproject.R;
import com.yyty.hao.studyproject.abstracts.ABaseAdapter;
import com.yyty.hao.studyproject.holder.ViewHolder;

import java.util.List;


/**
 *   首页适配器
 */
public class MainAdapter extends ABaseAdapter<String> {

    public MainAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @Override
    public void processeItemData(View convertView, int position) {
        TextView tv_content = ViewHolder.get(convertView, R.id.tv_content);
        String content = getItem(position);
        tv_content.setText(content);
    }

    @Override
    public int initItemLayout() {
        return R.layout.item_main;
    }
}
