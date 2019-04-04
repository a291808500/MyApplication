package com.version.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.version.myapplication.xuanhao.Mypageradapter;
import com.version.myapplication.xuanhao.imageUtil;
import com.version.myapplication.xuanhao.viewpagersing;

import java.util.ArrayList;
import java.util.List;

public class activity extends Activity {

    private ViewPager tv_vp;
    LinearLayout ll_back;
    private List<ImageView> imageViewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //      AndroidBug5497Workaround.assistActivity(this);
        setContentView(R.layout.viewpager);
        //   LogUtil.setDebugLevel(LogUtil.ERROR);
        tv_vp = findViewById(R.id.tv_vp);
        ll_back = findViewById(R.id.ll_back);
        initView();
        tv_vp.setOffscreenPageLimit(imageViewList.size());
        int pw = (int)(getResources().getDisplayMetrics().widthPixels*3.0f/5.0f);
        ViewGroup.LayoutParams lp = tv_vp.getLayoutParams();
        if(lp==null){
            lp = new ViewGroup.LayoutParams(pw,ViewGroup.LayoutParams.MATCH_PARENT);
        }else {
            lp.width = pw;
        }
        tv_vp.setLayoutParams(lp);
        tv_vp.setPageMargin(-50);
        ll_back.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return tv_vp.dispatchTouchEvent(event);
            }
        });
        tv_vp.setPageTransformer(true, new viewpagersing());
        tv_vp.setAdapter(new Mypageradapter(imageViewList));
    }

    private void initView() {
        ImageView imageView = new ImageView(activity.this);
        imageView.setImageBitmap(imageUtil.getreverse(R.mipmap.pic_xd_back,activity.this));
        ImageView imageViews = new ImageView(activity.this);
        imageViews.setImageBitmap(imageUtil.getreverse(R.mipmap.pic_xd_backss,activity.this));
        ImageView imageViewss = new ImageView(activity.this);
        imageViewss.setImageBitmap(imageUtil.getreverse(R.mipmap.pic_xd_backss,activity.this));
        imageViewList.add(imageView);
        imageViewList.add(imageViews);
        imageViewList.add(imageViewss);
    }
}
