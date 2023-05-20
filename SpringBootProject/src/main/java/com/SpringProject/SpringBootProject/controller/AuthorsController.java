package com.SpringProject.SpringBootProject.controller;

import com.SpringProject.SpringBootProject.entity.Authors;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorsController {
    @Autowired
    private AuthorsRepository authorsRepository;

    //GET ALL
    @GetMapping
    public Page<Authors> findAllAuthors(Pageable pageable){
        return this.authorsRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Authors findAuthorById(@PathVariable (value = "id") long author_id){
    return this.authorsRepository.findById(author_id)
            .orElseThrow(()-> new ResourceNotFoundException("Author with this Id is NOT FOUND!" + author_id));
    }
    @PostMapping
    public Authors createAuthor(@RequestBody Authors author){
        return this.authorsRepository.save(author);
    }

    @PutMapping("/{id}")
    public Authors updateAuthor(@RequestBody Authors author, @PathVariable ( value = "id") long authorId){
        Authors existingAuthor = this.authorsRepository.findById(authorId)
                .orElseThrow(()-> new ResourceNotFoundException("Author with this Id NOT FOUND!" + authorId));
        existingAuthor.setName(existingAuthor.getName());
        existingAuthor.setSurname(existingAuthor.getSurname());
        existingAuthor.setBook_Authors(existingAuthor.getBook_Authors());
        existingAuthor.setBirth(existingAuthor.getBirth());
        existingAuthor.setCity(existingAuthor.getCity());
        return this.authorsRepository.save(existingAuthor);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Authors> deleteAuthor(@PathVariable (value = "id") long authorId){
        Authors existingAuthor = this.authorsRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with this id: " + authorId));
        this.authorsRepository.delete(existingAuthor);
        return ResponseEntity.ok().build();
    }
}
