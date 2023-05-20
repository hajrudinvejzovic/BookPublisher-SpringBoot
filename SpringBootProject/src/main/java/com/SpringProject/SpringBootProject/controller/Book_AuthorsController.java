package com.SpringProject.SpringBootProject.controller;

import com.SpringProject.SpringBootProject.entity.Book_Authors;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.Book_AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/book_authors")
public class Book_AuthorsController {
    @Autowired
    private Book_AuthorsRepository book_authorsRepository;

    //Get all
    @GetMapping
    public Page<Book_Authors> findAllBookAuthors(Pageable pageable){
        return this.book_authorsRepository.findAll(pageable);

    }
    @GetMapping("/{id}")
    public Book_Authors findBookAuthorsById(@PathVariable(value = "id") long bookAuthorId){
        return this.book_authorsRepository.findById(bookAuthorId)
                .orElseThrow(()-> new ResourceNotFoundException("Book_Author with this Id NOT FOUND!" + bookAuthorId));
    }
    @PostMapping
    public Book_Authors createBookAuthors(@RequestBody Book_Authors bookAuthors){
        return this.book_authorsRepository.save(bookAuthors);
    }
    @PutMapping("/{id}")
    public Book_Authors updateBookAuthors(@RequestBody Book_Authors bookAuthors, @PathVariable(value ="id") long bookAuthorsId){
        Book_Authors existingBookAuthors = this.book_authorsRepository.findById(bookAuthorsId)
                .orElseThrow(()-> new ResourceNotFoundException("BookAuthors with this Id NOT FOUND!"));
        existingBookAuthors.setAuthor(existingBookAuthors.getAuthor());
        existingBookAuthors.setBook(existingBookAuthors.getBook());
        return this.book_authorsRepository.save(existingBookAuthors);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Book_Authors> deleteBookAuthors(@PathVariable (value = "id") long bookAuthorId){
        Book_Authors existingBookAuthor = this.book_authorsRepository.findById(bookAuthorId)
                .orElseThrow(() -> new ResourceNotFoundException("Book_Author not found with this id: " + bookAuthorId));
        this.book_authorsRepository.delete(existingBookAuthor);
        return ResponseEntity.ok().build();
    }

}
