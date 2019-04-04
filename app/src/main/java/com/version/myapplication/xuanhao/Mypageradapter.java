package com.version.myapplication.xuanhao;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class Mypageradapter extends PagerAdapter {
    private List<ImageView> listview;
    public Mypageradapter( List<ImageView> listview){
        this.listview = listview;
    }
    @Override
    public int getCount() {
        return listview==null ? 0:listview.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = listview.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(listview.get(position));
    }
}
