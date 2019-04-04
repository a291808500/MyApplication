package com.version.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



public class RmCityAdapter<T extends String> extends BaseRecyclerAdapter<T> {
    private Context context;
    private LinearLayout ll_js;
    private ImageView im_img;
    private TextView tv_name,tv_context,tv_sum,tv_city;

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.item_rmcity;
    }

    public RmCityAdapter(Context context) {
        this.context = context;


    }



    public interface IAddListListener {//创建抽象类

        void deleteBank(String money, String charge_id, int sumno);//添加抽象方法，可任意添加多个可带参数如void test(String cibtext);往下加就行
        void onitemclick();
    }


    @Override
    public void onBindViewHolder(@NonNull CommonHolder holder, int position) {
        final T item = getItem(position);
        tv_city =  holder.getView(R.id.tv_city);
        tv_city.setText(item+"");

    }
}
