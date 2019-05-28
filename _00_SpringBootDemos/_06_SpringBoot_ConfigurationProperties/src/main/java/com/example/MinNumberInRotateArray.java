package com.example;

/**
 * Created by gustaov on 2019/5/19.
 */
public class MinNumberInRotateArray {
    public static int minNumberInRotateArray(int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] < array[right]) {
                right = mid-1;
            } else {
                left = mid ;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array={7,1};
        System.out.println(minNumberInRotateArray(array));
    }
}
