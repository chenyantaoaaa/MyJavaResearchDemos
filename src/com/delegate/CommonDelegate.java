package com.delegate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yantao.chen
 */
public class CommonDelegate implements InvocationHandler {

    private Object target;//需要代理的类

    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.Log(method.getName());
        Object obj = method.invoke(this.target,args);
        return obj;
    }

    private void Log(String methodName){
        System.out.println("进行日志记录方法" + methodName);
    }
}
