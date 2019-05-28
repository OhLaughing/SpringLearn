package com.example;

/**
 * Created by gustaov on 2019/5/19.
 */
public class NumberOf1 {
    public static int NumberOf1(int n) {
        int number = 0;
        while (n != 0) {
            if ((n & 0x01) == 1) {
                number++;
            }
            n = n >>> 1;
        }
        return number;
    }

    public static void main(String[] args) {
        System.out.println(NumberOf1(15));
    }
}
