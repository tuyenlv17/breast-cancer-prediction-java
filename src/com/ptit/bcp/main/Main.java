/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.bcp.main;

import com.ptit.bcp.entity.Measure;
import com.ptit.bcp.entity.Instance;
import com.ptit.bcp.entity.TreeNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tuyenlv
 */
public class Main {    
    public static ID3Tree model;
    
    public static ID3Tree training(List <Instance> instances) {
        
    }
    
    public static int predict(Instance instance) {
        
    }
    
    public static Measure evaludate(List <Instance> instances) {//benign: negative
        int tp = 0, fp = 0, tn = 0, fn = 0;
        for(Instance instance : instances) {
            int predicted = predict(instance);
            if(predicted == 2) {
                if(instance.getType() == 2) {
                    tn++;
                } else {
                    fn++;
                }
            } else {
                if(instance.getType() == 4) {
                    tp++;
                } else {
                    fp++;
                }
            }
        }
        return new Measure(tp, fp, tn, fn);
    }
    
    public static void kFold(List <Instance> instances, int k) {
        if(k <= 0 || k > 10) {
            System.out.println("Fold not valid!");
            return;
        }
        Collections.shuffle(instances);
        Measure meanMeasure = new Measure();
        int foldSize = instances.size() / k;
        for(int i = 0; i < k; i++) {
            List <Instance> testSet = instances.subList(i * foldSize, (i + 1) * foldSize);
            List <Instance> trainSet = new ArrayList(instances);
            trainSet.removeAll(testSet);
            training(trainSet);
            Measure measure = evaludate(testSet);
            System.out.println(measure);
        }        
    }
    
    public static void main(String[] args) {        
        try {
            System.out.println("Load data...");
            Scanner sc = new Scanner(new File(args[0]));
            List <Instance> instances = new ArrayList();
            while(sc.hasNext()) {
                String line = sc.nextLine();
                if(!line.contains(",") || line.contains("?")) {
                    continue;
                }
                String [] attrValStr = line.split(",");
                int[] attrVal = new int[9];
                for(int i = 1; i < 10; i++) {
                    attrVal[i - 1] = Integer.parseInt(attrValStr[i]);
                }
                Instance instance = new Instance(attrValStr[0], attrVal, Integer.parseInt(attrValStr[10]));
                instances.add(instance);
            }
            if(args[0].equals("predict")) {
                training(instances);
            } else {
                kFold(instances, 10);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File " + args[0] + " not found!");
        }
    }
}
