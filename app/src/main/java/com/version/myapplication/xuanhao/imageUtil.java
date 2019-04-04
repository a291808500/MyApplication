package com.version.myapplication.xuanhao;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;

public class imageUtil {
    public static Bitmap getreverse(int resId, Context context){
        Bitmap s = scaleIge(context,resId,600,600);
        Matrix matrix = new Matrix();
        matrix.setScale(1,-1);
        Bitmap in = Bitmap.createBitmap(s,0,s.getHeight()/2,s.getWidth(),s.getHeight()/3,matrix,false);
        Bitmap groupbtm = Bitmap.createBitmap(s.getWidth(),s.getHeight()+s.getHeight()/3+50,s.getConfig());
        Canvas gc = new Canvas(groupbtm);
        gc.drawBitmap(s,0,0,null);
        gc.drawBitmap(in,0,s.getHeight()+100,null);
        Paint p = new Paint();
        Shader.TileMode t =Shader.TileMode.CLAMP;
        LinearGradient shader = new LinearGradient(0,s.getHeight()+100,0,groupbtm.getHeight(), Color.BLACK,Color.TRANSPARENT,t);
        p.setShader(shader);
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        gc.drawRect(0,s.getHeight()+150,s.getWidth(),groupbtm.getHeight(),p);
        return groupbtm;

    }


















    protected  static  Bitmap scaleIge(Context context ,int resId,int w,int h){
        Bitmap b = BitmapFactory.decodeResource(context.getResources(),resId);
        int width = b.getWidth();
        int height = b.getHeight();
        int neww = w;
        int newh = h;
        float sww =(float) neww/width;
        float shh = (float) newh/height;
        Matrix matrix = new Matrix();
        matrix.postScale(sww,shh);
        Bitmap newbttm = Bitmap.createBitmap(b,0,0,width,height,matrix,true);
        return newbttm;

    }
}
