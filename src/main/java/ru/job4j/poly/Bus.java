package ru.job4j.poly;

public class Bus implements Transport{
    @Override
    public void drive() {
    }

    @Override
    public void passengers(int num) {
    }

    @Override
    public double fuel(double litres) {
        double litreCost = 5;
        return litres * litreCost;
    }
}
