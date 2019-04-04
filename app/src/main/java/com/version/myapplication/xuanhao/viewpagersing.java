package com.version.myapplication.xuanhao;

import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

public class viewpagersing implements ViewPager.PageTransformer {
    private static  final  float min_scale = 0.55f;
    @Override
    public void transformPage(@NonNull View view, float v) {
        float sf = Math.max(min_scale,1-Math.abs(v));
        float rotatte = 20 *Math.abs(v);
        if (v<-1){

        }else {
            if (v<0){
                view.setScaleX(sf);
                view.setScaleY(sf);
                view.setRotationY(rotatte);
            }else if (v >=0&& v<1){
                view.setScaleX(sf);
                view.setScaleY(sf);
                view.setRotationY(-rotatte);

            }else if(v>=1){
                view.setScaleX(sf);
                view.setScaleY(sf);
                view.setRotationY(-rotatte);
            }
        }
    }
}
