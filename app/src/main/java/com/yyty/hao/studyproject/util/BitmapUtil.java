package com.yyty.hao.studyproject.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.IOException;

public class BitmapUtil {

	/**
	 * @Description 按照期望宽高重新计算insamplesize的大小
	 * @return int
	 */
	private static int calculateInSampSize(BitmapFactory.Options options, int reqWidth, int reqHeight){
		final int outWidth = options.outWidth;
		final int outHeight = options.outHeight;
		int inSampleSize=1;
		
		if(outHeight>reqHeight||outWidth>reqWidth){
			final int halfHeight=outHeight/2;
			final int halfWidth=outWidth/2;
			while (halfHeight/inSampleSize>=reqHeight&&halfWidth/inSampleSize>=reqWidth) {
				inSampleSize*=2;
			}
		}
		return inSampleSize;
	}
		
		
	/**
	 * @Description 压缩bitmap
	 * @return Bitmap
	 */
	public static Bitmap decodeSimpleBitmapFrom(String path,int reqWidth,int reqHeight){
		final BitmapFactory.Options option=new BitmapFactory.Options();
		option.inJustDecodeBounds=true;
		BitmapFactory.decodeFile(path, option);
		option.inSampleSize=calculateInSampSize(option, reqWidth, reqHeight);
		option.inJustDecodeBounds=false;
		return BitmapFactory.decodeFile(path, option);
	}
	/** 
     * 读取图片属性：旋转的角度 
     * @param path 图片绝对路径 
     * @return degree旋转的角度 
     */  
    private static int readPictureDegree(String path) {
        int degree = 0;  
        try {  
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL);  
            switch (orientation) {  
            case ExifInterface.ORIENTATION_ROTATE_90:  
                degree = 90;  
                break;  
            case ExifInterface.ORIENTATION_ROTATE_180:  
                degree = 180;  
                break;  
            case ExifInterface.ORIENTATION_ROTATE_270:  
                degree = 270;  
                break;  
            }  
        } catch (IOException e) {
            e.printStackTrace();  
            return degree;  
        }  
        return degree;  
    }

	/**
	 * @param path 图片路径   旋转成为原始图片的角度
	 * @return
	 */
	public static Bitmap rotateBitmap(String path) {
		Bitmap bitmap=BitmapFactory.decodeFile(path);
		int degrees=readPictureDegree(path);
        if (degrees == 0 || null == bitmap) {  
            return bitmap;  
        }  
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees, bitmap.getWidth() / 2, bitmap.getHeight() / 2);  
        Bitmap bmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);  
        if (null != bitmap) {  
            bitmap.recycle();  
        }  
        return bmp;  
    }

	/**
	 * @param bitmap 原始图片的bitmap对象
	 * @param path 图片路径   旋转成为原始图片的角度
	 * @return
	 */
	public static Bitmap rotateBitmap(Bitmap bitmap,String path){
		int degrees=readPictureDegree(path);
		if (degrees == 0 || null == bitmap) {
			return bitmap;
		}
		Matrix matrix = new Matrix();
		matrix.setRotate(degrees, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
		Bitmap bmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		if (null != bitmap) {
			bitmap.recycle();
		}
		return bmp;
	}

}
