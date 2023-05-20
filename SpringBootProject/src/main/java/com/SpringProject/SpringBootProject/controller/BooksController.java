package com.SpringProject.SpringBootProject.controller;

import com.SpringProject.SpringBootProject.entity.Books;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {
    @Autowired
    private BooksRepository booksRepository;

    @GetMapping
    public Page<Books> findAllBooks(Pageable pageable){
        return this.booksRepository.findAll(pageable);
    }
    @GetMapping("/{id}")
    public Books findBookById(@PathVariable(value = "id") long bookId){
        return this.booksRepository.findById(bookId)
                .orElseThrow(()-> new ResourceNotFoundException("Book with this Id NOT FOUND!" + bookId));
    }
    @PostMapping
    public Books createBook(@RequestBody Books book){
        return this.booksRepository.save(book);
    }
    @PutMapping("/{id}")
    public Books updateBook(@RequestBody Books book, @PathVariable(value = "id") long bookId){
        Books existingBook = this.booksRepository.findById(bookId)
                .orElseThrow(()-> new ResourceNotFoundException("Book with this Id NOT FOUND!" + bookId));
        existingBook.setName(existingBook.getName());
        existingBook.setBook_Authors(existingBook.getBook_Authors());
        existingBook.setBook_Genres(existingBook.getBook_Genres());
        existingBook.setBook_Languages(existingBook.getBook_Languages());
        existingBook.setAvailability(existingBook.isAvailability());
        existingBook.setIsbn(existingBook.getIsbn());
        existingBook.setItems_Per_Orders(existingBook.getItems_Per_Orders());
        existingBook.setReports(existingBook.getReports());
        existingBook.setPublishing_year(existingBook.getPublishing_year());
        existingBook.setPrice(existingBook.getPrice());
        existingBook.setBestseller(existingBook.isBestseller());
        existingBook.setTotal_pages(existingBook.getTotal_pages());
        existingBook.setIn_stock(existingBook.isIn_stock());
        return this.booksRepository.save(existingBook);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Books> deleteBook(@PathVariable(value = "id") long booksId){
        Books existingBook = this.booksRepository.findById(booksId)
                .orElseThrow(() -> new ResourceNotFoundException("Books with this id NOT FOUND: " + booksId));
        this.booksRepository.delete(existingBook);
        return ResponseEntity.ok().build();
    }

}
