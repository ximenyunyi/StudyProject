package com.yyty.hao.studyproject.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.yyty.hao.studyproject.R;
import com.yyty.hao.studyproject.util.ScreenUtil;


/**
 * 带角度的图片
 */
public class FilletImageView extends ImageView {

    private Context context;

    /**
     * 圆角横坐标度数
     */
    private float radiusX=0;

    /**
     * 圆角纵坐标度数
     */
    private float radiusY=0;

    /**
     * 图片资源
     */
    private Drawable imageSource;

    private Paint mShaderPaint =new Paint(Paint.ANTI_ALIAS_FLAG);

    private Path path=new Path();

    private boolean isNeedRing=false;

    private interface Shaper{
        //圆
        public int CIRCULAR=0;
        //矩形
        public int RECTANGLE=1;
    }

    private int shaperType=Shaper.RECTANGLE;

    private int measuredWidth;
    private int measuredHeight;

    private int offset;

    private int ringColor=Color.WHITE;

    public FilletImageView(Context context) {
        super(context);
        this.context=context;
        init(null, 0);
    }

    public FilletImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init(attrs, 0);
    }

    public FilletImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context=context;
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FilletImageView);
        radiusX=typedArray.getFloat(R.styleable.FilletImageView_radiusX,0);
        radiusY=typedArray.getFloat(R.styleable.FilletImageView_radiusY,0);

        imageSource = typedArray.getDrawable(R.styleable.FilletImageView_imageSource);
        shaperType=typedArray.getInt(R.styleable.FilletImageView_shaper,Shaper.RECTANGLE);
        isNeedRing=typedArray.getBoolean(R.styleable.FilletImageView_isNeedRing,false);
        ringColor=typedArray.getInt(R.styleable.FilletImageView_ringColor,Color.WHITE);
        mShaderPaint.setFilterBitmap(false);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();
        offset = Math.min(measuredWidth,measuredHeight);
        if(!isInEditMode()){
            createShade();
            creatGraphical(canvas);
            if(isNeedRing){
                createRing(canvas);
            }

        }else{
            super.onDraw(canvas);

        }


    }

    /**
     * @param canvas 创建圆环
     */
    private void createRing(Canvas canvas) {
        Paint paints=new Paint(Paint.ANTI_ALIAS_FLAG);
        int strokeWidth= ScreenUtil.getInstants().dip2px(context,3);
        paints.setStrokeWidth(strokeWidth);
        paints.setColor(Color.WHITE);
        paints.setStrokeCap(Paint.Cap.ROUND);
        paints.setStyle(Paint.Style.STROKE);
        RectF rectf;
        if(offset==measuredHeight){

            rectf =new RectF((getMeasuredWidth()-offset)/2+strokeWidth/2,strokeWidth/2,(getMeasuredWidth()-offset)/2+offset-strokeWidth/2,getMeasuredHeight()-strokeWidth/2);
        }else{
            rectf =new RectF(strokeWidth/2,strokeWidth/2,measuredWidth-strokeWidth/2,measuredWidth-strokeWidth/2);
        }

        canvas.drawArc(rectf,0,360,false,paints);
        canvas.save();
        paints.reset();
        strokeWidth=1;
        paints.setStrokeWidth(strokeWidth);
        paints.setColor(ringColor);
        paints.setStrokeCap(Paint.Cap.ROUND);
        paints.setStyle(Paint.Style.STROKE);
        paints.setFlags(Paint.ANTI_ALIAS_FLAG);
        if(offset==measuredHeight){

            rectf =new RectF((getMeasuredWidth()-offset)/2+strokeWidth/2,strokeWidth/2,(getMeasuredWidth()-offset)/2+offset-strokeWidth/2,getMeasuredHeight()-strokeWidth/2);
        }else{
            rectf =new RectF(strokeWidth/2,strokeWidth/2,measuredWidth-strokeWidth/2,measuredWidth-strokeWidth/2);
        }
        canvas.drawArc(rectf,0,360,false,paints);
    }

    /**
     * 创建shader
     */
    private void createShade(){
        Drawable drawable = getDrawable();
        Bitmap bitmap=null;
        if(drawable!=null){
            bitmap=drawableToBitmap(drawable);
        }else if(imageSource!=null){
            bitmap= drawableToBitmap(imageSource);
        }else{
            bitmap=drawableToBitmap(null);
        }
        BitmapShader shader=new BitmapShader(bitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        shader.setLocalMatrix(getImageMatrix());
        mShaderPaint.setShader(shader);
    }

    /**
     * @param drawable drawable转bitmap
     * @return
     */
    private Bitmap drawableToBitmap(Drawable drawable){
        Bitmap bitmap=null;
        if(drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
            return bitmap;
        }
        if(drawable==null || drawable instanceof ColorDrawable){
            bitmap =Bitmap.createBitmap(2,2, Bitmap.Config.ARGB_8888);
        }else{
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            bitmap=Bitmap.createBitmap(Math.max(intrinsicWidth,2),Math.max(intrinsicHeight,2), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas=new Canvas(bitmap);
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 绘制图形
     */
    private void creatGraphical(Canvas canvas){
        mShaderPaint.setStyle(Paint.Style.FILL);
        switch (shaperType){
            case Shaper.RECTANGLE:
                RectF rectF=new RectF(0,0,measuredWidth,measuredHeight);
                path.addRoundRect(rectF,radiusX,radiusY, Path.Direction.CW);
                break;
            case Shaper.CIRCULAR:
                if(offset==measuredHeight){

                    path.addCircle(measuredWidth/2,measuredHeight/2,offset/2,Path.Direction.CW);
                }else{
                    path.addCircle(measuredWidth/2,measuredWidth/2,offset/2,Path.Direction.CW);
                }
                break;
        }
        canvas.drawPath(path,mShaderPaint);
        canvas.save();

    }
}
