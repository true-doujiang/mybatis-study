package com.yhh.mybatisplus.sample.crud;

class Tool2 {
    //静态泛型 如下
    public static <W> W print(W a) {
        System.out.printf("print : " + a);
        return a;
    }
}


public class Tool {
    public static void main(String[] args) {
        Tool2.<Double>print(11d).doubleValue();
    }
}