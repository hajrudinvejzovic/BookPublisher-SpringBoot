package com.SpringProject.SpringBootProject;
import com.SpringProject.SpringBootProject.controller.CitiesController;
import com.SpringProject.SpringBootProject.entity.Cities;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.CitiesRepository;
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
@SpringBootTest
public class CitiesControllerTests {
    @Mock
    CitiesRepository citiesRepository;
    @InjectMocks
    CitiesController citiesController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllCities(){
        List<Cities> citiesList = new ArrayList<>();
        citiesList.add(new Cities("London", null, null, null));
        Page<Cities> citiesPage = new PageImpl<>(citiesList);

        when(citiesRepository.findAll(any(Pageable.class))).thenReturn(citiesPage);

        Page<Cities> result = citiesController.findAllCities(Pageable.unpaged());
        assertEquals(citiesList.size(),result.getContent().size());

    }
    @Test
    public void testCityById(){
        Cities city = new Cities("London", null, null, null);
        when(citiesRepository.findById(anyLong())).thenReturn(Optional.of(city));

        Cities result = citiesController.findCityById(1L);

        assertEquals(city.getName(),result.getName());

    }
    @Test
    public void testCityByIdNotFound(){
        Cities city = new Cities("London", null, null, null);

        when(citiesRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()-> citiesController.findCityById(1L));

    }
    @Test
    public void testCreateCity(){
        Cities city = new Cities("London", null, null, null);
        when(citiesRepository.save(any(Cities.class))).thenReturn(city);

        Cities result = citiesController.createCity(city);

        assertEquals(city.getName(),result.getName());

    }
    @Test
    public void testUpdateCity(){
        Cities existingcity = new Cities("London", null, null, null);
        when(citiesRepository.findById(anyLong())).thenReturn(Optional.of(existingcity));
        when(citiesRepository.save(any(Cities.class))).thenReturn(existingcity);

        Cities updatedCity = new Cities("Palermo", null, null, null);
        Cities result = citiesController.findCityById(1L);

        assertEquals(existingcity.getName(),result.getName());


    }
    @Test
    public void testUpdateCityNotFound(){
        when(citiesRepository.findById(anyLong())).thenReturn(Optional.empty());

        Cities updatedCity = new Cities("Palermo", null, null, null);

        assertThrows( ResourceNotFoundException.class, ()->  citiesController.updateCity(updatedCity,1L));

    }
    @Test
    public void testDeleteCity(){
        Cities existingcity = new Cities("Palermo", null, null, null);
        when(citiesRepository.findById(anyLong())).thenReturn(Optional.of(existingcity));

        ResponseEntity<Cities> result = citiesController.deleteCity(2L);
        assertEquals(HttpStatus.OK,result.getStatusCode());

    }



    @Test
    public void testDeleteCityNotFound(){
        when(citiesRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()-> citiesController.deleteCity(1L));

    }


}
