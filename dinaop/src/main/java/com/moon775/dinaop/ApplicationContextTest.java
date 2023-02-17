package com.moon775.dinaop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Scope("prototype")
class Door {}
@Component class Engine {}
@Component class TurboEngine extends Engine {}
@Component class SuperEngine extends Engine {}

@Component
class Car {
    @Value("red") String color;
    @Value("100") int oil;
    @Autowired
    Engine engine;
    @Autowired    Door[] doors;

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

public class ApplicationContextTest {
    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
//      Car car = ac.getBean("car", Car.class); // 타입을 지정하면 형변환 안해도됨. 아래의 문장과 동일
        Car car  = (Car) ac.getBean("car"); // 이름으로 빈 검색
        Car car2 = ac.getBean(Car.class);   // 타입으로 빈 검색
        System.out.println("car = " + car);
        System.out.println("car2 = " + car2);

        System.out.println("ac.getBeanDefinitionNames() = " + Arrays.toString(ac.getBeanDefinitionNames())); // 정의된 빈의 이름을 배열로 반환
        System.out.println("ac.getBeanDefinitionCount() = " + ac.getBeanDefinitionCount()); // 정의된 빈의 개수를 반환

        System.out.println("ac.containsBeanDefinition(\"car\") = " + ac.containsBeanDefinition("car"));  // true 빈의 정의가 포함되어 있는지 확인
        System.out.println("ac.containsBean(\"car\") = " + ac.containsBean("car")); // true 빈이 포함되어 있는지 확인

        System.out.println("ac.getType(\"car\") = " + ac.getType("car")); // 빈의 이름으로 타입을 알아낼 수 있음.
        System.out.println("ac.isSingleton(\"car\") = " + ac.isSingleton("car")); // true 빈이 싱글톤인지 확인
        System.out.println("ac.isPrototype(\"car\") = " + ac.isPrototype("car")); // false 빈이 프로토타입인지 확인
        System.out.println("ac.isPrototype(\"door\") = " + ac.isPrototype("door")); // true
        System.out.println("ac.isTypeMatch(\"car\", Car.class) = " + ac.isTypeMatch("car", Car.class)); // "car"라는 이름의 빈의 타입이 Car인지 확인
        System.out.println("ac.findAnnotationOnBean(\"car\", Component.class) = " + ac.findAnnotationOnBean("car", Component.class)); // 빈 car에 @Component가 붙어있으면 반환
//        System.out.println("ac.getBeanNamesForAnnotation(Component.class) = " + Arrays.toString(ac.getBeanNamesForAnnotation(Component.class))); // @Component가 붙은 빈의 이름을 배열로 반환
        System.out.println("ac.getBeanNamesForType(Engine.class) = " + Arrays.toString(ac.getBeanNamesForType(Engine.class))); // Engine 또는 그 자손 타입인 빈의 이름을 배열로 반환
    }
}
