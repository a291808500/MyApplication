package com.version.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.socks.library.KLog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainActivity extends AppCompatActivity {
    private LotteryAdapter mAdapter;

    private LotteryAdapter1 mAdapter1;
    private LotteryAdapter2 mAdapter2;
    private LotteryAdapter3 mAdapter3;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.rcv_one)
    MaxHeightRecyclerView rcv_one;
    @BindView(R.id.rcv_two)
    MaxHeightRecyclerView rcv_two;
    @BindView(R.id.rcv_three)
    MaxHeightRecyclerView rcv_three;
    @BindView(R.id.rcv_four)
    MaxHeightRecyclerView rcv_four;
    @BindView(R.id.tv_zw)
    TextView tv_zw;
    @BindView(R.id.recycler_city)
    RecyclerView recycler_city;
    @BindView(R.id.tv_btn)
    TextView tv_btn;
    @BindView(R.id.ll_view)
    ScrollView ll_view;
    @BindView(R.id.tv_gdxd)
    TextView tv_gdxd;
    @BindView(R.id.ll_top)
    LinearLayout ll_top;
    @BindView(R.id.ll_sstop)
    LinearLayout ll_sstop;
    @BindView(R.id.rv_title)
    RelativeLayout rv_title;


    String[] city = {"北京", "上海", "深圳", "广州", "杭州", "武汉", "成都", "西安"};
    private CityAdapter cityAdapter;
    private LinearLayoutManager linearLayoutManager;
    private DisplayMetrics dm2;
    private RecyclerView recyclerView;
    private Unbinder mUnbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        recyclerView = findViewById(R.id.recycler_view);
        ButterKnife.bind(this);
        RecyclerView recyclerView1 = findViewById(R.id.recycler_view1);
        RecyclerView recyclerView2 = findViewById(R.id.recycler_view2);
        RecyclerView recyclerView3 = findViewById(R.id.recycler_view3);
        iniview();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter = new LotteryAdapter(this, 1, new LotteryAdapter.IAddListListener() {
            @Override
            public void deleteBank(String name) {
                getCiytShowList(name);
                ll_view.post(new Runnable() {
                    @Override
                    public void run() {
                        ll_view.scrollTo(0, tv_gdxd.getTop());
                    }
                });
                mAdapter1.deletteAll();
                mAdapter2.deletteAll();
                mAdapter3.deletteAll();
                rcv_one.setVisibility(View.VISIBLE);
                rcv_two.setVisibility(View.GONE);
                rcv_three.setVisibility(View.GONE);
                rcv_four.setVisibility(View.GONE);
            }

            @Override
            public void onClose() {
                rcv_one.setVisibility(View.GONE);
            }
        }));


        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(mAdapter1 = new LotteryAdapter1(this, 1, new LotteryAdapter1.IAddListListener() {
            @Override
            public void deleteBank(String name) {
                ll_view.post(new Runnable() {
                    @Override
                    public void run() {
                        ll_view.scrollTo(0, tv_gdxd.getTop());
                    }
                });
                getCiytShowList(name);
                mAdapter.deletteAll();
                mAdapter2.deletteAll();
                mAdapter3.deletteAll();
                rcv_one.setVisibility(View.GONE);
                rcv_two.setVisibility(View.VISIBLE);
                rcv_three.setVisibility(View.GONE);
                rcv_four.setVisibility(View.GONE);
            }

            @Override
            public void onClose() {
                rcv_two.setVisibility(View.GONE);
            }
        }));


        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setAdapter(mAdapter2 = new LotteryAdapter2(this, 1, new LotteryAdapter2.IAddListListener() {
            @Override
            public void deleteBank(String name) {
                getCiytShowList(name);
                ll_view.post(new Runnable() {
                    @Override
                    public void run() {
                        ll_view.scrollTo(0, tv_gdxd.getTop());
                    }
                });
                mAdapter.deletteAll();
                mAdapter1.deletteAll();
                mAdapter3.deletteAll();
                rcv_one.setVisibility(View.GONE);
                rcv_two.setVisibility(View.GONE);
                rcv_three.setVisibility(View.VISIBLE);
                rcv_four.setVisibility(View.GONE);
            }

            @Override
            public void onClose() {
                rcv_three.setVisibility(View.GONE);
            }
        }));


        recyclerView3.setLayoutManager(new LinearLayoutManager(this));
        recyclerView3.setAdapter(mAdapter3 = new LotteryAdapter3(this, 1, new LotteryAdapter3.IAddListListener() {
            @Override
            public void deleteBank(String name) {
                getCiytShowList(name);
                ll_view.post(new Runnable() {
                    @Override
                    public void run() {
                        ll_view.scrollTo(0, tv_gdxd.getTop());
                    }
                });
                mAdapter.deletteAll();
                mAdapter1.deletteAll();
                mAdapter2.deletteAll();
                rcv_one.setVisibility(View.GONE);
                rcv_two.setVisibility(View.GONE);
                rcv_three.setVisibility(View.GONE);
                rcv_four.setVisibility(View.VISIBLE);
            }

            @Override
            public void onClose() {
                rcv_four.setVisibility(View.GONE);
            }
        }));
        iniMode();


        dm2 = getResources().getDisplayMetrics();

        ll_top.post(new Runnable() {
            @Override
            public void run() {

                KLog.e("tag", "匹配城市" + ll_top.getHeight());
                KLog.e("tag", "搜索" + ll_sstop.getHeight());
                KLog.e("tag", "标题" + rv_title.getHeight());
                ViewGroup.LayoutParams para2;
                para2 = rcv_one.getLayoutParams();
                para2.height = dm2.heightPixels - ll_top.getHeight() - recyclerView.getHeight() * 4;
                rcv_one.setLayoutParams(para2);
                rcv_two.setLayoutParams(para2);
                rcv_three.setLayoutParams(para2);
                rcv_four.setLayoutParams(para2);


                ViewGroup.LayoutParams para1;
                para1 = ll_view.getLayoutParams();
                para1.height = dm2.heightPixels - ll_top.getHeight();
                ll_view.setLayoutParams(para1);
            }
        });
//        rcv_one.setmMaxHeight(ll_view.getHeight()-recyclerView.getHeight()*4);
//        rcv_two.setmMaxHeight(ll_view.getHeight()-recyclerView.getHeight()*4);
//        rcv_four.setmMaxHeight(ll_view.getHeight()-recyclerView.getHeight()*4);
//        rcv_three.setmMaxHeight(ll_view.getHeight()-recyclerView.getHeight()*4);
//        KLog.e("tag","屏幕高度"+dm2.heightPixels);
//        KLog.e("tag","匹配城市"+ll_top.getHeight());
//        KLog.e("tag","搜索"+ll_sstop.getHeight());
//        KLog.e("tag","标题"+rv_title.getHeight());
    }

    CityItemBean cityItemBean;

    private void getCiytShowList(String name) {
        if (null == citySBean) {
            return;
        }
        switch (name) {
            case "A":
                arrayList.clear();
                if (null == citySBean.getData().getA()) {

                    cityAdapter.setNewData(arrayList);
                    return;
                }
                for (int i = 0; i < citySBean.getData().getA().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getA().get(i).getAreaname(), citySBean.getData().getA().get(i).getDredge(), citySBean.getData().getA().get(i).getFirstletter(), citySBean.getData().getA().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "B":
                arrayList.clear();
                if (null == citySBean.getData().getB()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }
                for (int i = 0; i < citySBean.getData().getB().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getB().get(i).getAreaname(), citySBean.getData().getB().get(i).getDredge(), citySBean.getData().getB().get(i).getFirstletter(), citySBean.getData().getB().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "C":
                arrayList.clear();
                if (null == citySBean.getData().getC()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }
                for (int i = 0; i < citySBean.getData().getC().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getC().get(i).getAreaname(), citySBean.getData().getC().get(i).getDredge(), citySBean.getData().getC().get(i).getFirstletter(), citySBean.getData().getC().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "D":
                arrayList.clear();
                if (null == citySBean.getData().getD()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }
                for (int i = 0; i < citySBean.getData().getD().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getD().get(i).getAreaname(), citySBean.getData().getD().get(i).getDredge(), citySBean.getData().getD().get(i).getFirstletter(), citySBean.getData().getD().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "E":
                arrayList.clear();
                if (null == citySBean.getData().getE()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }
                for (int i = 0; i < citySBean.getData().getE().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getE().get(i).getAreaname(), citySBean.getData().getE().get(i).getDredge(), citySBean.getData().getE().get(i).getFirstletter(), citySBean.getData().getE().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "F":
                arrayList.clear();
                if (null == citySBean.getData().getF()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }
                for (int i = 0; i < citySBean.getData().getF().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getF().get(i).getAreaname(), citySBean.getData().getF().get(i).getDredge(), citySBean.getData().getF().get(i).getFirstletter(), citySBean.getData().getF().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "G":
                arrayList.clear();
                if (null == citySBean.getData().getG()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }
                for (int i = 0; i < citySBean.getData().getG().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getG().get(i).getAreaname(), citySBean.getData().getG().get(i).getDredge(), citySBean.getData().getG().get(i).getFirstletter(), citySBean.getData().getG().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "H":
                arrayList.clear();
                if (null == citySBean.getData().getH()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }
                for (int i = 0; i < citySBean.getData().getH().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getH().get(i).getAreaname(), citySBean.getData().getH().get(i).getDredge(), citySBean.getData().getH().get(i).getFirstletter(), citySBean.getData().getH().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "I":
                arrayList.clear();
                if (null == citySBean.getData().getI()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }
                for (int i = 0; i < citySBean.getData().getI().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getI().get(i).getAreaname(), citySBean.getData().getI().get(i).getDredge(), citySBean.getData().getI().get(i).getFirstletter(), citySBean.getData().getI().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);

                break;
            case "J":
                arrayList.clear();
                if (null == citySBean.getData().getJ()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }
                for (int i = 0; i < citySBean.getData().getJ().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getJ().get(i).getAreaname(), citySBean.getData().getJ().get(i).getDredge(), citySBean.getData().getJ().get(i).getFirstletter(), citySBean.getData().getJ().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "K":
                arrayList.clear();
                if (null == citySBean.getData().getK()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }
                for (int i = 0; i < citySBean.getData().getK().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getK().get(i).getAreaname(), citySBean.getData().getK().get(i).getDredge(), citySBean.getData().getK().get(i).getFirstletter(), citySBean.getData().getK().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "L":

                arrayList.clear();
                if (null == citySBean.getData().getL()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }
                for (int i = 0; i < citySBean.getData().getL().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getL().get(i).getAreaname(), citySBean.getData().getL().get(i).getDredge(), citySBean.getData().getL().get(i).getFirstletter(), citySBean.getData().getL().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "M":
                arrayList.clear();
                if (null == citySBean.getData().getM()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }
                for (int i = 0; i < citySBean.getData().getM().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getM().get(i).getAreaname(), citySBean.getData().getM().get(i).getDredge(), citySBean.getData().getM().get(i).getFirstletter(), citySBean.getData().getM().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "N":
                arrayList.clear();
                if (null == citySBean.getData().getN()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }
                for (int i = 0; i < citySBean.getData().getN().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getN().get(i).getAreaname(), citySBean.getData().getN().get(i).getDredge(), citySBean.getData().getN().get(i).getFirstletter(), citySBean.getData().getN().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "O":
                arrayList.clear();
                if (null == citySBean.getData().getO()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }
                for (int i = 0; i < citySBean.getData().getO().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getO().get(i).getAreaname(), citySBean.getData().getO().get(i).getDredge(), citySBean.getData().getO().get(i).getFirstletter(), citySBean.getData().getO().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "P":
                arrayList.clear();
                if (null == citySBean.getData().getP()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }
                for (int i = 0; i < citySBean.getData().getP().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getP().get(i).getAreaname(), citySBean.getData().getP().get(i).getDredge(), citySBean.getData().getP().get(i).getFirstletter(), citySBean.getData().getP().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "Q":
                arrayList.clear();
                if (null == citySBean.getData().getQ()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }
                for (int i = 0; i < citySBean.getData().getQ().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getQ().get(i).getAreaname(), citySBean.getData().getQ().get(i).getDredge(), citySBean.getData().getQ().get(i).getFirstletter(), citySBean.getData().getQ().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "R":
                arrayList.clear();
                if (null == citySBean.getData().getR()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }
                for (int i = 0; i < citySBean.getData().getR().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getR().get(i).getAreaname(), citySBean.getData().getR().get(i).getDredge(), citySBean.getData().getR().get(i).getFirstletter(), citySBean.getData().getR().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "S":
                arrayList.clear();
                if (null == citySBean.getData().getS()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }
                for (int i = 0; i < citySBean.getData().getS().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getS().get(i).getAreaname(), citySBean.getData().getS().get(i).getDredge(), citySBean.getData().getS().get(i).getFirstletter(), citySBean.getData().getS().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "T":
                arrayList.clear();
                if (null == citySBean.getData().getT()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }
                for (int i = 0; i < citySBean.getData().getT().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getT().get(i).getAreaname(), citySBean.getData().getT().get(i).getDredge(), citySBean.getData().getT().get(i).getFirstletter(), citySBean.getData().getT().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "U":
                arrayList.clear();
                if (null == citySBean.getData().getU()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }

                for (int i = 0; i < citySBean.getData().getU().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getU().get(i).getAreaname(), citySBean.getData().getU().get(i).getDredge(), citySBean.getData().getU().get(i).getFirstletter(), citySBean.getData().getU().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "V":
                arrayList.clear();
                if (null == citySBean.getData().getV()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }

                for (int i = 0; i < citySBean.getData().getV().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getV().get(i).getAreaname(), citySBean.getData().getV().get(i).getDredge(), citySBean.getData().getV().get(i).getFirstletter(), citySBean.getData().getV().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "W":
                arrayList.clear();
                if (null == citySBean.getData().getW()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }

                for (int i = 0; i < citySBean.getData().getW().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getW().get(i).getAreaname(), citySBean.getData().getW().get(i).getDredge(), citySBean.getData().getW().get(i).getFirstletter(), citySBean.getData().getW().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "X":
                arrayList.clear();
                if (null == citySBean.getData().getX()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }

                for (int i = 0; i < citySBean.getData().getX().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getX().get(i).getAreaname(), citySBean.getData().getX().get(i).getDredge(), citySBean.getData().getX().get(i).getFirstletter(), citySBean.getData().getX().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "Y":
                arrayList.clear();
                if (null == citySBean.getData().getY()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }

                for (int i = 0; i < citySBean.getData().getY().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getY().get(i).getAreaname(), citySBean.getData().getY().get(i).getDredge(), citySBean.getData().getY().get(i).getFirstletter(), citySBean.getData().getY().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
            case "Z":
                arrayList.clear();
                if (null == citySBean.getData().getZ()) {
                    cityAdapter.setNewData(arrayList);
                    return;
                }
                for (int i = 0; i < citySBean.getData().getZ().size(); i++) {
                    cityItemBean = new CityItemBean(citySBean.getData().getZ().get(i).getAreaname(), citySBean.getData().getZ().get(i).getDredge(), citySBean.getData().getZ().get(i).getFirstletter(), citySBean.getData().getZ().get(i).getAreaid());
                    arrayList.add(cityItemBean);
                }
                cityAdapter.setNewData(arrayList);
                break;
        }
    }

    private CitySBean citySBean;
    private Gson mGson;

    private void iniMode() {
        String json = "{\n" +
                "\t\"code\": \"1\",\n" +
                "\t\"msg\": \"获取成功\",\n" +
                "\t\"data\": {\n" +
                "\t\t\"A\": [{\n" +
                "\t\t\t\"areaid\": \"3251\",\n" +
                "\t\t\t\"areaname\": \"澳门特别行政区\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"A\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"1074\",\n" +
                "\t\t\t\"areaname\": \"安庆市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"A\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"460\",\n" +
                "\t\t\t\"areaname\": \"阿拉善盟\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"A\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"2800\",\n" +
                "\t\t\t\"areaname\": \"阿里地区\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"A\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"3109\",\n" +
                "\t\t\t\"areaname\": \"阿拉尔市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"A\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"1556\",\n" +
                "\t\t\t\"areaname\": \"安阳市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"A\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"2431\",\n" +
                "\t\t\t\"areaname\": \"阿坝藏族羌族自治州\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"A\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"2514\",\n" +
                "\t\t\t\"areaname\": \"安顺市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"A\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"3217\",\n" +
                "\t\t\t\"areaname\": \"阿勒泰地区\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"A\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"2907\",\n" +
                "\t\t\t\"areaname\": \"安康市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"A\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"3160\",\n" +
                "\t\t\t\"areaname\": \"阿克苏地区\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"A\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"490\",\n" +
                "\t\t\t\"areaname\": \"鞍山市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"A\"\n" +
                "\t\t}],\n" +
                "\t\t\"B\": [{\n" +
                "\t\t\t\"areaid\": \"2130\",\n" +
                "\t\t\t\"areaname\": \"北海市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"B\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"359\",\n" +
                "\t\t\t\"areaname\": \"包头市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"B\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"1041\",\n" +
                "\t\t\t\"areaname\": \"蚌埠市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"B\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"506\",\n" +
                "\t\t\t\"areaname\": \"本溪市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"B\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"2159\",\n" +
                "\t\t\t\"areaname\": \"百色市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"B\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"420\",\n" +
                "\t\t\t\"areaname\": \"巴彦淖尔市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"B\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"2207\",\n" +
                "\t\t\t\"areaname\": \"保亭黎族苗族自治县\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"B\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"2940\",\n" +
                "\t\t\t\"areaname\": \"白银市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"B\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"2828\",\n" +
                "\t\t\t\"areaname\": \"宝鸡市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"B\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"2421\",\n" +
                "\t\t\t\"areaname\": \"巴中市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"B\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"3150\",\n" +
                "\t\t\t\"areaname\": \"巴音郭楞蒙古自治州\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"B\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"1\",\n" +
                "\t\t\t\"areaname\": \"北京市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"B\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"3145\",\n" +
                "\t\t\t\"areaname\": \"博尔塔拉蒙古自治州\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"B\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"123\",\n" +
                "\t\t\t\"areaname\": \"保定市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"B\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"2521\",\n" +
                "\t\t\t\"areaname\": \"毕节市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"B\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"1487\",\n" +
                "\t\t\t\"areaname\": \"滨州市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"B\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"3110\",\n" +
                "\t\t\t\"areaname\": \"北屯市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"B\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"2616\",\n" +
                "\t\t\t\"areaname\": \"保山市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"B\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"2206\",\n" +
                "\t\t\t\"areaname\": \"白沙黎族自治县\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"B\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"634\",\n" +
                "\t\t\t\"areaname\": \"白城市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"B\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"621\",\n" +
                "\t\t\t\"areaname\": \"白山市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"B\"\n" +
                "\t\t}],\n" +
                "\t\t\"C\": [{\n" +
                "\t\t\t\"areaid\": \"2660\",\n" +
                "\t\t\t\"areaname\": \"楚雄彝族自治州\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"C\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"842\",\n" +
                "\t\t\t\"areaname\": \"常州市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"C\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"580\",\n" +
                "\t\t\t\"areaname\": \"长春市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"C\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"2755\",\n" +
                "\t\t\t\"areaname\": \"昌都市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"C\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"2209\",\n" +
                "\t\t\t\"areaname\": \"澄迈县\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"C\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"2278\",\n" +
                "\t\t\t\"areaname\": \"成都市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"C\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"564\",\n" +
                "\t\t\t\"areaname\": \"朝阳市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"C\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"2208\",\n" +
                "\t\t\t\"areaname\": \"昌江黎族自治县\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"C\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"1093\",\n" +
                "\t\t\t\"areaname\": \"滁州市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"C\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"1130\",\n" +
                "\t\t\t\"areaname\": \"池州市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"C\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"1883\",\n" +
                "\t\t\t\"areaname\": \"郴州市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"C\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"1861\",\n" +
                "\t\t\t\"areaname\": \"常德市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"C\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"1799\",\n" +
                "\t\t\t\"areaname\": \"长沙市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"C\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"165\",\n" +
                "\t\t\t\"areaname\": \"承德市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"C\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"2062\",\n" +
                "\t\t\t\"areaname\": \"潮州市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"C\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"177\",\n" +
                "\t\t\t\"areaname\": \"沧州市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"C\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"373\",\n" +
                "\t\t\t\"areaname\": \"赤峰市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"C\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"areaid\": \"247\",\n" +
                "\t\t\t\"areaname\": \"长治市\",\n" +
                "\t\t\t\"dredge\": \"0\",\n" +
                "\t\t\t\"firstletter\": \"C\"\n" +
                "\t\t}]\n" +
                "\t}\n" +
                "}";
        mGson = new Gson();
        citySBean = mGson.fromJson(json, CitySBean.class);
        MainActivity.this.citySBean = citySBean;

    }

    private ArrayList<CityItemBean> arrayList = new ArrayList();
    private ArrayList<String> city_arrayList = new ArrayList();

    private void iniview() {
        RmCityAdapter rmCityAdapter = new RmCityAdapter(this);
        GridLayoutManager gridLayoutManagers = new GridLayoutManager(this, 4);
        recycler_city.setLayoutManager(gridLayoutManagers);
        recycler_city.setAdapter(rmCityAdapter);


        cityAdapter = new CityAdapter(this);
        linearLayoutManager = new LinearLayoutManager(this);
        rcv_one.setLayoutManager(linearLayoutManager);
        rcv_one.setAdapter(cityAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        rcv_two.setLayoutManager(linearLayoutManager);
        rcv_two.setAdapter(cityAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        rcv_three.setLayoutManager(linearLayoutManager);
        rcv_three.setAdapter(cityAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        rcv_four.setLayoutManager(linearLayoutManager);
        rcv_four.setAdapter(cityAdapter);
        cityAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, BaseRecyclerAdapter.CommonHolder holder, int position) {
                Toast.makeText(MainActivity.this, arrayList.get(position).getAreaname(), Toast.LENGTH_LONG).show();

            }
        });

        for (int i = 0; i < city.length; i++) {
            city_arrayList.add(city[i]);
        }
        rmCityAdapter.setNewData(city_arrayList);
//        for (int i = 0; i < 20; i++) {
//            arrayList.add(i+"////****");
//        }
//        cityAdapter.setNewData(arrayList);

    }
}
