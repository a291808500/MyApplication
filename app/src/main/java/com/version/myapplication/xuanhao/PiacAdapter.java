package com.version.myapplication.xuanhao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.socks.library.KLog;
import com.version.myapplication.R;

import java.util.ArrayList;



/**
 * Created by Administrator on 2017/12/10.
 */

public class PiacAdapter extends BaseAdapter {

    private final Context mContext;
    private final LayoutInflater mInflater;
    private final ArrayList<String> list;
    private IPickListener listener;

    public PiacAdapter(Context context, ArrayList<String> list) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        this.list = list;
    }
    public interface IPickListener {
        void deleteBank(String shuzu);
        void huadong();
    }

    public void setIDeleteListener(IPickListener listener) {
        this.listener = listener;
    }
    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_pick, parent, false);
            holder.im = convertView.findViewById(R.id.im);
            holder.tv_name = convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(parent.getHeight()>180&&null!=listener){
            listener.huadong();
        }

        KLog.e("TAG",list);
        holder.tv_name.setText(list.get(position));
        holder.im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.deleteBank(list.get(position));
            }
        });
        return convertView;
    }

    class ViewHolder {
ImageView im;
TextView tv_name;
    }
}
