package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> findName = name -> name.getName().equals(key);
        Predicate<Person> findSurname = surname -> surname.getSurname().equals(key);
        Predicate<Person> findPhone = phone -> phone.getPhone().equals(key);
        Predicate<Person> findAddress = address -> address.getAddress().equals(key);
        Predicate<Person> combine = findName.or(findSurname).or(findPhone).or(findAddress);
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
