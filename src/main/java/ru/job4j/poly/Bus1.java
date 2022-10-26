package ru.job4j.poly;

public class Bus1 implements Vehicle {
    @Override
    public void move() {
        System.out.println(getClass().getSimpleName() + " дивгается по скоростным трассам");
    }
}
