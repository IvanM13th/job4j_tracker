package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("bmchek13@gmail.com", "Marishin Ivan");
        map.put("Vasya13@gmail.com", "Vasiliev Vasiliy");
        map.put("Tanya14@gmail.com", "Ivanona Tatyana");
        for (String key : map.keySet()) {
            String value = map.get(key);
            System.out.println("key: " + key + ", " + "value: " + value);
        }
    }
}
