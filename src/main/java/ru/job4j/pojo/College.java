package ru.job4j.pojo;

import ru.job4j.encapsulation.Student;
import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFullName("Marishin Ivan Gennadievich");
        student.setGroup("NR17");
        student.setJoinDate(new Date());

        System.out.println("Student " + student.getFullName() + " joined group " + student.getGroup() + " on " + student.getJoinDate());
    }
}
