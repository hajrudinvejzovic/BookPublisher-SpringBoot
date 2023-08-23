package com.SpringProject.SpringBootProject;
import com.SpringProject.SpringBootProject.controller.AuthorsController;
import com.SpringProject.SpringBootProject.entity.Authors;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.AuthorsRepository;
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
public class AuthorsControllerTests {

	@Mock
	private AuthorsRepository authorsRepository;

	@InjectMocks
	private AuthorsController authorsController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@Order(1)
	public void testFindAllAuthors() {
		List<Authors> authorsList = new ArrayList<>();
		authorsList.add(new Authors("John", "Doe", "01.01.1980", null, null));
		Page<Authors> authorsPage = new PageImpl<>(authorsList);

		when(authorsRepository.findAll(any(Pageable.class))).thenReturn(authorsPage);

		Page<Authors> result = authorsController.findAllAuthors(Pageable.unpaged());

		assertEquals(authorsList.size(), result.getContent().size());
	}

	@Test
	@Order(2)
	public void testFindAuthorById() {
		Authors author = new Authors("John", "Doe", "01.01.1980", null, null);
		when(authorsRepository.findById(anyLong())).thenReturn(Optional.of(author));

		Authors result = authorsController.findAuthorById(1L);

		assertEquals(author.getName(), result.getName());
		assertEquals(author.getSurname(), result.getSurname());
        assertEquals(author.getBirth(),result.getBirth());
	}

	@Test
	@Order(3)
	public void testFindAuthorByIdNotFound() {
		when(authorsRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> authorsController.findAuthorById(1L));
	}

	@Test
	@Order(4)
	public void testCreateAuthor() {
		Authors author = new Authors("John", "Doe", "01.01.1980", null, null);
		when(authorsRepository.save(any(Authors.class))).thenReturn(author);

		Authors result = authorsController.createAuthor(author);

		assertEquals("John", result.getName());
		assertEquals("Doe", result.getSurname());
	}

	@Test
	@Order(5)
	public void testUpdateAuthor() {
		Authors existingAuthor = new Authors("John", "Doe", "01.01.1980", null, null);
		when(authorsRepository.findById(anyLong())).thenReturn(Optional.of(existingAuthor));
		when(authorsRepository.save(any(Authors.class))).thenReturn(existingAuthor);

		Authors updatedAuthor = new Authors("Jane", "Smith", "02.02.1990", null, null);
		Authors result = authorsController.updateAuthor(updatedAuthor, 1L);

		assertEquals("Jane", result.getName());
		assertEquals("Smith", result.getSurname());
		assertEquals("02.02.1990", result.getBirth());
	}

	@Test
	@Order(6)
	public void testUpdateAuthorNotFound() {
		when(authorsRepository.findById(anyLong())).thenReturn(Optional.empty());

		Authors updatedAuthor = new Authors("Jane", "Smith", "02.02.1990", null, null);

		assertThrows(ResourceNotFoundException.class, () -> authorsController.updateAuthor(updatedAuthor, 1L));
	}

	@Test
	@Order(7)
	public void testDeleteAuthor() {
		Authors existingAuthor = new Authors("John", "Doe", "01.01.1980", null, null);
		when(authorsRepository.findById(anyLong())).thenReturn(Optional.of(existingAuthor));

		ResponseEntity<Authors> result = authorsController.deleteAuthor(1L);

		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	@Order(8)
	public void testDeleteAuthorNotFound() {
		when(authorsRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> authorsController.deleteAuthor(1L));

	}
}
