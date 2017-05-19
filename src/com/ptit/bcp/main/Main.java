/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.bcp.main;

import com.ptit.bcp.entity.Measure;
import com.ptit.bcp.entity.Instance;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author tuyenlv
 */
public class Main {
    
    public static void kFold(List <Instance> instances, int k) {
        if(k <= 0 || k > 10) {
            System.out.println("Fold not valid!");
            return;
        }
        //split fold
        List <Instance> negIns = new ArrayList();
        List <Instance> posIns = new ArrayList();
        for(Instance instance : instances) {
            if(instance.getInsClass() == 0) {
                negIns.add(instance);
            } else {
                posIns.add(instance);
            }
        }
        int negStep = negIns.size() / k, posStep = posIns.size() / k;
//        Collections.shuffle(instances);
        Measure meanMeasure = new Measure();
        System.out.println(instances.subList(0, 10));
        int foldSize = instances.size() / k;
        int[][] conMatrix = new int[Instance.classLabel.length][Instance.classLabel.length];
        double mPrecision = 0, mRecal = 0, mF1 = 0, mAccuracy = 0;
        for(int i = 0; i < k; i++) {
            System.out.println("Trainning fold " + i);
            List <Instance> negTestSet = instances.subList(i * negStep, (i + 1) * negStep);
            List <Instance> posTestSet = instances.subList(i * posStep, (i + 1) * posStep);
            List <Instance> trainSet = new ArrayList(instances);
            trainSet.removeAll(negTestSet);
            trainSet.removeAll(posTestSet);
//            System.out.println("Train size " + trainSet.size());
//            System.out.println("Test size " + testSet.size());
            ID3Tree kModel = new ID3Tree(trainSet);
//            System.out.println("Evaluating...");
            Measure negMeasure = kModel.evaludate(negTestSet);
            Measure posMeasure = kModel.evaludate(posTestSet);
            System.out.println("Negative" + negMeasure);
            System.out.println("Positive" + posMeasure);
            Measure measure = negMeasure;            
            mAccuracy += measure.getAccuracy();
            mPrecision += measure.getPrecision();
            mRecal += measure.getRecall();
            mF1 += measure.getF1();                        
        }
        meanMeasure.setAccuracy(mAccuracy / k);
        meanMeasure.setPrecision(mPrecision / k);
        meanMeasure.setRecall(mRecal / k);
        meanMeasure.setF1(mF1 / k);
        System.out.println(meanMeasure);
    }
    
    public static void predict(List <Instance> instances) {
        ID3Tree model = new ID3Tree(instances);
    }
    
    public static void main(String[] args) {   
        if(args.length == 0) {
            String [] params =  {"breast-cancer-wisconsin.data", "k-fold"};
            args = params;
        }
        try {
            System.out.println("Load data...");
            Scanner sc = new Scanner(new File(args[0]));
            List <Instance> instances = new ArrayList();
            while(sc.hasNext()) {
                String line = sc.nextLine();
                
                if(!line.contains(",")) {
                    continue;
                }
                String [] attrValStr = line.split(",");
                int[] attrVal = new int[9];
                for(int i = 1; i < 10; i++) {                    
                    if(attrValStr[i].equals("?")) {
                        Random random = new Random(System.currentTimeMillis());
                        attrVal[i - 1] = random.nextInt(10) + 1;
                        continue;
                    }
                    attrVal[i - 1] = Integer.parseInt(attrValStr[i]);
                }
                Instance instance = new Instance(attrValStr[0], attrVal, Integer.parseInt(attrValStr[10]));
                instance.setInsClass(attrValStr[10]);
                instances.add(instance);
            }
            if(args[1].equals("predict")) {
                predict(instances);
            } else {
                kFold(instances, 10);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File " + args[0] + " not found!");
        }
    }
}
