package com.wsx.web.Servlet;

import javax.servlet.*;
import java.io.IOException;

// 尽量不要在Servlet里面定义成员变量，如果必须要定义的话，那么就不要去修改这个值，因为线程之间的不同步，
// 可能会造成线程不安全
public class demo1 implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("hello servlet!!!");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    // 只有在服务器正常关闭时，才会执行destory方法
    @Override
    public void destroy() {

    }
}
