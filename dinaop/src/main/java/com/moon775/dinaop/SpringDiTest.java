package com.moon775.dinaop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

class Car{
    String color;
    int oil;
    Engine engine;
    Door[] doors;

    public Car() {} // 기본 생성자를 잊지 않고 만들어줘야 좋다.

    public Car(String color, int oil, Engine engine, Door[] doors) {
        this.color = color;
        this.oil = oil;
        this.engine = engine;
        this.doors = doors;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOil(int oil) {
        this.oil = oil;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setDoors(Door[] doors) {
        this.doors = doors;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", oil=" + oil +
                ", engine=" + engine +
                ", doors=" + Arrays.toString(doors) +
                '}';
    }
}
class Engine{}
class Door{}

public class SpringDiTest {
    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
//        Car car = (Car) ac.getBean("car");  // by Name. 아래와 같은 문장
        Car car = ac.getBean("car", Car.class);  // by Name
        Car car2 = ac.getBean(Car.class);  // by Type

        Engine engine = (Engine) ac.getBean("engine");
        Door door = (Door) ac.getBean("door");

        System.out.println("car = " + car);
    }
}
