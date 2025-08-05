package com.demo.eclipsestore;

import com.demo.eclipsestore.model.Book;
import com.demo.eclipsestore.service.LibraryService;
import com.demo.eclipsestore.service.StorageService;
import java.util.List;

public class EclipseStoreApplication {

    public static void main(final String... args) {
        final StorageService storageService = new StorageService();
        final LibraryService libraryService = new LibraryService(storageService);

        libraryService.initializeLibrary("My Personal Library");

        final Book book1 = new Book();
        book1.setTitle("Effective Java");
        book1.setAuthor("Joshua Bloch");
        book1.setYear(2018);

        final Book book2 = new Book();
        book2.setTitle("Clean Code");
        book2.setAuthor("Robert C. Martin");
        book2.setYear(2008);

        final Book book3 = new Book();
        book3.setTitle("Java Persistence with Hibernate");
        book3.setAuthor("Christian Bauer");
        book3.setYear(2015);

        libraryService.addBooks(List.of(book1, book2, book3));

        // Query examples
        System.out.println("All books: " + libraryService.getAllBooks());
        System.out.println("Books by Joshua Bloch: " + libraryService.getBooksByAuthor("Joshua Bloch"));
        System.out.println("Books published after 2010: " + libraryService.getBooksPublishedAfter(2010));
        System.out.println("Books with 'Java' in title: " + libraryService.getBooksByTitle("Java"));
        System.out.println("Total books in library: " + libraryService.getBookCount());

        storageService.shutdown();
    }
}