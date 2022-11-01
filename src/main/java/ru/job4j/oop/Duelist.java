package ru.job4j.oop;

public class Duelist {
    private int health;
    private int damage;
    private int armor;

    public Duelist(int health, int damage, int armor) {
        this.health = health;
        this.damage = damage;
        this.armor = armor;
    }

    public void fight(Duelist another) {
        another.health -= this.damage;
    }

    public boolean lifeCheck(int health) {
        boolean dead = false;
        if (this.health <= 0) {
            dead = true;
        }
        return dead;
    }

    public static void main(String[] args) {
        Duelist kroi = new Duelist(50, 5, 10);
        Duelist poulder = new Duelist(60, 4, 13);

        int round = 1;
        while (kroi.health > 0 && poulder.health > 0) {
            System.out.println("Round " + round);
            kroi.fight(poulder);
            System.out.println("kroi deals " + kroi.damage + " and poulder has " + poulder.health + " health left");
            if (poulder.lifeCheck(poulder.health)) {
                System.out.println("dead");
                break;
            }
            poulder.fight(kroi);
            System.out.println("poulder deals " + poulder.damage + " and kroi has " + kroi.health + " health left");
            if (kroi.lifeCheck(kroi.health)) {
                System.out.println("dead");
                break;
            }
            round++;
        }
        System.out.println("Duel lasted " + round + " rounds");
    }
}
