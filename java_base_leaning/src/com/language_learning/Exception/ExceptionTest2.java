package com.language_learning.Exception;

import org.junit.Test;

import java.time.Instant;


// 总而言之 try catch finally 是将异常捕获，并且加以处理
// 而throw 仅仅是将异常抛出来，等待处理

// 这个地方还是需要注意的 子类抛出来的异常一定要比父类要小
public class ExceptionTest2 {

    @Test
    public void test1() throws Exception{
        String str = "123";
        str = "abc";
        int num = Integer.parseInt(str);
        System.out.println(num);
    }

    // 计算的就是本初子午线线相应的时间
    @Test
    public void test() {
        Instant instant = Instant.now();
        System.out.println(instant);
    }
}
