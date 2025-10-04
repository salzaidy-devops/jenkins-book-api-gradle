package com.example.books.service;

import com.example.books.model.Book;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService {
    private final Map<Long, Book> store = new LinkedHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    public BookService() {
        create(new Book(null, "Clean Code", "Robert C. Martin"));
        create(new Book(null, "Effective Java", "Joshua Bloch"));
    }

    public List<Book> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Book create(Book b) {
        long id = seq.incrementAndGet();
        b.setId(id);
        store.put(id, b);
        return b;
    }

    public Optional<Book> update(Long id, Book b) {
        if (!store.containsKey(id)) return Optional.empty();
        b.setId(id);
        store.put(id, b);
        return Optional.of(b);
    }

    public boolean delete(Long id) {
        return store.remove(id) != null;
    }
}
