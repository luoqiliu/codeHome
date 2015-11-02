package com.holydota.common.tool.math.sort.impl;

import com.holydota.common.tool.math.sort.api.Sort;

public class QuickSort implements Sort {

    @Override
    public int sort(int[] needSort, boolean isAsc) {
        if (needSort == null || needSort != null && needSort.length == 0) {
            return -1;
        }
        qsort(needSort, 0, needSort.length - 1, isAsc);
        return 0;
    }

    private static void qsort(int[] n, int left, int right, boolean isAsc) {
        int pivot;
        if (left < right) {
            pivot = partition(n, left, right, isAsc);
            qsort(n, left, pivot - 1, isAsc);
            qsort(n, pivot + 1, right, isAsc);

        }

    }

    private static int partition(int[] n, int left, int right, boolean isAsc) {
        int pivotkey = n[left];
        while (left < right) {
            if (isAsc) {
                while (left < right && n[right] >= pivotkey)
                    right--;
                n[left] = n[right];
                while (left < right && n[left] <= pivotkey)
                    left++;
                n[right] = n[left];
            } else {
                while (left < right && n[right] <= pivotkey)
                    right--;
                n[left] = n[right];
                while (left < right && n[left] >= pivotkey)
                    left++;
                n[right] = n[left];
            }

        }
        n[left] = pivotkey;
        return left;
    }
}
