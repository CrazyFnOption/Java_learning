package com.language_learning.Exception;

import org.junit.Test;


// 一般下面所表现的 不一样的地方就在于 catch  存在一个父子继承的优先情况
// 就像下面所存在的一样
// 一般适用try catch fially 一般适用处理编译时的异常
// 一般finally 一般是一定会被执行的代码
public class ExceptionTest1 {
    @Test
    public void test() {
        System.out.println(test1());
    }

    public int test1() {

        try {
            String str = "123";
            str = "abc";
            int num = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            System.out.println("throw NumberFormatException");
            //e.printStackTrace();

            // 下面设置的catch 这里也许还是有错误，或者还是在之前return掉了
            // 但是如果有一定要被执行的代码finally里面
            return 1;
        }
        catch(Exception e) {
            System.out.println("真的发生相关的错误呢");
            return 2;
        }
        finally {
            System.out.println("最终我还是处理了相关的异常");
        }
        return 0;
    }

    
}
