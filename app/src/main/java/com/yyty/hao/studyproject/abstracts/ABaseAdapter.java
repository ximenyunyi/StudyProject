package com.yyty.hao.studyproject.abstracts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;
import java.util.logging.Handler;


/**
 * @param <T>通用适配器
 */
public abstract class ABaseAdapter<T> extends BaseAdapter {

    /**
     * 数据源
     */
    public List<T> data;

    /**
     * 上下文对象
     */
    public Context context;

    /**
     * 消息对象
     */
    public Handler handler;


    /**
     * @param context
     * @param data
     */
    public ABaseAdapter(Context context,List<T> data) {
        this.data = data;
        this.context = context;
    }

    /**
     * @param context 带消息的构造
     */
    public ABaseAdapter(Context context, List<T> data, Handler handler) {
        this.context = context;
        this.data = data;
        this.handler = handler;
    }

    @Override
    public int getCount() {
        return data==null?0:data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, initItemLayout(), null);
        }
        processeItemData(convertView,position);
        return convertView;
    }

    /**
     * @param convertView  处理item的数据
     * @param position
     */
    public abstract void processeItemData(View convertView,int position);

    /**
     * @return  item的布局
     */
    public abstract int initItemLayout();
}
