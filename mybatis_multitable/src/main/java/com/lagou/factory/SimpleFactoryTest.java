package com.lagou.factory;

public class SimpleFactoryTest {

    public static void main(String[] args) {
        AbstractComputer computer = ComputerFactory.creatComputer("hp");
        computer.start();



    }
}
