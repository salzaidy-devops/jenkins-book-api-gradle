package com.example.books.controller;

import com.example.books.model.Book;
import com.example.books.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService svc;

    public BookController(BookService svc) {
        this.svc = svc;
    }

    @GetMapping({ "", "/" })
    public List<Book> all() {
        return svc.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> one(@PathVariable Long id) {
        return svc.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping({ "", "/" })
    public ResponseEntity<Book> create(@RequestBody Book b) {
        Book saved = svc.create(b);
        return ResponseEntity.created(URI.create("/api/books/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book b) {
        return svc.update(id, b).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return svc.delete(id) ? ResponseEntity.noContent().build()
                              : ResponseEntity.notFound().build();
    }
}
