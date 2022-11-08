package ru.job4j.ex;

public class FindEl {
    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int rsl = -1;
        for (int i = 0; i < value.length; i++) {
            if (value[i].equals(key)) {
                return i;
            }
        }
        throw new ElementNotFoundException("Element not found");
    }

    public static void main(String[] args) {
        String[] value = {"a", "b", "C", "d", "e"};
        try {
            indexOf(value, "f");
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}