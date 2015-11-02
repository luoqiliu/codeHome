package com.holydota.common.tool.math.sort.content;

import com.holydota.common.tool.math.sort.api.Sort;

public class Content {
    Sort    so;
    boolean isAsc = true;
    int[]   a;

    public void setSo(Sort so) {
        this.so = so;
    }

    //是否升序
    public void setAsc(boolean isAsc) {
        this.isAsc = isAsc;
    }

    public void setA(int[] a) {
        this.a = a;
    }

    public int Sort() {
        if (so != null) {
            long begintime = System.nanoTime();
            //运算代码
            int result = so.sort(a, isAsc);
            long endtime = System.nanoTime();
            long costTime = (endtime - begintime) / 1000000;
            System.out.println(so.getClass().toString() + (isAsc ? " asc " : " desc ") + " calc cost time:" + costTime
                               + " ms");
            return result;
        } else {
            return -1;
        }
    }
}
