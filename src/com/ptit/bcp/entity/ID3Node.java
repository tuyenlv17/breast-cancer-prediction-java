/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.bcp.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tuyenlv
 */
public class ID3Node {
    int classIndex;
    int attribute;
    int[] cntClass;
    List<ID3Node> children;
    List<Integer> edgeVal;

    public ID3Node() {
        children = new ArrayList<ID3Node>();
        edgeVal = new ArrayList();
    }
        
    public int getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
    }

    public int[] getCntClass() {
        return cntClass;
    }

    public void setCntClass(int[] cntClass) {
        this.cntClass = cntClass;
    }

    public List<ID3Node> getChildren() {
        return children;
    }

    public void setChildren(List<ID3Node> children) {
        this.children = children;
    }

    public List<Integer> getEdgeVal() {
        return edgeVal;
    }

    public void setEdgeVal(List<Integer> edgeVal) {
        this.edgeVal = edgeVal;
    }
        

    public int getAttribute() {
        return attribute;
    }

    public void setAttribute(int attribute) {
        this.attribute = attribute;
    }
        
    
}
