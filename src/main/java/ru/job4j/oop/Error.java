package ru.job4j.oop;

public class Error {
    boolean active;
    int status;
    String message;

    public Error() {

    }

    public Error(boolean active, int status, String message)  {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println("Активность: " + active);
        System.out.println("Статус: " + status);
        System.out.println("Сообщение: " + message);
    }

    public static void main(String[] args) {
        Error error = new Error();
        error.printInfo();
        System.out.println();

        Error par1 = new Error(true, 2, "Констуркутор с параметрами 1");
        par1.printInfo();
        System.out.println();

        Error par2 = new Error(false, 7, "Второй набор параметро");
        par2.printInfo();
    }

}
