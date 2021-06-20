package com.lagou.test;

import com.lagou.constructor.Computer;
import com.lagou.constructor.ComputerBuilder;

public class ConstructorTest {


    public static void main(String[] args) {

        ComputerBuilder computerBuilder = new ComputerBuilder();
        computerBuilder.installDisplayer("显示屏");
        computerBuilder.installMainUnit("主机");
        computerBuilder.installMouse("鼠标");
        computerBuilder.installKeyboard("键盘");
        Computer computer = computerBuilder.build();
        System.out.println(computer);
    }


}
