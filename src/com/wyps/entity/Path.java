package com.wyps.entity;

public class Path {

    

    private PathType type;

    public PathType getType() {
        return type;
    }

    public void setType(PathType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;
}
