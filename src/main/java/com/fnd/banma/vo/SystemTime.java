package com.fnd.banma.vo;

public class SystemTime {

    public static Long getSystemTime(){
        long time = System.currentTimeMillis();
        return time;
    }

}
