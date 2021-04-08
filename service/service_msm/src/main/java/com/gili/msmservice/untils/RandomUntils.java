package com.gili.msmservice.untils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RandomUntils {
    private static final Random random=new Random();
    private static final DecimalFormat fourformat=new DecimalFormat("0000");
    private static final DecimalFormat sixformat=new DecimalFormat("000000");

    public static String getFourFormat(){
        return fourformat.format(random.nextInt(10000));
    }
    public static String getSixFormat(){
        return sixformat.format(random.nextInt(1000000));
    }


    public static ArrayList getRandom(List list,int n){
        Random random=new Random();
        HashMap<Object, Object> hashMap=new HashMap<>();

        for(int i=0; i<list.size();i++){
            int number=random.nextInt(100)+1;
            hashMap.put(number,i);
        }
        Object[] robjs=hashMap.values().toArray();
        ArrayList r=new ArrayList();

        for(int i=0; i<list.size();i++) {
            r.add(list.get((Integer) robjs[i]));
            System.out.println(list.get((Integer) robjs[i]));
        }
        return r;
    }
}
