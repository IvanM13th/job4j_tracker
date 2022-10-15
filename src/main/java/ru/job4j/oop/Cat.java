package ru.job4j.oop;

public class Cat {

    private String name;
    private String food;

    public void show() {
        System.out.println("Имя котика - " + this.name + ", " + " его еда - " + this.food);
    }

    public void giveNick(String nick) {
        this.name = nick;
    }

    public void eat(String meat) {
        this.food = meat;
    }

    public static void main(String[] args) {
        Cat gav = new Cat();
        gav.giveNick("gav");
        gav.eat("kotleta");
        gav.show();
    }
}
