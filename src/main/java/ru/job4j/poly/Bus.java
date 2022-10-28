package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void drive() {
        System.out.println("Пункт назначения автобуса - г. Москва");
    }

    @Override
    public void passengers(int num) {
        System.out.println("Количество пассажиров в автобусе: " + num);
    }

    @Override
    public double fuel(double litres) {
        double litreCost = 5;
        return litres * litreCost;
    }
}

