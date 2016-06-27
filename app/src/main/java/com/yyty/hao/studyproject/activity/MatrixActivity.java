package com.yyty.hao.studyproject.activity;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.yyty.hao.studyproject.R;
import com.yyty.hao.studyproject.abstracts.ABaseActivity;

public class MatrixActivity extends ABaseActivity {

    private SeekBar sb_R,sb_G,sb_B;

    private ImageView iv;

    private Bitmap originalBitmap=null;

    /**
     * rfg数值
     */
    private float tone=100;

    /**
     * 饱和对
     */
    private float saturation=100;

    /**
     * 亮度
     */
    private float brightness=100;

    /**
     * 最大进度
     */
    private int MAX_PROGRESS=100;

    @Override
    public void initView() {

        iv = (ImageView) this.findViewById(R.id.iv);
        sb_R = (SeekBar) this.findViewById(R.id.sb_r);
        sb_G = (SeekBar) this.findViewById(R.id.sb_g);
        sb_B = (SeekBar) this.findViewById(R.id.sb_b);

    }

    @Override
    public void initData() {
        initBitmap();
        sb_G.setProgress((int) saturation);
        sb_B.setProgress((int) brightness);
        sb_R.setProgress((int) tone);

    }

    @Override
    public void setClickListener() {
        sb_R.setOnSeekBarChangeListener(new SeekBarChangeListener());
        sb_G.setOnSeekBarChangeListener(new SeekBarChangeListener());
        sb_B.setOnSeekBarChangeListener(new SeekBarChangeListener());
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_matrix;
    }

    @Override
    public void initTitleData() {
        topView.setTitleText(getString(R.string.matrix));

    }

    /**
     * seekbar的监听事件
     */
    private class SeekBarChangeListener implements SeekBar.OnSeekBarChangeListener{


        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            switch (seekBar.getId()){
                case R.id.sb_r:
                    tone=(progress-MAX_PROGRESS)*1.0f/MAX_PROGRESS*180;
                    break;
                case  R.id.sb_g:

                    saturation=progress*1.0f/MAX_PROGRESS;
                    break;
                case  R.id.sb_b:
                    brightness=1*progress*1.0f/MAX_PROGRESS;
                    break;
            }
            iv.setImageBitmap(getBitmap());

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    private Bitmap getBitmap(){
        Bitmap bitmap=Bitmap.createBitmap(originalBitmap.getWidth(),originalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //色调
        ColorMatrix toneMatrix=new ColorMatrix();
        //取值范围-180~180 值为“0”为原始色调
//        toneMatrix.setRotate(0,tone);
//        toneMatrix.setRotate(1,tone);
        toneMatrix.setRotate(2,tone);

        //饱和对
        ColorMatrix saturationMatrix=new ColorMatrix();
        //取值范围从0~1 1为原亮度
        saturationMatrix.setSaturation(saturation);

        //亮度
        ColorMatrix brightnessMatrix=new ColorMatrix();
        //同为1的时候为亮度
        brightnessMatrix.setScale(brightness,brightness,brightness,1);
        //混合
        ColorMatrix colorMatrix=new ColorMatrix();
        colorMatrix.postConcat(toneMatrix);
//        colorMatrix.postConcat(saturationMatrix);
        colorMatrix.postConcat(brightnessMatrix);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(originalBitmap,0,0,paint);
        return  bitmap;
    }

    /**
     * 初始化原始bitmap
     */
    private void initBitmap(){
        Drawable drawable = iv.getDrawable();
        if(drawable!=null&&drawable instanceof BitmapDrawable){
            originalBitmap=((BitmapDrawable)drawable).getBitmap();
        }else{

            originalBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.lsq_style_default_filter_normal);
        }
    }
}
