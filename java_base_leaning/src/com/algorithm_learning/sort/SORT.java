package com.algorithm_learning.sort;


import java.util.Scanner;

public class SORT {

    private double [] data;

    private void maopao(int n) {

        initialize init = new initialize(n);
        double t = 0;
        this.data = init.getData();
        init.startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i ++) {
            for (int j = i + 1; j < n; j++) {
                if (data[i] > data[j]) {
                    t = data[i];
                    data[i] = data[j];
                    data[j] = t;
                }
            }
        }
        init.endTime = System.currentTimeMillis();
        System.out.println(init);
    }

    private void charu(int n) {
        initialize init = new initialize(n);
        data = init.getData();
        init.startTime = System.currentTimeMillis();
        for (int i = 1; i < n; i++) {
            double tmp = data[i];
            int j = i;
            while (j > 0 && tmp < data[j - 1]) {
                data[j] = data[j - 1];
                j--;
            }
            if (i != j) {
                data[j] = tmp;
            }
        }
        init.endTime = System.currentTimeMillis();
        System.out.println(init);
    }

    private void kuaisu(int n) {
        initialize init = new initialize(n);
        data = init.getData();
        init.startTime = System.currentTimeMillis();
        quick_sort(0,n - 1);
        init.endTime = System.currentTimeMillis();
        System.out.println(init);
    }

    private void quick_sort(int left, int right) {
        if (left < right) {
            int index = part_sort(left, right);
            quick_sort(left, index - 1);
            quick_sort(index + 1, right);
        }
    }

    private int part_sort(int left, int right) {
        int mid = get_mid(left,right);
        double tmp = data[mid];
        data[mid] = data[right];
        data[right] = tmp;
        double key = data[right];
        while (left < right) {
            while (left < right && data[left] <= key)
                ++ left;
            data[right] = data[left];
            while (left < right && data[right] >= key)
                -- right;
            data[left] = data[right];
        }
        data[right] = key;
        return right;
    }

    // 这里的意思就是三值取中法,取到中间大小的即可。
    private int get_mid (int left, int right) {
        int mid = (left + right) / 2;
        if (data[left] <= data[right]) {
            if (data[mid] < data[left]) return left;
            if (data[mid] > data[right]) return right;
            return mid;
        }
        else {
            if (data[mid] < data[right]) return right;
            if (data[mid] > data[left]) return left;
            return mid;
        }
    }

    private void xuanze(int n) {
        initialize init = new initialize(n);
        data = init.getData();
        init.startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            int Min = i;
            for (int j = i + 1; j < n; j++) {
                if (data[Min] > data[j]) Min = j;
            }
            if (Min != i) {
                double tmp = data[Min];
                data[Min] = data[i];
                data[i] = tmp;
            }
        }
        init.endTime = System.currentTimeMillis();
        System.out.println(init);
    }

    private void dui(int n) {
        initialize init = new initialize(n);
        data = init.getData();
        init.startTime = System.currentTimeMillis();
        for (int i = n / 2; i >= 0; i--)
            adjust(i,n);
        for (int i = n - 1; i > 0; i--) {
            double tmp = data[0];
            data[0] = data[i];
            data[i] = tmp;
            adjust(0,i);
        }
        init.endTime = System.currentTimeMillis();
        System.out.println(init);
    }

    private void adjust(int root, int len) {
        double tmp = data[root];
        int child = 2 * root;
        while (child < len) {
            if (child + 1 < len  && data[child] < data[child + 1]) child++;
            if (tmp >= data[child]) break;
            data[root] = data[child];
            root = child;
            child = 2 * child;
        }
        data[root] = tmp;
    }

    private void xier(int n) {
        initialize init = new initialize(n);
        data = init.getData();
        init.startTime = System.currentTimeMillis();
        for (int gap = n / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < n; i++) {
                double tmp = data[i];
                int j = i;
                while (j > gap - 1 && tmp < data[j - gap]) {
                    data[j] = data[j - gap];
                    j -= gap;
                }
                data[j] = tmp;
            }
        }
        init.endTime = System.currentTimeMillis();
        System.out.println(init);
    }

    private void guibing(int left,int right,double[] tmp) {
        if (left < right) {
            int mid = (left + right) / 2;
            guibing(left, mid, tmp);
            guibing(mid + 1, right, tmp);
            merge(left, mid, right, tmp);
        }
    }

    private void merge(int left, int mid, int right, double[] tmp) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            if (data[i] < data[j])
                tmp[t++] = data[i++];
            else tmp[t++] = data[j++];
        }

        while (i <= mid)
            tmp[t++] = data[i++];
        while (j <= right)
            tmp[t++] = data[j++];
        t = 0;
        while (left <= right)
            data[left++] = tmp[t++];
    }

    private void guibing(int n) {
        initialize init = new initialize(n);
        data = init.getData();
        double[] tmp = new double[n];
        init.startTime = System.currentTimeMillis();
        guibing(0,n - 1, tmp);
        init.endTime = System.currentTimeMillis();
        System.out.println(init);
    }

    public static void main (String[] args) {
        SORT s = new SORT();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println("冒泡 :");
        s.maopao(n);

        System.out.println("快速排序：");
        s.kuaisu(n);

        System.out.println("插入 :");
        s.charu(n);

        System.out.println("希尔 :");
        s.xier(n);

        System.out.println("选择排序 :");
        s.xuanze(n);

        System.out.println("堆排序：");
        s.dui(n);

        System.out.println("归并排序:");
        s.guibing(n);

    }
}
