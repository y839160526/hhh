/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wyps.entity.tools;

/**
 *
 * @author ying.chen
 */
public class CommonUtils {
    public static boolean isEmpty(String v) {
        if (v == null || v.equals("")) {
            return true;
        }
        return false;
    }
}
