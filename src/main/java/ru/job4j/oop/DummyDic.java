package ru.job4j.oop;

public class DummyDic {
    public String engToRus(String eng) {
        String line = "Неизвестное слово. " + eng;
        return line;
    }

    public static void main(String[] args) {
        DummyDic word = new DummyDic();
        String say = word.engToRus("ukulele");
        System.out.println(say);
    }
}
