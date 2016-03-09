package com.yyty.hao.studyproject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yyty.hao.studyproject.R;
import com.yyty.hao.studyproject.abstracts.IBaseAdapter;
import com.yyty.hao.studyproject.holder.ViewHolder;

import java.util.List;


/**
 *   首页适配器
 */
public class MainAdapter extends IBaseAdapter<Object> {

    public MainAdapter(Context context, List<Object> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_main, null);
        }
        TextView tv_content = ViewHolder.get(convertView, R.id.tv_content);
        String content = (String) getItem(position);
        tv_content.setText(content);
        return convertView;
    }
}
