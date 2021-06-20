package com.lagou.factory;

public class ComputerFactory {

    public static AbstractComputer creatComputer(String type){
        AbstractComputer computer =null;

        switch (type) {
            case "lenovo" :computer = new LenovoComputer();break;
            case "hp":computer = new HPComputer();
        }



        return computer;
    }

}
