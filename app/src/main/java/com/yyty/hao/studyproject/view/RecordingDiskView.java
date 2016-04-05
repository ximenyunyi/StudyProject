package com.yyty.hao.studyproject.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.yyty.hao.studyproject.R;
import com.yyty.hao.studyproject.bean.RecordingDiskBean;
import com.yyty.hao.studyproject.util.ScreenUtil;

/**
 * @author 周祥浩
 * @version V1.0
 * @Description  刻录盘控件
 * @e-mail yyty208@gmail.com
 */

public class RecordingDiskView extends View{

    private RecordingDiskBean mRecordingDiskBean;

    /**
     * @param mRecordingDiskBean 设置参数实体
     */
    public void setmRecordingDiskBean(RecordingDiskBean mRecordingDiskBean) {
        this.mRecordingDiskBean = mRecordingDiskBean;
        initParames();
        initPaint();
        invalidate();
    }

    /**
     * 控件时间宽高
     */
    private int actualWidth=100;

    /**
     * 圆环的最大度数
     */
    private int totalRadian = 360;

    /**
     * 最大的值
     */
    private int maxCount=120;

    /**
     * 标题
     */
    private String title;

    /**
     * 文本
     */
    private String content;

    /**
     * 是否需要仪表盘
     */
    private boolean isNeedRecordingDisk=false;

    /**
     * 是否需要标题
     */
    private boolean isNeedTitle=false;

    /**
     * 当前度数
     */
    private int currentRadian;

    /**
     * 圆环渐变色
     */
    private int[] gradientColor= new int[]{Color.GREEN, Color.YELLOW, Color.RED, Color.RED};

    /**
     * 圆的底色
     */
    private int circularBg;

    /**
     * 圆的底色画笔
     */
    private  Paint circularPaint;

    /**
     * 标题画笔
     */
    private Paint titlePaint;


    /**
     * 提示文本
     */
    private String hintText;

    /**
     * 文本颜色
     */
    private int  hintTextBg,titleBg,contentBg;

    /**
     * 提示画笔
     */
    private  Paint hintPaint;

    /**
     *内容画笔
     */
    private Paint contentPaint;

    /**
     * 当前圆的画笔
     */
    private Paint progressPaint;

    /**
     * 当前圆的宽度
     */
    private float progressWidth ;

    /**
     * 锯齿参数
     */
    private  PaintFlagsDrawFilter filter;



    /**
     * 半径
     */
    private int radius;

    /**
     * 圆心横坐标；
     */
    private int  currentX;

    /**
     * 圆心纵坐标
     */
    private int currentY;

    private Context context;


    /**
     * 刻录盘长线
     */
    private int longLine;

    /**
     * 刻录盘短线
     */
    private int shortLine;

    /**
     *刻录尺前端距离
     */
    private int frontSize;

    /**
     * 刻录尺后端距离
     */
    private int backSize;

    /**
     * 刻录尺长线画笔
     */
    private  Paint longLinePaint;

    /**
     *
     */
    private int circularWidth;

    /**
     * 刻录尺短线画笔
     */
    private Paint shortLinePaint;

    private int actualCount=0;

    private float proportion;

    public RecordingDiskView(Context context) {
        super(context);
        initParames(null);
    }

    public RecordingDiskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initParames(attrs);
    }

    public RecordingDiskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        initParames(attrs);
    }

    /**
     * 初始化控件参数
     * @param attrs
     */
    private void initParames(AttributeSet attrs){
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RecordingDiskView);
        int startColor = a.getInteger(R.styleable.RecordingDiskView_startColor, Color.BLUE);
        int centerColor= a.getInteger(R.styleable.RecordingDiskView_centerColor, startColor);
        int endColor= a.getInteger(R.styleable.RecordingDiskView_endColor, startColor);
        gradientColor=new int[]{startColor,centerColor,endColor,endColor,endColor};
        totalRadian= a.getInteger(R.styleable.RecordingDiskView_totalRadian, 360);
        isNeedTitle=a.getBoolean(R.styleable.RecordingDiskView_isNeedTitle, false);
        if(isNeedTitle){
            title= a.getString(R.styleable.RecordingDiskView_titles);
        }
        content=a.getString(R.styleable.RecordingDiskView_content);
        isNeedRecordingDisk=a.getBoolean(R.styleable.RecordingDiskView_isNeedRecordingDisk, false);
        maxCount=a.getInteger(R.styleable.RecordingDiskView_maxCount, 100);
        circularBg=a.getInteger(R.styleable.RecordingDiskView_circularBg, Color.GRAY);
        hintText=a.getString(R.styleable.RecordingDiskView_hintText);
        hintTextBg=a.getInteger(R.styleable.RecordingDiskView_hintTextBg, Color.parseColor("#6B6B6B"));
        titleBg=a.getInteger(R.styleable.RecordingDiskView_titleBg, Color.parseColor("#6B6B6B"));
        contentBg=a.getInteger(R.styleable.RecordingDiskView_contentBg, Color.parseColor("#000000"));
        a.recycle();

        progressWidth = ScreenUtil.getInstants().dip2px(context,10);
        initPaint();

    }

    /**
     *   初始化画笔
     */
    private void initPaint() {


        //圆的底色画笔
        circularPaint = new Paint();
        circularPaint.setAntiAlias(true);
        circularPaint.setColor(circularBg);

        circularWidth = ScreenUtil.getInstants().dip2px(context, 2);
        circularPaint.setStrokeWidth(circularWidth);
        circularPaint.setStrokeCap(Paint.Cap.ROUND);
        circularPaint.setStyle(Paint.Style.STROKE);

        //当前圆画笔
        progressPaint = new Paint();
        progressPaint.setAntiAlias(true);
        progressPaint.setColor(Color.GREEN);
        progressPaint.setStrokeWidth(progressWidth);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setStyle(Paint.Style.STROKE);


        //标题画笔
        titlePaint = new Paint();
        titlePaint.setAntiAlias(true);
        titlePaint.setColor(titleBg);
        titlePaint.setTextSize(25);
        titlePaint.setTextAlign(Paint.Align.CENTER);

        //内容画笔
        contentPaint = new Paint();
        contentPaint.setAntiAlias(true);
        contentPaint.setColor(contentBg);
        contentPaint.setTextSize(55);
        contentPaint.setTextAlign(Paint.Align.CENTER);

        //提示画笔
        hintPaint = new Paint();
        hintPaint.setAntiAlias(true);
        hintPaint.setColor(titleBg);
        hintPaint.setTextSize(35);
        hintPaint.setTextAlign(Paint.Align.CENTER);


        longLinePaint = new Paint();
        longLinePaint.setAntiAlias(true);
        longLinePaint.setColor(Color.BLACK);
        longLinePaint.setTextSize(15);

        shortLinePaint = new Paint();
        shortLinePaint.setAntiAlias(true);
        shortLinePaint.setColor(Color.GRAY);
        shortLinePaint.setTextSize(15);

        longLinePaint.setTextAlign(Paint.Align.CENTER);
        //设置图像图片抗锯齿和paint.setAntiAlias(true);通用
        filter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setDrawFilter(filter);
        int noDraw = (360-totalRadian) / 18;
        //绘制刻录盘
        RectF rectF;
        if(isNeedRecordingDisk){
            for (int i=0;i<40;i++){
                if(i>20-noDraw&&i<noDraw+20){
                    canvas.rotate(9,currentX,currentY);
                    continue;
                }
                //绘制大刻录盘
                if(i%5==0){
                    canvas.drawLine(currentX,frontSize,currentX,frontSize +longLine,longLinePaint);
                //绘制小刻录盘
                }else{
                    canvas.drawLine(currentX,frontSize+(longLine-shortLine)/2,currentX,frontSize+ (longLine-shortLine)/2+shortLine,shortLinePaint);
                }
                canvas.rotate(9,currentX,currentY);
            }
            rectF =new RectF(frontSize+backSize+longLine,frontSize+backSize+longLine,actualWidth-frontSize-backSize-longLine,actualWidth-frontSize-backSize-longLine);
        }else{
            rectF =new RectF(frontSize,frontSize,actualWidth-frontSize,actualWidth-frontSize);
        }
        canvas.drawArc(rectF,noDraw*9+90,totalRadian,false,circularPaint);

        SweepGradient shader=new SweepGradient(currentX,currentY,gradientColor,null);
        Matrix matrix=new Matrix();
        matrix.setRotate(noDraw*9+85, currentX, currentY);
        shader.setLocalMatrix(matrix);
        progressPaint.setShader(shader);
        canvas.drawArc(rectF,noDraw*9+90,currentRadian,false,progressPaint);
        if(isNeedTitle){
            canvas.drawText(title,currentX,currentY-35*2,titlePaint);
        }
        canvas.drawText(actualCount+"",currentX,currentY,contentPaint);
        canvas.drawText(hintText,currentX,currentY+35*2,hintPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if(widthMode==MeasureSpec.EXACTLY){
            actualWidth=widthSize;
            widthSize=widthMeasureSpec;
            heightSize=MeasureSpec.makeMeasureSpec(widthSize,MeasureSpec.EXACTLY);
        }else if(widthMode==MeasureSpec.AT_MOST){
            if(widthSize<actualWidth){
                widthSize=MeasureSpec.makeMeasureSpec(actualWidth,MeasureSpec.EXACTLY);
                heightSize=MeasureSpec.makeMeasureSpec(actualWidth,MeasureSpec.EXACTLY);
            }
        }
        initCinfig();
        super.onMeasure(widthSize, heightSize);
    }

    /**
     *  初始化控件参数值
     */
    private void initCinfig() {
        //圆心坐标
        currentX = actualWidth/2;
        currentY=currentX;
        //刻录盘的参数
        frontSize = ScreenUtil.getInstants().dip2px(context,20);
        if(isNeedRecordingDisk){
            backSize=ScreenUtil.getInstants().dip2px(context,10);
            longLine=ScreenUtil.getInstants().dip2px(context,10);
            shortLine=ScreenUtil.getInstants().dip2px(context,4);
            radius=(actualWidth-frontSize-backSize-longLine)/2;
        }else{
            radius=(actualWidth-frontSize-circularWidth)/2;
        }
        //设置最大的圆环度数
        if(totalRadian>=360){
            totalRadian=360;
        }else{
            if((360-totalRadian)%18!=0){
                totalRadian=totalRadian-(360-totalRadian)%18;
            }
        }

    }

    /**
     * @param count 设置进度条实际大小
     */
    public void setActualCount(int count){

        proportion = totalRadian/(float)maxCount;
        if(count>=maxCount){
           this.actualCount= maxCount;
        }else{
            this.actualCount=count;
        }
        changeProgressUI();
    }

    /**
     * 修改进度条
     */
    private void changeProgressUI(){
        ValueAnimator animator=ValueAnimator.ofInt(0, actualCount);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
               int value= (int) animation.getAnimatedValue();
                RecordingDiskView.this.currentRadian= (int) (value*proportion+0.5f);
                RecordingDiskView.this.actualCount=value;
                invalidate();
            }
        });
        animator.start();
    }

    /**
     * 根据实体初始化控件参数
     */
    private void initParames(){
        if(mRecordingDiskBean==null){
            return;
        }
        //圆环渐变色
        gradientColor=new int[]{
                mRecordingDiskBean.getStartColor()!=0?mRecordingDiskBean.getStartColor():Color.GREEN,
                mRecordingDiskBean.getCenterColor()!=0?mRecordingDiskBean.getCenterColor():Color.YELLOW,
                mRecordingDiskBean.getEndColor()!=0?mRecordingDiskBean.getEndColor():Color.RED,
                };
        //圆环的最大度数
        this.totalRadian=mRecordingDiskBean.getTotalRadian();
        //文本内容以及颜色
        this.title=mRecordingDiskBean.getTitles();
        this.content=mRecordingDiskBean.getContent();
        this.hintText=mRecordingDiskBean.getHintText();
        this.titleBg=mRecordingDiskBean.getTitleBg();
        this.contentBg=mRecordingDiskBean.getCircularBg();
        this.hintTextBg=mRecordingDiskBean.getHintTextBg();

        this.isNeedTitle=mRecordingDiskBean.isNeedTitle();
        this.isNeedRecordingDisk=mRecordingDiskBean.isNeedRecordingDisk();

        this.circularBg=mRecordingDiskBean.getCircularBg();
        this.maxCount=mRecordingDiskBean.getMaxCount();

    }
}
