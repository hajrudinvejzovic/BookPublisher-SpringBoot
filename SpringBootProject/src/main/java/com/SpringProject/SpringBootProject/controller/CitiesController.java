package com.SpringProject.SpringBootProject.controller;

import com.SpringProject.SpringBootProject.entity.Cities;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
    @Autowired
    private CitiesRepository citiesRepository;
    @GetMapping
    public Page<Cities> findAllCities(Pageable pageable){
        return this.citiesRepository.findAll(pageable);
    }
    @GetMapping("/{id}")
    public Cities findCityById(@PathVariable(value = "id") long cityId){
        return this.citiesRepository.findById(cityId)
                .orElseThrow(()-> new ResourceNotFoundException("City with this Id NOT FOUND!" + cityId));
    }
    @PostMapping
    public Cities createCity(@RequestBody Cities city){
        return this.citiesRepository.save(city);
    }

    @PutMapping("/{id}")
    public Cities updateCity(@RequestBody Cities city, @PathVariable(value = "id") long cityId){
        Cities existingCity = this.citiesRepository.findById(cityId)
                .orElseThrow(()-> new ResourceNotFoundException("City with this Id NOT FOUND!" + cityId));
        existingCity.setName(existingCity.getName());
        existingCity.setAuthor(existingCity.getAuthor());
        existingCity.setEmployee(existingCity.getEmployee());
        existingCity.setUser(existingCity.getUser());
       return this.citiesRepository.save(existingCity);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Cities> deleteCity(@PathVariable(value = "id") long cityId){
       Cities existingCity = this.citiesRepository.findById(cityId)
               .orElseThrow(() -> new ResourceNotFoundException("City with this id NOT FOUND: " + cityId));
       this.citiesRepository.delete(existingCity);
       return ResponseEntity.ok().build();
    }
}
