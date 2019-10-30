package com.algorithm_learning.sort;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class erluguibing {

    private static void quick_sort(int left, int right,double[] data) {
        if (left < right) {
            int index = part_sort(left, right,data);
            quick_sort(left, index - 1,data);
            quick_sort(index + 1, right,data);
        }
    }

    private static int part_sort(int left, int right,double[] data) {
        int mid = get_mid(left,right,data);
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
    private static int get_mid (int left, int right, double[] data) {
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

    private static void merge(double[] data1, double[] data2, double[] tmp) {
        int i = 0,j = 0;
        int tot = 0;
        while (i < data1.length || j < data2.length) {
            if (data1[i] < data2[j]) {
                tmp[tot] = data1[i];
                i++;
                tot++;
                if (i == data1.length) break;
            }
            if (data1[i] > data2[j]) {
                tmp[tot] = data2[j];
                tot++;
                j++;
                if (j == data2.length) break;
            }
        }

        while (i < data1.length) {
            tmp[tot++] = data1[i++];
        }
        while (j < data2.length) {
            tmp[tot++] = data2[j++];
        }
    }

    @Test
    public void test() {
        double[] t1 = {1.2,3.4,5.4,7.1,13.6,19.8};
        double[] t2 = {2.3,2.6,5.3,7.8,9.5,17.4};
        double[] tmp = new double[12];
        merge(t1,t2,tmp);
        for (double i : tmp) System.out.print(i + " ");
    }

    public static void main(String[] args) throws IOException {
        initialize init = new initialize(20000);
        double [] data = init.getData();


        double[][] num = new double[20][1000];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 1000; j++) {
                num[i][j] = data[i * 1000 + j];
            }
            quick_sort(0,num[i].length - 1,num[i]);
        }

        double[] ss = new double[num[0].length + num[1].length];
        merge(num[0],num[1],ss);


        double[] tmp;
        for (int i = 2; i < 20; i++) {
            tmp = new double[ss.length + num[i].length];
            merge(ss,num[i],tmp);
            ss = tmp;
        }

        System.out.print(ss.length);
        FileWriter fiw = new FileWriter(new File("/Users/wangshuxiao/Desktop/Java_learning/src/com/algorithm_learning/sort/text.txt"));
        String str;

        for (int i = 0; i < ss.length; i++) {
            str = "" + ss[i] + "\n";
            fiw.write(str);
        }

    }
}
