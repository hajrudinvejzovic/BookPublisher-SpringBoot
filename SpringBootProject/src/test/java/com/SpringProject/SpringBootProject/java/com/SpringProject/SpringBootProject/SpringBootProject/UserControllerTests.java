package com.SpringProject.SpringBootProject;
import com.SpringProject.SpringBootProject.controller.UserController;
import com.SpringProject.SpringBootProject.entity.User;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
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

import java.time.LocalDate;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserControllerTests {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testFindAllUsers(){
        List<User> userList = new ArrayList<>();
        userList.add(new User(null, null, "Hajrudin", "Vejzović", null, null, null));

        Page<User> userPage = new PageImpl<>(userList);

        when(userRepository.findAll(any(Pageable.class))).thenReturn(userPage);
        Page<User> result = userController.findAllUsers(Pageable.unpaged());
        assertEquals(userList.size(),result.getContent().size());

    }
    @Test
    public void testFindUserById(){
       User user = new User(null, null, "Ime", "Prezime", null, null, null);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        User result = userController.findUserById(1L);
        assertEquals(user.getName(),result.getName());
        assertEquals(user.getSurname(),result.getSurname());
    }
    @Test
    public void testFindUserByIdNotFound(){
        User user  = new User(null, null, "Ime", "Prezime", null, null, null);
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, ()-> userController.findUserById(1L));
    }
    @Test
    public void testCreateUser(){
        User user  = new User(null, null, "Ime", "Prezime", null, null, null);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userController.createUser(user);
        assertEquals(result.getName(),user.getName());
        assertEquals(result.getSurname(),user.getSurname());

    }
    @Test
    public void testUpdateUser(){
        User existingUser  = new User(null, null, "Ime", "Prezime", null, null, null);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        User updatedUser  = new User(null, null, "Dante", "Penumbra", null, null, null);
        User result = userController.findUserById(2L);
        assertEquals(updatedUser.getSurname(),result.getSurname());
        assertEquals(updatedUser.getName(),result.getName());

    }
    @Test
    public void testUpdateUserNotFound(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        User updatedUser  = new User(null, null, "Dante", "Penumbra", null, null, null);

        assertThrows(ResourceNotFoundException.class,() -> userController.updateUser(updatedUser,1L));
    }
    @Test
    public void testDeleteUser(){
        User existingUser  = new User(null, null, "Dante", "Penumbra", null, null, null);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(existingUser));

        ResponseEntity<User> result = userController.deleteUser(2L);
        assertEquals(HttpStatus.OK,result.getStatusCode());
    }
    @Test
    public void testDeleteUserNotFound(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()-> userController.deleteUser(1L));

    }



}
