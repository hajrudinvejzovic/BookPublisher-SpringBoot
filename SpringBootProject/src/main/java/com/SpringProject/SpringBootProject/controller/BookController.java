package com.SpringProject.SpringBootProject.controller;

import com.SpringProject.SpringBootProject.entity.Book;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;
   //get all books GET
    @GetMapping
    public List<Book> AllBooks() {
        return this.bookRepository.findAll();
    }
    //get book by id GET
    @GetMapping("/{id}")
    public Book BookbyId(@PathVariable(value = "id") long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(()-> new ResourceNotFoundException("This book doesnt exist!" + bookId));
    }

    //create book POST
    @PostMapping
    public Book newBook(@RequestBody Book book){
        return this.bookRepository.save(book);
    }

    //update book PUT
    @PutMapping("/{id}")
    public Book book(@RequestBody Book book, @PathVariable(value = "id") long bookId){
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(()-> new ResourceNotFoundException("Book by this id is not found:" + bookId));
        existingBook.setName(existingBook.getName());
        existingBook.setGenre(existingBook.getGenre());
        return this.bookRepository.save(existingBook);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> book(@PathVariable("id") long bookId){
        Book existingbook= bookRepository.findById(bookId)
                .orElseThrow(()-> new ResourceNotFoundException("Book by this ID is not found" + bookId));
        this.bookRepository.delete(existingbook);
        return ResponseEntity.ok().build();

    }

}
