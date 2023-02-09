package com.moon775.dinaop.diCopy2;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class Car {}
class SportsCar extends Car{}
class Truck extends Car{}
class Engine {}

class AppContext {
    Map map;    // 객체 저장소

    AppContext() {
        map = new HashMap();
        map.put("car", new SportsCar());
        map.put("engine", new Engine());
    }

    Object getBean(String key) {
        return map.get(key);
    }
}

public class Main2 {
    public static void main(String[] args) throws Exception{
        AppContext ac = new AppContext();
        Car car = (Car) ac.getBean("car");
        Engine engine = (Engine) ac.getBean("engine");
        System.out.println("car = " + car);
        System.out.println("engine = " + engine);
    }

}
