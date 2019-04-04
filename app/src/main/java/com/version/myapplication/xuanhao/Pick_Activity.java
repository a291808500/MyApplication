package com.version.myapplication.xuanhao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.socks.library.KLog;
import com.version.myapplication.LotteryAdapter;
import com.version.myapplication.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



/**  低價購買
 * Created by Administrator on 2017/11/27.
 */

public class Pick_Activity extends Activity implements View.OnClickListener, PiacAdapter.IPickListener {

    private LotteryAdapters mAdapter;
    private GridView gv_xuanhao;
    private TextView tv_add, tv_sjyz, tv_sjwz, tv_delete, tv_money;
    private PiacAdapter adapter;
    ArrayList<String> list;
    private int[] data;
    private String msg;
    private String type;
    private int goodszone = 1;
    private String uid;
    private String name;
    private TextView tv_name;
    private ArrayList<String> arrayListss;
    private int maxcount=100;
    protected Gson mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  //      AndroidBug5497Workaround.assistActivity(this);
        setContentView(R.layout.pickactivity);
     //   LogUtil.setDebugLevel(LogUtil.ERROR);
        iniview();
    }

    private void iniview() {
        list = new ArrayList<>();
        goodszone = 100;
        gv_xuanhao = findViewById(R.id.gv_xuanhao);
        tv_add = findViewById(R.id.tv_add);
        tv_sjyz = findViewById(R.id.tv_sjyz);
        tv_sjwz = findViewById(R.id.tv_sjwz);
        tv_name = findViewById(R.id.tv_name);
        tv_name.setText(name);
        tv_delete = findViewById(R.id.tv_delete);
        tv_money = findViewById(R.id.tv_money);
        tv_add.setOnClickListener(this);
        tv_sjyz.setOnClickListener(this);
        tv_sjwz.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (goodszone == 100 || goodszone == 10) {
            recyclerView.setAdapter(mAdapter = new LotteryAdapters(this, 7));
        } else {
            recyclerView.setAdapter(mAdapter = new LotteryAdapters(this, 5));
        }

        if (list.size() != 0) {
            adapter = new PiacAdapter(this, list);
            adapter.setIDeleteListener(this);
            gv_xuanhao.setAdapter(adapter);
            tv_money.setText(list.size() * 100 + "");

        }


    }
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();

    }
    public void getSelected(View view) {
        int[] data = mAdapter.getSelectedItems();
        if (list.size() == 0) {
            showToast("请选择号码");
            return;
        }
        Gson gson = new Gson();
        KLog.e("TAG", gson.toJson(list));
    //    parameters.put("goodsuid", uid);
        try {
            JSONArray myJsonArray = new JSONArray(gson.toJson(list));
            Log.e("tag",myJsonArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //  parameters.put("selectNumbers" + uid, myJsonArray);
    }








    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add:
                if (list.size() + 1 > maxcount) {
                    showToast("本期只能选" + maxcount + "个号码");
                    return;
                }

                data = mAdapter.getSelectedItems();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).equals(mAdapter.msg)) {
                        showToast("号码已重复");
                        return;
                    }
                }
                if (data == null) {
                    showToast("请输入正确号码");
                    return;
                }
                list.add(mAdapter.msg);
                adapter = new PiacAdapter(this, list);
                adapter.setIDeleteListener(this);
                gv_xuanhao.setAdapter(adapter);
                        tv_money.setText(list.size() * 100 + "");


                break;
            case R.id.tv_sjyz:
                if (list.size() + 1 > maxcount) {
                    showToast("本期只能选" + maxcount + "个号码");
                    return;
                }
                data = mAdapter.getSelectedItems();
                if (goodszone == 100 || goodszone == 10) {

                    msg = (int) ((Math.random() * 9999999)) + "";
                    msg = "0000000".substring(0, 7 - msg.length()) + msg;
                } else {
                    msg = (int) ((Math.random() * 99999)) + "";
                    msg = "0000000".substring(0, 5 - msg.length()) + msg;
                }

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).equals(msg)) {
                        showToast("号码已重复");
                        return;
                    }
                }
                list.add(msg);
                adapter = new PiacAdapter(this, list);
                adapter.setIDeleteListener(this);
                gv_xuanhao.setAdapter(adapter);
                        tv_money.setText(list.size() * 100 + "");
                break;
            case R.id.tv_sjwz:
                if (list.size() + 5 > maxcount) {
                    showToast("本期只能选" + maxcount + "个号码");
                    return;
                }
                data = mAdapter.getSelectedItems();
                for (int o = 0; o < 5; o++) {
                    if (list.size() < 1) {


                        for (int i = 0; i < list.size(); i++) {
                            if (goodszone == 100 || goodszone == 10) {

                                msg = (int) ((Math.random() * 9999999)) + "";
                                msg = "0000000".substring(0, 7 - msg.length()) + msg;
                            } else {
                                msg = (int) ((Math.random() * 99999)) + "";
                                msg = "0000000".substring(0, 5 - msg.length()) + msg;
                            }
                            if (list.get(i).equals(msg)) {
                                showToast("号码已重复");
                                return;
                            } else {

                            }
                        }
                    }
                    if (goodszone == 100 || goodszone == 10) {
                        msg = (int) ((Math.random() * 9999999)) + "";
                        msg = "0000000".substring(0, 7 - msg.length()) + msg;
                    } else {
                        msg = (int) ((Math.random() * 99999)) + "";
                        msg = "0000000".substring(0, 5 - msg.length()) + msg;
                    }
                    list.add(msg);
                }
                //   list.add((int)((Math.random()*9+1)*1000000)+"");
                adapter = new PiacAdapter(this, list);
                adapter.setIDeleteListener(this);
                gv_xuanhao.setAdapter(adapter);
                        tv_money.setText(list.size() * 100 + "");

                break;
            case R.id.tv_delete:
                list.clear();
                adapter = new PiacAdapter(this, list);
                adapter.setIDeleteListener(this);
                gv_xuanhao.setAdapter(adapter);
                tv_money.setText("0");
                if (list.size() < 1) {
                    return;
                }
                        tv_money.setText(list.size() * 100 + "");
                break;
        }
    }

    public static int getRandNum(int min, int max) {
        int randNum = min + (int) (Math.random() * ((max - min) + 1));
        return randNum;
    }



    @Override
    public void deleteBank(String shuzu) { //删除
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(shuzu)) {
                list.remove(i);
            }
        }
        adapter = new PiacAdapter(this, list);
        adapter.setIDeleteListener(this);
        gv_xuanhao.setAdapter(adapter);

                tv_money.setText(list.size() * 100 + "");

    }

    @Override
    public void huadong() {
        gv_xuanhao.setStackFromBottom(true);
    }


}
