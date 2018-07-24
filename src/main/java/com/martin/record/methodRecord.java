package com.martin.record;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class methodRecord {

    public static Long TIME = null;

    public static ConcurrentHashMap<String,List<TimeResult>> s = new ConcurrentHashMap<>();

    public static synchronized void ss(String name, long time){
        TimeResult timeResult = new TimeResult(time);
       // System.out.println("method is "+name+" usetime is "+timeResult.toString());
        if(TIME==null){
            TIME = System.currentTimeMillis();
        }else if(System.currentTimeMillis()-TIME>=10000){
            TIME = System.currentTimeMillis();
            Map<String,List<TimeResult>> old = new ConcurrentHashMap<>();
            old = s;
            s =new ConcurrentHashMap<>();
            Set<String> ss = old.keySet();
            for(String a:ss){
                for(TimeResult re :old.get(a)){
                    System.out.println("method is "+a+" ustime is "+re);
                }
            }

        }
        if(s.containsKey(name)){
            s.get(name).add(timeResult);
        }else{
            List<TimeResult> resul = new ArrayList<>();
            resul.add(timeResult);
            s.put(name,resul);
        }

    }
}
