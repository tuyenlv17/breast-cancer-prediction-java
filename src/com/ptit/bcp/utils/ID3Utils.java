/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.bcp.utils;

import com.ptit.bcp.entity.Instance;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author tuyenlv
 */
public class ID3Utils {
    public static double entropy(List <Instance> instances) {
//        //System.out.println(instances);
        double res = 0;
        int totalClasses = Instance.classLabel.length;
        int [] cnt = new int[totalClasses];
        for(Instance instance : instances) {
            cnt[instance.getInsClass()]++;
        }
        for(int i = 0; i < totalClasses; i++) {            
            double pi = (double)cnt[i] / instances.size();
            //System.out.println(res + " << " + pi);
            if(cnt[i] == 0) {
                continue;
            }
            res -=  pi * Math.log(pi) / Math.log(2);            
            //System.out.println(res + " >> " + pi);
        }
        //System.out.println("entropy = " + res);
        return res;
    }
    
    public static void countClass(List<Instance> instances, int[] classCnt) {
        for(Instance instance : instances) {
            classCnt[instance.getInsClass()]++;
        }
    }
}
