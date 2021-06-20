package com.lagou.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKDynamicProxy  implements InvocationHandler
{
    private Person person;

    public JDKDynamicProxy(Person person) {
        this.person = person;
    }

    public Object proxyPerson(){
        Object proxyInstance = Proxy.newProxyInstance(person.getClass().getClassLoader(),
                person.getClass().getInterfaces(), this);

        return proxyInstance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("对原方法前增强");
        Object invoke = method.invoke(person, args);

        System.out.println("对原方法后增强");
        return invoke;
    }
}
