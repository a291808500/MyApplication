package com.version.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import com.socks.library.KLog;


/**
 * Created by wuyr on 17-12-9 下午7:21.
 */

public class LotteryAdapter3 extends RecyclerView.Adapter<LotteryAdapter3.ViewHolder> {

    private Context mContext;
    private int[] mItemStatus;
    private String[] TEXT_TABLE = new String[]{"第一位", "第二位", "第三位", "第四位", "第五位", "第六位", "第七位"};
    private String[] TEXT_TABLES = new String[]{"第一位", "第二位", "第三位", "第四位", "第五位"};
    public String msg = "";
    private int sun;

    public LotteryAdapter3(Context context, int sun, IAddListListener listener) {
        mContext = context;
        this.sun = sun;
        this.listener = listener;
        initData();
    }

    private LotteryAdapter3.IAddListListener listener;//声明成员变量

    public interface IAddListListener {//创建抽象类

        void deleteBank(String name);//添加抽象方法，可任意添加多个可带参数如void test(String cibtext);往下加就行
        void onClose();

    }

    public void deletteAll() {
        holder.lotteryView.setSelectedAll();
    }

    ViewHolder holder;

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        this.holder = holder;
        holder.lotteryView.setIndex(position);
        holder.lotteryView.setSelectedIndex(mItemStatus[position]);
        DisplayMetrics dm2 = mContext.getResources().getDisplayMetrics();
        System.out.println("heigth2 : " + dm2.heightPixels);
        System.out.println("width2 : " + dm2.widthPixels);
        holder.lotteryView.setOnItemStateChangedListener(new LotteryView.OnItemStateChangedListener() {
            @Override
            public void onStateChanged(int viewIndex, int itemIndex, String name) {
                //更新状态
                mItemStatus[viewIndex] = itemIndex;
                listener.deleteBank(name);
            }
            @Override
            public void ondeleat() {
                listener.onClose();
            }
        });
        if (dm2.widthPixels < 750) {
            holder.lotteryView.setBorderRadius(30);
            return;
        }
        if (dm2.widthPixels < 850) {
            holder.lotteryView.setBorderRadius(38);
            return;
        }
        if (dm2.widthPixels < 950) {
            holder.lotteryView.setBorderRadius(46);
            return;
        }
        if (dm2.widthPixels < 1050) {
            holder.lotteryView.setBorderRadius(54);
            return;
        }
        if (dm2.widthPixels < 1150) {
            holder.lotteryView.setBorderRadius(62);
            return;
        }
        if (dm2.widthPixels > 1150) {
            holder.lotteryView.setBorderRadius(70);
            return;
        }


    }

    /**
     * 获取选择状态
     *
     * @return 已经选择的号码数组
     */
    public int[] getSelectedItems() {
        boolean isFinishSelected = true;
        msg = "";
        for (int i = 0; i < sun; i++) {
            if (mItemStatus[i] == -1) {

                return null;
            }
            msg = msg + mItemStatus[i] + "";
            if (i < 0) {
                isFinishSelected = false;
                break;
            }
        }
        KLog.e("TAG", msg);
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
        return new ViewHolder(new LotteryView(mContext, 4));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        LotteryView lotteryView;

        ViewHolder(View itemView) {
            super(itemView);
            lotteryView = (LotteryView) itemView;
        }
    }
}