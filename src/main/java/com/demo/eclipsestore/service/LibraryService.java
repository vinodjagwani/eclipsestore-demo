package com.demo.eclipsestore.service;

import com.demo.eclipsestore.model.Book;
import com.demo.eclipsestore.model.Library;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LibraryService {

    StorageService storageService;

    public void initializeLibrary(final String name) {
        final Library library = storageService.getRoot();
        library.setName(name);
        storageService.saveLibrary(library);
    }

    public void addBook(Book book) {
        final Library library = storageService.getRoot();
        library.setBooks(List.of(book));
        storageService.saveBooks();
    }

    public void addBooks(final List<Book> books) {
        final Library library = storageService.getRoot();
        library.setBooks(books);
        storageService.saveBooks();
    }

    public List<Book> getAllBooks() {
        return storageService.getRoot().getBooks();
    }

    public List<Book> getBooksByAuthor(final String author) {
        return storageService.getRoot().getBooks().stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .toList();
    }

    public List<Book> getBooksPublishedAfter(final int year) {
        return storageService.getRoot().getBooks().stream()
                .filter(book -> book.getYear() > year)
                .toList();
    }

    public List<Book> getBooksByTitle(final String title) {
        return storageService.getRoot().getBooks().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }

    public long getBookCount() {
        return storageService.getRoot().getBooks().size();
    }
}
