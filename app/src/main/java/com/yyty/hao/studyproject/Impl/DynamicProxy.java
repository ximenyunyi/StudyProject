package com.yyty.hao.studyproject.Impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author 周祥浩
 * @version V1.0
 * @Description 动态代理
 * @e-mail yyty208@gmail.com
 */

public class DynamicProxy implements InvocationHandler {

    private Object obj;

    public DynamicProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Object="+proxy.getClass().getName());
        System.out.println("obj="+obj.getClass().getName());
        Object result = method.invoke(obj, args);
        return null;
    }
}
