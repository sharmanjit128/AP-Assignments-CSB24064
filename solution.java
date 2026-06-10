import java.util.*;




public class LibrarySystem {
     static abstract class LibraryItem {
        protected String title;
        protected int year;


        // constructor overloading
        public LibraryItem() {
            this("Unknown", 0);
        }


        public LibraryItem(String title, int year) {
            this.title = title;
            this.year = year;
        }


        // abstract method
        public abstract void displayInfo();
    }
    static class Book extends LibraryItem {
        private String author;


        public Book(String title, int year, String author) {
            super(title, year);
            this.author = author;
        }


        @Override
        public void displayInfo() {
            System.out.println("Book:");
            System.out.println("Title: " + title);
            System.out.println("Year: " + year);
            System.out.println("Author: " + author);
        }
    }
    static class DVD extends LibraryItem {
        private int duration;
        private String genre;


        public DVD(String title, int year, int duration, String genre) {
            super(title, year);
            this.duration = duration;
            this.genre = genre;
        }


        @Override
        public void displayInfo() {
            System.out.println("DVD:");
            System.out.println("Title: " + title);
            System.out.println("Year: " + year);
            System.out.println("Duration: " + duration + " mins");
            System.out.println("Genre: " + genre);
        }
    }


    public static void main(String[] args) {


        ArrayList<LibraryItem> items = new ArrayList<>();


        items.add(new Book("Java Basics", 2020, "James Gosling"));
        items.add(new DVD("Inception", 2010, 148, "Sci-Fi"));


        // Polymorphism
        for (LibraryItem item : items) {
            System.out.println("\n--- Item Details ---");
            item.displayInfo(); // runtime polymorphism
        }
    }
}

