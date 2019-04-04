package com.version.myapplication;

public class CityItemBean {
    /**
     * areaname : 城市名称
     * dredge : 1 开通 0 未开通
     * firstletter : B
     * areaid : id
     */

    private String areaname;
    private String dredge;
    private String firstletter;
    private String areaid;
public CityItemBean(String areaname, String dredge, String firstletter, String areaid){
    this.areaname= areaname;
    this.dredge= dredge;
    this.firstletter= firstletter;
    this.areaid= areaid;

    }
    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getDredge() {
        return dredge;
    }

    public void setDredge(String dredge) {
        this.dredge = dredge;
    }

    public String getFirstletter() {
        return firstletter;
    }

    public void setFirstletter(String firstletter) {
        this.firstletter = firstletter;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }
}
