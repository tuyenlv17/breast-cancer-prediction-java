/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.bcp.entity;

/**
 *
 * @author tuyenlv
 */
public class Instance {
    public static String [] attributes = {};
    public static String [] classLabel = {};
    private String id;
    private int[] attrVal;
    private int type;
    
    public Instance() {
    }

    public Instance(String id, int[] attrVal, int type) {
        this.id = id;
        this.attrVal = attrVal;
        this.type = type;
    }       

    public static String[] getAttributes() {
        return attributes;
    }

    public static void setAttributes(String[] attributes) {
        Instance.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int[] getAttrVal() {
        return attrVal;
    }

    public void setAttrVal(int[] attrVal) {
        this.attrVal = attrVal;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    
    
}
