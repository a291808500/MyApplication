package com.version.myapplication.xuanhao;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;


import com.socks.library.KLog;
import com.version.myapplication.LotteryAdapter;
import com.version.myapplication.LotteryView;


/**
 * Created by wuyr on 17-12-9 下午7:21.
 */

public class LotteryAdapters extends RecyclerView.Adapter<LotteryAdapters.ViewHolder> {

    private Context mContext;
    private int[] mItemStatus;
    private String[] TEXT_TABLE = new String[]{"第一位", "第二位", "第三位", "第四位", "第五位", "第六位", "第七位"};
    private String[] TEXT_TABLES = new String[]{"第一位", "第二位", "第三位", "第四位", "第五位"};
    public String msg ="";
    private int sun;
    public LotteryAdapters(Context context, int sun) {
        mContext = context;
        this.sun = sun;
        initData();
    }
    @Override
    public void onBindViewHolder(@NonNull LotteryAdapters.ViewHolder holder, int position) {


        holder.lotteryView.setIndex(position);
        holder.lotteryView.setSelectedIndex(mItemStatus[position]);
        DisplayMetrics dm2 = mContext.getResources().getDisplayMetrics();
        System.out.println("heigth2 : " + dm2.heightPixels);
        System.out.println("width2 : " + dm2.widthPixels);
        holder.lotteryView.setOnItemStateChangedListener(new LotteryViews.OnItemStateChangedListener() {
            @Override
            public void onStateChanged(int viewIndex, int itemIndex) {
                //更新状态
                mItemStatus[viewIndex] = itemIndex;
            }
        });
       if(dm2.widthPixels<750){
           holder.lotteryView.setBorderRadius(35);
           return;
       }
        if(dm2.widthPixels<850){
            holder.lotteryView.setBorderRadius(43);
            return;
        }
        if(dm2.widthPixels<950){
            holder.lotteryView.setBorderRadius(51);
            return;
        }
        if(dm2.widthPixels<1050){
            holder.lotteryView.setBorderRadius(59);
            return;
        }
        if(dm2.widthPixels<1150){
            holder.lotteryView.setBorderRadius(67);
            return;
        }
        if(dm2.widthPixels>1150){
            holder.lotteryView.setBorderRadius(75);
            return;
        }



    }

    /**
     * 获取选择状态
     * @return 已经选择的号码数组
     */
    public int[] getSelectedItems() {
        boolean isFinishSelected = true;
        msg = "";
          for(int i = 0 ; i < sun ; i ++){
              if(mItemStatus[i]==-1){

                  return null;
              }
            msg = msg+mItemStatus[i]+"";
            if (i < 0) {
                isFinishSelected = false;
                break;
            }
        }
        KLog.e("TAG",msg);
        return isFinishSelected ? mItemStatus : null;

    }

    /**
     * 初始化选择状态， -1表示未选择
     */
    private void initData() {
        mItemStatus = new int[7];
        for (int i = 0; i < 7; i++) {
            mItemStatus[i] = -1;
        }
    }

    @Override
    public int getItemCount() {
        return sun;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(new LotteryViews(mContext));
    }



    static class ViewHolder extends RecyclerView.ViewHolder {

        LotteryViews lotteryView;

        ViewHolder(View itemView) {
            super(itemView);
            lotteryView = (LotteryViews) itemView;
        }
    }
}