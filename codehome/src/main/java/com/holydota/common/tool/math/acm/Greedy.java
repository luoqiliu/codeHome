package com.holydota.common.tool.math.acm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 背包贪心法
 * 
 * @author ***
 * 
 */
public class Greedy {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the number of objects(请输入物品的数量：):");
        int n = in.nextInt();
        int[] w = new int[n]; // 物品重量数组
        int[] v = new int[n]; // 物品价钱数组
        System.out.println("Now, please enter the weight of these objects(现在请输入这些物品的重量：)");
        for (int i = 0; i < n; i++) {
            w[i] = in.nextInt();
        }
        System.out.println("Now, please enter the value of these objects(现在请输入这些物品的价值：)");
        for (int i = 0; i < n; i++) {
            v[i] = in.nextInt();
        }
        System.out.println("Now, please enter the capacity of the pack(现在请输入背包的容量：)");
        int c = in.nextInt();
        /**
         * 按单位重量价值r[i] = v[i] / w[i]降序排列
         * 
         * ps:排序用到了选择排序，详情请查看选择排序
         */
        double startTime = System.currentTimeMillis();
        double[] r = new double[n];
        int[] index = new int[n];
        for (int i = 0; i < n; i++) {
            r[i] = (double) v[i] / (double) w[i];
            index[i] = i;
        }
        double temp = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (r[i] < r[j]) {
                    temp = r[i];
                    r[i] = r[j];
                    r[j] = temp;
                    int x = index[i];
                    index[i] = index[j];
                    index[j] = x;
                }
            }
        }
        /**
         * 排序后的重量和价值分别存到w1[]和v1[]中
         */
        int[] w1 = new int[n];
        int[] v1 = new int[n];
        for (int i = 0; i < n; i++) {
            w1[i] = w[index[i]];
            v1[i] = v[index[i]];
        }
        /**
         * 初始化解向量x[n]
         */
        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = 0;
        }
        /**
         * 求解并打印解向量
         */
        for (int i = 0; i < n; i++) {
            if (w1[i] < c) {
                x[i] = 1;
                c = c - w1[i];
            }
        }
        System.out.println("The solution vector is(解向量是：)" + Arrays.toString(x));
        /**
         * 根据解向量求出背包中存放物品的最大价值并打印
         */
        int maxValue = 0;
        for (int i = 0; i < n; i++) {
            if (x[i] == 1)
                maxValue += v1[i];
        }
        double endTime = System.currentTimeMillis();
        System.out.println("Now, the largest values of objects in the pack is(背包中物品的最大价值为：)" + maxValue);
        System.out.println("Basic Statements take(基本语句用时)" + (endTime - startTime) + " milliseconds!");
    }
}
