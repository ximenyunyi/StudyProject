package com.yyty.hao.studyproject.util;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;


/**
 * textview的工具类
 */
public class TextUtils {

    private static TextUtils instance;

    /**
     * @return 单例
     */
    public TextUtils getInstance(){
        if(instance==null){
            instance=new TextUtils();
        }
        return instance;
    }


    /**
     * @param tv
     * @param color  设置textview文本局部颜色
     * @param startLength
     * @param endLength
     */
    public void setTextPartialColor(TextView tv,int color,int startLength,int endLength){
        SpannableStringBuilder builder = new SpannableStringBuilder(tv.getText().toString());
        ForegroundColorSpan span = new ForegroundColorSpan(color);
        builder.setSpan(span, startLength, endLength, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(builder);
    }


    /**
     * @param tv
     * @param content 设置textview文本局部颜色
     * @param partialStr
     * @param color
     */
    public void setTextPartialColor(TextView tv,String content,String partialStr,int color){
        int index=content.indexOf(partialStr);
        SpannableStringBuilder builder = new SpannableStringBuilder(content);
        ForegroundColorSpan span = new ForegroundColorSpan(color);
        builder.setSpan(span, index, index+partialStr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(builder);
    }
}
