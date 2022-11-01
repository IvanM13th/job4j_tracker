package ru.job4j.oop;

public class HierarchyUsage {
    public static void main(String[] args) {
        Car car = new Car(); // создали объект типа Car
        Transport tp = car; // записали этот объект в переменную типа Transport
        Object obj = car; // записали этот объект в переменную типа Object
        Object ocar = new Car(); // создали объект типа Object
        Car carFromObject = (Car) ocar; // понижающее приведение типаов, спускаемся вниз по иерархии

        Object bicycle = new Bicycle();
        Car cb = (Car) bicycle;
    }
}
