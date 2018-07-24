package com.martin.count;


import com.martin.record.TimeResult;
import com.martin.record.methodRecord;

public final class TimeCount {
    
    public static void methodTakesTime(long startTime,String methodid){
        long endTime = System.nanoTime();
//        if(endTime-startTime>=0){
//            TimeResult timeResult = new TimeResult(endTime-startTime);
//            System.out.println("method is "+methodid+" time is "+timeResult.getTime()+" useTime is "+timeResult);
//        }
        //System.out.println("method is: "+methodid+" use Time is "+(endTime-startTime)+" ns");
        methodRecord.ss(methodid,(endTime-startTime));


    }
}
