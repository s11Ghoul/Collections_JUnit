package org.example;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        Set<Integer> numbers = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1<o2){
                    return 1;
                } else if (o2<o1){
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        for(int i=0;i<100;i++){
            numbers.add(i);
        }
        for(int i:numbers){
            System.out.println(i);
        }
    }




}





