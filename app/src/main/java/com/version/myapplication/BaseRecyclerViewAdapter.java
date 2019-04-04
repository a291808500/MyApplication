package com.version.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;



/**
 * Created by XiaJun on 2015/7/2.
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected String TAG = getClass().getSimpleName();
    public Context mContext;
    public LayoutInflater mInflater;
    public List<T> list;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    public String mAvatar;

    public BaseRecyclerViewAdapter(Context context, List<T> dataList, String mAvatar) {
        this.list = dataList;
        this.mContext = context;
        if(null!=mAvatar){
            this.mAvatar = mAvatar+"";
            Log.e("liaotian",mAvatar+"");
        }

        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        Log.e("liaotian---数据数量",list.size()+"");
        return list.size();
    }

    public List<T> getList() {
        return list;
    }

    public void setData(List<T> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    public void addData(T e) {
        this.list.add(e);
        notifyItemInserted(list.size());
        notifyDataSetChanged();
    }
    public void addDatas(T e) {
        this.list.add(0,e);
        notifyItemInserted(list.size());
        notifyDataSetChanged();


    }
    public void setDatas(List<T> list) {
        this.list.clear();
        this.list = list;
        notifyItemInserted(list.size());
        notifyDataSetChanged();
    }
    public void setData(T[] data) {
        if (data != null) {
            setData(Arrays.asList(data));
        }
    }

    public void addData(T[] data) {
        if (data != null && data.length > 0) {
            addData(Arrays.asList(data));
        }
    }
    public void addDatas(List<T> list) {
        //这里面 无论是 list  还是 this.list 还是dbCahtListBeans  都是不同指向名称   但是指向的都是一个堆内存  所以 他们3个是一个东西
        //list 这里  list 有数据
        Log.e("123",list.size()+"  ");
        //this.list  这个是另一个指向  他指向了数据源的堆内存 然后 吧自身清空了
        this.list.clear();
        Log.e("1233",list.size()+"  ");
        //这里已经没有了  我们做个试验
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    public void addDatat(List<T> list) {
        this.list.addAll(0,list);
     //   notifyDataSetChanged();
    }
    public void addData(List<T> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public T getData(int position) {
        return list.get(position);
    }

    public void removeData(int position) {
        this.list.remove(position);
        notifyItemRemoved(position);
    }

    public void removeData(T e) {
        this.list.remove(e);
        notifyDataSetChanged();
    }

    public void updateData(int position, T e) {
        list.set(position, e);
        notifyItemChanged(position);
    }

    public void clearData() {
        this.list.clear();
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(View holder, final int position) {
        if (onItemClickListener != null) {
            holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null)
                        onItemClickListener.onItemClick(position, getData(position));
                }
            });
        }
    }

    public void setOnItemLongClickListener(View holder, final int position) {
        if (onItemLongClickListener != null) {
            holder.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onItemLongClickListener != null)
                        onItemLongClickListener.onItemLongClick(v, position, getData(position));
                    return true;
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener mListener) {
        if (mListener != null)
            this.onItemClickListener = mListener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(int position, T data);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mListener) {
        if (mListener != null)
            this.onItemLongClickListener = mListener;
    }

    public interface OnItemLongClickListener<T> {
        void onItemLongClick(View itemView, int position, T data);
    }

    public void showToast(String str) {
        showToast( str);
    }

    public void showToast(int resId) {
        showToast( resId);
    }

    public void showToast(JSONObject response) {
        showToast(response.optString("msg"));
    }

    public void showToastRequestFailure() {
        showToast("暂无数据");
    }





}