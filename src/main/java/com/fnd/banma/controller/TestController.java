package com.fnd.banma.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestController {

    /*全局变量 总票数*/
    private static Integer sum = 100;

    public static void main(String[] args) throws Exception {
        short a = 1;
        short b = 1;
        short c = ++a;
        System.out.println(c);


    }

    /*售票员*/
    public static class Conductor implements Runnable {
        private String name;

        public Conductor(String name) {
            this.name = name;
        }

        public void run() {
            synchronized (sum) {
                while (sum != 0) {
                    sum--;
                    System.out.println(name + "出售一张票 剩余:" + sum);
                }
            }
        }
    }

}


