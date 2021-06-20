package com.lagou.proxy;

public class ProxyTest {
    public static void main(String[] args) {
        System.out.println("原对象使用方法");
        Person person = new Bob();
        person.doSomething();

        System.out.println("~~~~~~~~~代理对象调用方法~~~~~~~~~~~~~~~~~~~~~");
        Person proxyPerson = (Person)new JDKDynamicProxy(new Bob()).proxyPerson();
        proxyPerson.doSomething();

    }
}
