package com.algorithm_learning.seach;

import com.algorithm_learning.sort.initialize;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class SeachTest {

    private double[] data;

    private double init(int n) {
        initialize init = new initialize(n);
        data = init.getData();
        Random r = new Random();
        int l = r.nextInt(n);
        return data[l];
    }

    @Test
    public void shunxuTest() {
        double num = init(2000);
        double starttime = System.currentTimeMillis();
        for (int i = 0; i < data.length; i++) {
            if (num == data[i]) {
                System.out.println("查找成功...万岁...\n该数在下标" + i + "\n值为" + num);

                break;
            }
        }
        double endTime = System.currentTimeMillis();
        System.out.println((endTime - starttime));
    }

    @Test
    public void binary_search() {
        double num = init(100000);
        int l = 0, r = data.length;
        int last = -1;
        Arrays.sort(data);
        double starttime = System.currentTimeMillis();
        while (l < r) {
            int mid = (l + r) / 2;
            if (data[mid] > num) r = mid - 1;
            else if (data[mid] < num) l = mid;
            else if (data[mid] == num){
                last = mid;
                break;
            }
        }
        if (last == -1) System.out.println("查找失败");
        else System.out.println("查找成功 该值的位置在" + last + "\n且该值为" + num);

        double endTime = System.currentTimeMillis();
        System.out.println((endTime - starttime));
    }

    @Test
    public void binaryTree() {
        double num = init(100000);

        binary_tree binary_tree = new binary_tree(data);
        double starttime = System.currentTimeMillis();
        if (binary_tree.search(num)) {
            System.out.println("查找成功...");
        }
        else System.out.println("查找失败...");
        double endTime = System.currentTimeMillis();
        System.out.println((endTime - starttime));

    }

}
