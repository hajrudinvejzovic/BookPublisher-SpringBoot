package com.SpringProject.SpringBootProject;
import com.SpringProject.SpringBootProject.controller.LanguagesController;
import com.SpringProject.SpringBootProject.entity.Languages;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.LanguagesRepository;
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
public class LanguagesControllerTests {
    @Mock
    LanguagesRepository languagesRepository;
    @InjectMocks
    LanguagesController languagesController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllLanguages(){
        List<Languages> languagesList = new ArrayList<>();
        languagesList.add(new Languages("Bosnian", null));
        Page<Languages> languagesPage = new PageImpl<>(languagesList);

        when(languagesRepository.findAll(any(Pageable.class))).thenReturn(languagesPage);
        Page<Languages> result = languagesController.findAllLanguages(Pageable.unpaged());
        assertEquals(languagesList.size(), result.getContent().size());
    }
    @Test
    public void testFindLanguageById(){
        Languages language = new Languages("English", null);
        when(languagesRepository.findById(anyLong())).thenReturn(Optional.of(language));

        Languages result = languagesController.findLanguageById(1L);
        assertEquals(language.getName(),result.getName());
    }
    @Test
    public void testFindLanguageByIdNotFound(){
        Languages language = new Languages("English", null);
        when(languagesRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()-> languagesController.findLanguageById(1L) );

    }
    @Test
    public void testCreateLanguage(){
        Languages language = new Languages("Spain", null);
        when(languagesRepository.save(any(Languages.class))).thenReturn(language);

        Languages result = languagesController.createLanguage(language);
        assertEquals(language.getName(),result.getName());
    }
    @Test
    public void testUpdateLanguages(){
        Languages existingLanguage =  new Languages("Spain", null);

        when(languagesRepository.findById(anyLong())).thenReturn(Optional.of(existingLanguage));
        when(languagesRepository.save(any(Languages.class))).thenReturn(existingLanguage);

        Languages updatedLanguage =  new Languages("Arabic", null);
        Languages result = languagesController.updateLanugage(updatedLanguage,1L);
        assertEquals(existingLanguage.getName(),result.getName());

    }
    @Test
    public void testUpdateLanguageNotFound(){
        when(languagesRepository.findById(anyLong())).thenReturn(Optional.empty());

        Languages updatedLanguage =  new Languages("Arabic", null);

        assertThrows(ResourceNotFoundException.class, ()-> languagesController.updateLanugage(updatedLanguage,1L));
    }
    @Test
    public void testDeleteLanguage(){
        Languages existingLanguage =  new Languages("Arabic", null);
        when(languagesRepository.findById(anyLong())).thenReturn(Optional.of(existingLanguage));
        ResponseEntity<Languages> result = languagesController.deleteLanguage(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    @Test
    public void testDeleteLanguageNotFound(){
        when(languagesRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, ()-> languagesController.deleteLanguage(1L));
    }







}
