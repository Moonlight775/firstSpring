package com.moon775.dinaop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("engine") class Engine{}     // @Conponent는 <bean id="engine" class="com.moon775.dinaop.Engine/> 와 같은 역할, ("engine")은 생략가능
@Component class SuperEngine extends Engine{}
@Component class TurboEngine extends Engine{}
@Component class Door{}
@Component
class Car{
    @Value("red") String color;
    @Value("100") int oil;
//    @Autowired Engine engine;   //  @Autowired는 byType - 타입으로 먼저 검색, 여러 개면 이름으로 검색. (타입의 첫 글자를 소문자로 한 이름으로 검색)

    //    class Engine{}에 @Component가 안 붙었다고 가정하면 위의 경우 같은 타입이 여러 개 이므로 이름으로도 찾을 수 없어서 에러 발생. (engine이라는 key가 없으므로)
    @Autowired @Qualifier("superEngine") Engine engine;    // 이럴 때 @Qualifier 에 적힌 이름(key)의 객체를 연결. @Autowired @Qualifier("superEngine")를 @Resource(name="superEngine")으로 변경 가능 (같지는 않음)
    @Autowired Door[] doors;

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

public class SpringDiTest {
    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
//        Car car = (Car) ac.getBean("car");  // by Name. 아래와 같은 문장
        Car car = ac.getBean("car", Car.class);  // by Name
//        Car car2 = ac.getBean(Car.class);  // by Type
//
//        Engine engine = (Engine) ac.getBean("engine");  // byName
////        Engine engine = (Engine) ac.getBean(Engine.class);  // byType - 같은 타입이 3개라서 에러 발생
//        Door door = (Door) ac.getBean("door");

//        car.setColor("red");
//        car.setOil(100);
//        car.setEngine(engine);
//        car.setDoors(new Door[] {ac.getBean("door", Door.class), (Door) ac.getBean("door")});

        System.out.println("car = " + car);
    }
}
