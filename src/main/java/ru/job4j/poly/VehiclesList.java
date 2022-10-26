package ru.job4j.poly;

public class VehiclesList {
    public static void main(String[] args) {
        Vehicle smallAirplane = new Airplane();
        Vehicle hugeAirplane = new Airplane();
        Vehicle electricTrain = new Train();
        Vehicle coalTrain = new Train();
        Vehicle oneLevelBus = new Bus1();
        Vehicle multiLevelBus = new Bus1();

        Vehicle[] vehicles = new Vehicle[]{smallAirplane, hugeAirplane, electricTrain, coalTrain, oneLevelBus, multiLevelBus};
        for (Vehicle v : vehicles) {
            v.move();
        }
    }
}
