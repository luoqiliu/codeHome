package com.holydota.common.tool.math.sort;

import java.util.Arrays;

import com.holydota.common.tool.math.sort.content.Content;
import com.holydota.common.tool.math.sort.impl.BuddleSort;
import com.holydota.common.tool.math.sort.impl.QuickSort;

public class test {
    public static void main(String[] args) {
        Content ct = new Content();
        int[] a = new int[10000];
        for (int i = 0; i < 10000; i++)
            a[i] = (int) (Math.random() * 10000);
        // int[] a = { 5, 2, 6, 12, 22, 4, 3, 23, 11, 23, 4, 56, 67, 888, 23, 2222, 421, 412, 3132, 1312, 4432, 534, 53,
        //        3453 };
        int[] buddle = a.clone();
        ct.setA(buddle);
        ct.setAsc(false);
        ct.setSo(new BuddleSort());
        ct.Sort();
        // printResult(buddle);
        int[] quick = a.clone();
        ct.setA(quick);
        ct.setAsc(true);
        ct.setSo(new QuickSort());
        ct.Sort();    
        //  printResult(quick);

    }

    public static void printResult(int[] result) {
        System.out.println(Arrays.toString(result));
    }
}
