package com.moon775.dinaop.diCopy1;

import java.io.FileReader;
import java.util.Properties;

class Car {}
class SportsCar extends Car{}
class Truck extends Car{}

public class Main1 {
    public static void main(String[] args) throws Exception{
        Car car = getCar();
        System.out.println("car = " + car);
    }

    static Car getCar() throws Exception{
        Properties p = new Properties();
        p.load(new FileReader("config.txt"));

        Class clazz = Class.forName(p.getProperty("car"));

        return (Car) clazz.newInstance();
    }
}
