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
public class Measure {
    private float accuracy, precision, f1, recall;
    private int truePos, falsePos, trueNeg, falseNeg;

    public Measure() {
        truePos = falsePos = trueNeg = falseNeg = 0;
    }
    
    public Measure(int truePos, int falsePos, int trueNeg, int falseNeg) {
        this.truePos = truePos;
        this.falsePos = falsePos;
        this.trueNeg = trueNeg;
        this.falseNeg = falseNeg;
        accuracy = (float)(truePos + trueNeg) / (truePos + trueNeg + falsePos + falseNeg);
        precision = (float)truePos / (truePos + falsePos);
        recall = (float)truePos / (truePos + falseNeg);
        f1 = precision * recall / (precision + recall);
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public float getPrecision() {
        return precision;
    }

    public void setPrecision(float precision) {
        this.precision = precision;
    }

    public float getF1() {
        return f1;
    }

    public void setF1(float f1) {
        this.f1 = f1;
    }

    public float getRecall() {
        return recall;
    }

    public void setRecall(float recall) {
        this.recall = recall;
    }

    public int getTruePos() {
        return truePos;
    }

    public void setTruePos(int truePos) {
        this.truePos = truePos;
    }

    public int getFalsePos() {
        return falsePos;
    }

    public void setFalsePos(int falsePos) {
        this.falsePos = falsePos;
    }

    public int getTrueNeg() {
        return trueNeg;
    }

    public void setTrueNeg(int trueNeg) {
        this.trueNeg = trueNeg;
    }

    public int getFalseNag() {
        return falseNeg;
    }

    public void setFalseNag(int falseNeg) {
        this.falseNeg = falseNeg;
    }

    @Override
    public String toString() {
        return "Measure{" + "accuracy=" + accuracy + ", precision=" + precision + ", f1=" + f1 + ", recall=" + recall + ", truePos=" + truePos + ", falsePos=" + falsePos + ", trueNeg=" + trueNeg + ", falseNeg=" + falseNeg + '}';
    }
        
    
}
