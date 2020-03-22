package com.wyps.entity;

import java.util.List;

public class Conf {

    private LoginConf loginconf;
    private ListConf listConf;
    private List<Field> fields;
    private int waitTime = 0;
    private String confName = "";

    public String getConfName() {
        return confName;
    }

    public void setConfName(String confName) {
        this.confName = confName;
    }

    public Conf() {
    }

    public Conf(LoginConf loginconf, ListConf listConf, List<Field> fields) {
        this.loginconf = loginconf;
        this.listConf = listConf;
        this.fields = fields;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public LoginConf getLoginconf() {
        return loginconf;
    }

    public void setLoginconf(LoginConf loginconf) {
        this.loginconf = loginconf;
    }

    public ListConf getListConf() {
        return listConf;
    }

    public void setListConf(ListConf listConf) {
        this.listConf = listConf;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public static void main(String[] args) {
//        Conf conf = new Conf();
//        ListConf list = new ListConf();
//        list.setPageCount(11);
//        Path path = new Path();
//        path.setType(PathType.tagName);
//        path.setValue("http://www.iteye.com/problems/48573");
//        list.setNextUrl(path);
//        conf.setListConf(list);
//        JSONObject json = JSONObject.fromObject(conf);
//        System.out.println(json.toString());
//        //String json="{\"fields\":[],\"listConf\":null,\"loginconf\":null}";
//        JSONObject jsonObject = JSONObject.fromObject(json.toString());
//        conf = (Conf) JSONObject.toBean(jsonObject, Conf.class);
    }

}
