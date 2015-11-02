package com.holydota.common.tool.math.sort.impl;

import com.holydota.common.tool.math.sort.api.Sort;

public class BuddleSort implements Sort {

    @Override
    public int sort(int[] needSort, boolean isAsc) {
        if (needSort == null || needSort != null && needSort.length == 0) {
            return -1;
        }
        for (int i = 0; i < needSort.length; i++) {
            for (int j = i + 1; j < needSort.length; j++) {
                if (isAsc) {
                    if (needSort[i] > needSort[j]) {
                        int tmp = needSort[i];
                        needSort[i] = needSort[j];
                        needSort[j] = tmp;
                    }
                } else {
                    if (needSort[i] < needSort[j]) {
                        int tmp = needSort[i];
                        needSort[i] = needSort[j];
                        needSort[j] = tmp;
                    }
                }

            }
        }
        return 0;
    }
}
