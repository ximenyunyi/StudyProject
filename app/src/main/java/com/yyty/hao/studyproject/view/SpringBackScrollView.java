package com.yyty.hao.studyproject.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

import com.yyty.hao.studyproject.R;

/**
 * 回弹的scrollview
 */
public class SpringBackScrollView extends ScrollView {

    /**
     * 头部控件难估计的高度
     */
    private float headerHeight;

    /**
     * 头部控件的可见高度
     */
    private float headerVisibleHeight;

    /**
     * 头部控件
     */
    private View mHeadView;

    /**
     *阻尼系数,越小阻力就越大.
     */
    private final float RADIO=0.35f;

    /**
     * 头部视图初始顶部和底部.
     */
    private int initTop,initBotton;

    /**
     * 头部视图拖动时顶部和底部.
     */
    private int mCurrentTop, mCurrentBottom;

    /**
     *当前的scrollview
     */
    private View mCurrentView;

    /**
     * 当前的scrollview的Rect
     */
    private Rect mContentRect=new Rect();

    /**
     * 是否在移动
     */
    private boolean isMoving=false;

    /**
     * 是否在顶端
     */
    private boolean isTop=true;

    /**
     * 首次点击的Y坐标.
     */
    private float mTouchDownY;

    private State state=State.NORMAL;

    private boolean isEnableScroll=false;


    private enum State{
        /**
         * 顶部
         */
        UP,
        /**
         * 底部
         */
        DOWN,
        /**
         * 正常
         */
        NORMAL,

        REFRESH

    }



    public void setmHeadView(View mHeadView) {
        this.mHeadView = mHeadView;
    }

    public SpringBackScrollView(Context context) {
        super(context);
        init(null, 0);
    }

    public SpringBackScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public SpringBackScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SpringBackScrollView, defStyle, 0);
        headerHeight= a.getDimension(R.styleable.SpringBackScrollView_headerHeight, 100);
        headerVisibleHeight=a.getDimension(R.styleable.SpringBackScrollView_headerVisibleHeight,100);
        a.recycle();

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(getChildCount()>0){
            mCurrentView=getChildAt(0);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(getScaleY()>0){
            isTop=false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_DOWN){
            mTouchDownY=ev.getY();
            mCurrentBottom=initBotton=mHeadView.getBottom();
            mCurrentTop=initTop=mHeadView.getTop();
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){

            case MotionEvent.ACTION_MOVE:
                touchMoveEvent(ev);
                break;
            case MotionEvent.ACTION_UP:
                touchUpEvent(ev);
                break;
        }
        return isEnableScroll||super.onTouchEvent(ev);
    }

    /**
     * @param ev 手指移动
     */
    private void touchMoveEvent(MotionEvent ev){
        if(getScrollY()==0){
            state=State.NORMAL;
            if(isTop){
                isTop=false;
                mTouchDownY = ev.getY();
            }

        }
        int  offsetY = (int) (ev.getY()-mTouchDownY);
        float diff= offsetY * 0.5f *RADIO;
        if(diff>130){
            state=State.REFRESH;
        }
        if(offsetY<0&&state==State.NORMAL){
            state=State.UP;
        } else if(offsetY>0&&state==State.NORMAL){
            state=State.DOWN;

        }

        if(state==State.UP){
            isMoving=false;
            isEnableScroll=false;
            offsetY = offsetY < 0 ? offsetY : 0;
        }else if(state==State.DOWN){
            if(getScaleY()<=offsetY){
                isMoving=true;
                isEnableScroll=true;
            }
            offsetY = offsetY < 0 ? 0 : offsetY;
        }
        if(isMoving){
            if(mContentRect.isEmpty()){
                mContentRect.set(mCurrentView.getLeft(),mCurrentView.getTop(),mCurrentView.getRight(),mCurrentView.getBottom());
            }
        mCurrentTop= (int) (initTop+diff);
            mCurrentBottom= (int) (initBotton+diff);
            float contentMoveHeight = offsetY * RADIO;
        int headerBottom = (int) (mCurrentBottom - headerVisibleHeight);
        int top = (int) (mContentRect.top + contentMoveHeight);
        int bottom = (int) (mContentRect.bottom + contentMoveHeight);
            if (top <= headerBottom) {
                mCurrentView.layout(mContentRect.left, top, mContentRect.right, bottom);

                mHeadView.layout(mHeadView.getLeft(),mCurrentTop,mHeadView.getRight(),mCurrentBottom);
            }
        }

    }

    /**
     * @param ev  手指抬起
     */
    private void touchUpEvent(MotionEvent ev){
        if(!mContentRect.isEmpty()&&state!=State.UP){

            robackAnimation();
        }
    }

    /**
     *
     */
    private void robackAnimation(){
        TranslateAnimation headAnimation=new TranslateAnimation(0,0,Math.abs(initTop-mCurrentTop),0);
        headAnimation.setDuration(200);
        mHeadView.startAnimation(headAnimation);
        mHeadView.layout(mHeadView.getLeft(),initTop,mHeadView.getRight(),initBotton);

        TranslateAnimation conentAnimation=new TranslateAnimation(0,0,mCurrentView.getTop(),mContentRect.top);
        conentAnimation.setDuration(200);
        mCurrentView.startAnimation(conentAnimation);
        mCurrentView.layout(mContentRect.left,mContentRect.top,mContentRect.right,mContentRect.bottom);

        mContentRect.setEmpty();
    }
}
