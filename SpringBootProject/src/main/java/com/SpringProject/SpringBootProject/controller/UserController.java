package com.SpringProject.SpringBootProject.controller;

import com.SpringProject.SpringBootProject.entity.User;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    //return all users GET
    @GetMapping
    public Page<User> findAllUsers(Pageable pageable){
        return this.userRepository.findAll(pageable);
    }
    //get user by id GET
   @GetMapping("/{id}")
    public User findUserById(@PathVariable( value = "id") long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with this id: " + userId));
    }
    //create user POST
    @PostMapping
    public User createUser(@RequestBody User user){
        return this.userRepository.save(user); // save user into database

    }
    //update user -PUT
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") long userId){
        User existingUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with this id: " + userId));
        existingUser.setReport(existingUser.getReport());
        existingUser.setName(existingUser.getName());
        existingUser.setOrder(user.getOrder());
        existingUser.setSurname(existingUser.getSurname());
        existingUser.setCity(existingUser.getCity());
        existingUser.setContact(existingUser.getContact());
        existingUser.setPayment(existingUser.getPayment());
        return this.userRepository.save(existingUser);

    }
    //delete user -DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long userId){
        User existingUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with this id: " + userId));
        this.userRepository.delete(existingUser);
        return  ResponseEntity.ok().build();
    }


}
