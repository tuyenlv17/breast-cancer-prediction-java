/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.bcp.entity;

import java.util.Arrays;

/**
 *
 * @author tuyenlv
 */
public class Instance {
    public static String [] attributes = {"Clump Thickness","Uniformity of Cell Size","Uniformity of Cell Shape","Marginal Adhesion","Single Epithelial Cell Size","Bare Nuclei","Bland Chromatin","Normal Nucleoli","Mitoses"};
    public static String [] classLabel = {"2", "4"};
    private String id;
    private int[] attrVal;
    private int insClass;
    
    public Instance() {
    }

    public Instance(String id, int[] attrVal, int insClass) {
        this.id = id;
        this.attrVal = attrVal;
        this.insClass = insClass;
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

    public int getInsClass() {
        return insClass;
    }

    public void setInsClass(int insClass) {
        this.insClass = insClass;
    }    
    
    public void setInsClass(String insClassStr) {
        int index = -1;
        for(int i = 0; i < classLabel.length; i++) {
            if(classLabel[i].equals(insClassStr)) {
                this.insClass = i;
                break;
            }
        }
    }    

    @Override
    public String toString() {
        return "Instance{attrVal=" + Arrays.toString(attrVal) + ", insClass=" + insClass + '}';
    }
    
    
}
