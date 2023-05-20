package com.SpringProject.SpringBootProject.controller;

import com.SpringProject.SpringBootProject.entity.Genres;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.GenresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenresController {
    @Autowired
    private GenresRepository genresRepository;
    @GetMapping
    public Page<Genres> findAllGenres(Pageable pageable){
        return this.genresRepository.findAll(pageable);
    }
    @GetMapping("/{id}")
    public Genres findGenreById(@PathVariable(value = "id") long genreId){
        return this.genresRepository.findById(genreId)
                .orElseThrow(()-> new ResourceNotFoundException("Genre with this Id NOT FOUND!" + genreId));
    }
    @PostMapping
    public Genres createGenre(@RequestBody Genres genres){
        return this.genresRepository.save(genres);
    }
    @PutMapping("/{id}")
    public Genres updateGenres(@RequestBody Genres genres, @PathVariable (value = "id") long genresId){
        Genres existingGenres = this.genresRepository.findById(genresId)
                .orElseThrow(()-> new ResourceNotFoundException("Genre with this Id NOT FOUND!" + genresId));
        existingGenres.setBookGenres(existingGenres.getBookGenres());
        existingGenres.setName(existingGenres.getName());
        return this.genresRepository.save(existingGenres);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Genres> deleteGenre(@PathVariable (value = "id") long genreId){
        Genres existingGenre = this.genresRepository.findById(genreId)
                .orElseThrow(() -> new ResourceNotFoundException("Genre with this id NOT FOUND: " + genreId));
        this.genresRepository.delete(existingGenre);
        return ResponseEntity.ok().build();
    }

}
