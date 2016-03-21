package com.yyty.hao.studyproject.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.yyty.hao.studyproject.R;

/**
 * @author 周祥浩
 * @version V1.0
 * @Description 自适应宽高控件
 * @e-mail yyty208@gmail.com
 */

public class SelfAdaptionView extends FrameLayout {


    /**
     * 宽高比例
     */

    private float proportion=0.0f;

    public void setProportion(float proportion) {
        this.proportion = proportion;
    }

    public SelfAdaptionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewParameter(context,attrs);
    }

    public SelfAdaptionView(Context context) {
        super(context);
        initViewParameter(context,null);
    }

    public SelfAdaptionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViewParameter(context,attrs);
    }

    /**
     * 初始化控件参数值
     */
    private void initViewParameter(Context context,AttributeSet attrs){
        try {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SelfAdaptionView);
            proportion=typedArray.getFloat(R.styleable.SelfAdaptionView_proportion, 0.0f);
            typedArray.recycle();
        }catch (Exception e){

        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode= MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        int height = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();
        //按照宽度适应
        if(widthMode==MeasureSpec.EXACTLY &&heightMode !=MeasureSpec.EXACTLY && proportion!=0.0f){
            heightMeasureSpec= MeasureSpec.makeMeasureSpec((int) (height*proportion)+getPaddingBottom()+getPaddingTop(),MeasureSpec.EXACTLY);
        //按照高度适应
        }else if(widthMode!=MeasureSpec.EXACTLY &&heightMode ==MeasureSpec.EXACTLY && proportion!=0.0f){
            widthMeasureSpec=  MeasureSpec.makeMeasureSpec((int) (width*proportion)+getPaddingLeft()+getPaddingRight(),MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
