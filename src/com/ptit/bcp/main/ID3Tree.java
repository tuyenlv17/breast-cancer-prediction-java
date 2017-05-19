/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.bcp.main;

import com.ptit.bcp.entity.ID3Node;
import com.ptit.bcp.entity.Instance;
import com.ptit.bcp.entity.Measure;
import com.ptit.bcp.utils.ID3Utils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tuyenlv
 */
public class ID3Tree {

    ID3Node root;

    public ID3Tree() {
        root = null;
    }

    public ID3Tree(List<Instance> instances) {
        this.root = new ID3Node();
        List<Integer> attributes = new ArrayList<>();
        for (int i = 0; i < Instance.attributes.length; i++) {
            attributes.add(i);
        }
        this.buildID3Tree(this.root, instances, attributes, 0);
    }

    public void buildID3Tree(ID3Node node, List<Instance> instances, List<Integer> attributes, int level) {
        int stopThredhold = 0;
        int totalIns = instances.size();
        int totalClass = Instance.classLabel.length;
        int[] classCnt = new int[totalClass];
        ID3Utils.countClass(instances, classCnt);
//        System.out.println("level " + level + " " + instances.size() + " " + attributes.size());
//        System.out.println(attributes);
        //leaf
        for (int i = 0; i < totalClass; i++) {
            if (totalIns - classCnt[i] <= stopThredhold) {
                node.setClassIndex(i);
                node.setAttribute(-1);
                node.setCntClass(classCnt);
                return;
            }
        }
        //no attribute left
        if (attributes.isEmpty()) {
            int maxI = 0;
            for (int i = 0; i < totalClass; i++) {
                if (classCnt[i] > classCnt[maxI]) {
                    maxI = i;
                }
            }
            node.setAttribute(-1);
            node.setClassIndex(maxI);
            node.setCntClass(classCnt);
            return;
        }
        //calculate entropy  
        int selectedAttr = -1;        
        double maxInfoGain = 0;
        for(Integer attribute : attributes) {            
            double infoGain = 0;
            for(int i = 1; i <= 10; i++) {
                List<Instance> subInstances = new ArrayList();
                for(Instance instance : instances) {
                    if(instance.getAttrVal()[attribute] == i) {
                        subInstances.add(instance);
                    }
                }
                if(subInstances.isEmpty()) {
                    continue;
                }
                infoGain -= ID3Utils.entropy(subInstances) * subInstances.size() / instances.size();
//                System.out.println(">>>>>>>>>>>>>>>>>>>>>>End entropy");
            }
            if(selectedAttr == -1 || infoGain > maxInfoGain) {
                selectedAttr = attribute;
                maxInfoGain = infoGain;
            }
//            System.out.println(">>>>>info gain " + attribute + " " + infoGain);
        }
        //go down
//        System.out.println(level +  " " + Instance.attributes[selectedAttr]);
        node.setAttribute(selectedAttr);
//        System.out.println(level + " >> " + node.getAttribute());
        List<Integer> childAttr = new ArrayList<>(attributes);
        childAttr.remove(new Integer(selectedAttr));
        for(int i = 1; i <= 10; i++) {
            List<Instance> subInstances = new ArrayList();            
            for(Instance instance : instances) {
                if(instance.getAttrVal()[selectedAttr] == i) {
                    subInstances.add(instance);
                }
            }
            if(subInstances.size() == 0) {
                continue;
            }
            ID3Node child = new ID3Node();
            node.getChildren().add(child);
            node.getEdgeVal().add(i);            
            buildID3Tree(child, subInstances, childAttr, level + 1);
        }
        
    }

    public int predict(Instance instance) {
//        return 0;
//        System.out.println("root " + root.getAttribute());
        return predict(instance, root, 0);
    }
    
    public int predict(Instance instance, ID3Node node, int level) {        
//        System.out.println("level " + level + " " + node.getAttribute());
        if(node.getAttribute() == -1) {
            return node.getClassIndex();
        }
        int nodeAttr = node.getAttribute();
        int val = instance.getAttrVal()[nodeAttr];
//        System.out.println(val + " " + node.getChildren().size());
        for(int i = 0; i < node.getChildren().size(); i++) {
            int edgeVal = node.getEdgeVal().get(i);            
//            System.out.println(val + " " + edgeVal);
            if(val == edgeVal) {
                ID3Node child = node.getChildren().get(i);
                return predict(instance, child, level + 1);
            }
        }
        throw new NullPointerException();
    } 

    public Measure evaludate(List<Instance> instances) {//benign: negative
        int[][] conMatrix = new int[Instance.classLabel.length][Instance.classLabel.length];
        for (Instance instance : instances) {
            int predicted;
            try {
                predicted = predict(instance);
                conMatrix[instance.getInsClass()][predicted]++;
            } catch (NullPointerException e) {
                conMatrix[instance.getInsClass()][1 - instance.getInsClass()]++;
//                System.out.println("Missing value " + instance.getId());
            }
            
//            System.out.println(instance + " " + predicted);            
        }
        return new Measure(conMatrix[1][1], conMatrix[0][1], conMatrix[0][0], conMatrix[1][0]);
    }
}
