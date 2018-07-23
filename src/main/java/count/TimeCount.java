package count;

public final class TimeCount {
    
    public static void methodTakesTime(long startTime,String methodid){
        long endTime = System.nanoTime();
        System.out.println("method is: "+methodid+" use Time is "+(endTime-startTime)+" ns");

    }
}
