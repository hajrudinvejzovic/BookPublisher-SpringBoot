package com.SpringProject.SpringBootProject;
import com.SpringProject.SpringBootProject.controller.GenresController;
import com.SpringProject.SpringBootProject.entity.Genres;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.GenresRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GenresControllerTests {
    @Mock
    GenresRepository genresRepository;
    @InjectMocks
    GenresController genresController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllGenres(){
        List<Genres> genresList = new ArrayList<>();
        genresList.add(new Genres("Drama", null));
        Page<Genres> genresPage = new PageImpl<>(genresList);

        when(genresRepository.findAll(any(Pageable.class))).thenReturn(genresPage);
        Page<Genres> result = genresController.findAllGenres(Pageable.unpaged());
        assertEquals(genresList.size(), result.getContent().size());
    }
    @Test
    public void testFindGenreById(){
        Genres genres = new Genres("Thriller", null);
        when(genresRepository.findById(anyLong())).thenReturn(Optional.of(genres));

        Genres result =genresController.findGenreById(1L);
        assertEquals(genres.getName(),result.getName());

    }
    @Test
    public void testFindGenresByIdNotFound(){
        Genres genres = new Genres("Romance",null);
        when(genresRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()-> genresController.findGenreById(1L) );
    }
    @Test
    public void testCreateGenre(){
        Genres genres = new Genres("Horror", null);
        when(genresRepository.save(any(Genres.class))).thenReturn(genres);

        Genres result = genresController.createGenre(genres);
        assertEquals(genres.getName(),result.getName());



    }
   @Test
    public void testUpdateGenres(){
       Genres existingGenre =  new Genres("Horror", null);

       when(genresRepository.findById(anyLong())).thenReturn(Optional.of(existingGenre));
       when(genresRepository.save(any(Genres.class))).thenReturn(existingGenre);

       Genres updatedGenre = new Genres("Comedy", null);
       Genres result = genresController.updateGenres(updatedGenre,1L);
       assertEquals(existingGenre.getName(),result.getName());
   }
   @Test
   public void testDeleteGenres(){
       Genres existingGenre =  new Genres("Horror", null);
       when(genresRepository.findById(anyLong())).thenReturn(Optional.of(existingGenre));
       ResponseEntity<Genres> result = genresController.deleteGenre(1L);
       assertEquals(HttpStatus.OK, result.getStatusCode());
   }
    @Test
    public void testDeleteGenreNotFound(){
        when(genresRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, ()-> genresController.deleteGenre(1L));
    }


}
