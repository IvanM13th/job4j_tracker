package ru.job4j.poly;

public class PolyUsage {
    public static void main(String[] args) {
        Animal cow = new Cow();
        Animal dog = new Dog();
        Animal goose = new Goose();
        Animal guineaPig = new GuineaPig();

        Animal[] animals = new Animal[] {cow, dog, goose, guineaPig};
        for (Animal animal : animals) {
            animal.sound();
        }
    }
}
