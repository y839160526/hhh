/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wyps.entity;

/**
 *
 * @author ying.chen
 */
public class Field {
    private boolean isUse=false;

    public boolean isIsUse() {
        return isUse;
    }

    public void setIsUse(boolean isUse) {
        this.isUse = isUse;
    }
    private Path fieldVal=new Path();
    private String fieldName="";
    private Path fieldGet=new Path();
    
    public Path getFieldVal() {
        return fieldVal;
    }

    public void setFieldVal(Path fieldVal) {
        this.fieldVal = fieldVal;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Path getFieldGet() {
        return fieldGet;
    }

    public void setFieldGet(Path fieldGet) {
        this.fieldGet = fieldGet;
    }
    
    
}
