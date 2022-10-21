package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book bloodAndSteel = new Book("Blood and Steel", 469);
        Book harrypotter = new Book("Harry Potter and the Philosopher's Stone", 326);
        Book redpill = new Book("Red Pill", 437);
        Book cleanCode = new Book("Clean Code", 618);

        Book[] books = new Book[4];
        books[0] = bloodAndSteel;
        books[1] = harrypotter;
        books[2] = redpill;
        books[3] = cleanCode;
        for (Book bk : books) {
            System.out.println("Book: " + bk.getName() + " has " + bk.getPages() + " pages");
        }
        System.out.println();

        System.out.println("Swap 0 and 3");
        Book temp = books[0];
        books[0] = books[3];
        books[3] = temp;
        for (Book bk : books) {
            System.out.println("Book: " + bk.getName() + " has " + bk.getPages() + " pages");
        }
        System.out.println();

        System.out.println("Print only \"Clean Code\"");
        for (Book book : books) {
            if ("Clean Code".equals(book.getName())) {
                System.out.println("Book: " + book.getName() + " has " + book.getPages() + " pages");
            }
        }
    }
}
