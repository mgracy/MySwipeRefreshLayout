package com.example.glmgr.myswiperefreshlayout;

/**
 * Created by glmgr on 2016/6/3.
 */
public class testExchange {
    String str = new String("good");
    char[] ch = {'a', 'b', 'c'};

    public static void main(String args[]) {
        testExchange ex = new testExchange();
        ex.change(ex.str, ex.ch);
        System.out.print(ex.str + " and ");
        System.out.print(ex.ch);
    }

    public void change(String str, char ch[]) {
        str = "test ok";
        ch[0] = 'g';
    }
}