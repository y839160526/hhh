package com.wyps.entity;

public class User {

    private String id;
    private String username;
    private String password;
    private String email;
    private boolean isLogSuc = false;
    private int vip = 0;

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogSuc() {
        return isLogSuc;
    }

    public void setLogSuc(boolean isLogSuc) {
        this.isLogSuc = isLogSuc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsLogSuc() {
        return isLogSuc;
    }

    public void setIsLogSuc(boolean isLogSuc) {
        this.isLogSuc = isLogSuc;
    }

}
