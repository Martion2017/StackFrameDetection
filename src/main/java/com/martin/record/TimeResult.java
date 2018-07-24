package com.martin.record;

import java.io.Serializable;

public class TimeResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private long time;

    private long takeTime;

    public TimeResult(long takeTime) {
        this.time = System.currentTimeMillis();
        this.takeTime = takeTime;
    }

    public long getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(long takeTime) {
        this.takeTime = takeTime;
    }

    public long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "result{" +
                "time=" + time +
                ", takeTime=" + takeTime +
                '}';
    }
}
