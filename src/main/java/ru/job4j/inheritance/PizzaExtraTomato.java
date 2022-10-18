package ru.job4j.inheritance;

public class PizzaExtraTomato extends PizzaExtraCheese {
    public String name() {
        return super.name() + " + extra tomatoes";
    }
}
