package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> findName = name -> key.equals(name.getName());
        Predicate<Person> findSurname = surname -> key.equals(surname.getSurname());
        Predicate<Person> findPhone = phone -> key.equals(phone.getPhone());
        Predicate<Person> findAddress = address -> key.equals(address.getAddress());
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
