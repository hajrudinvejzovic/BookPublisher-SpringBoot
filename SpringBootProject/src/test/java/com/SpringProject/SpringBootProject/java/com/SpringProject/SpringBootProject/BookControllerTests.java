package com.SpringProject.SpringBootProject;
import com.SpringProject.SpringBootProject.controller.BooksController;
import com.SpringProject.SpringBootProject.entity.Books;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.BooksRepository;
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
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
@SpringBootTest
public class BookControllerTests {
    @Mock
    private BooksRepository booksRepository;
    @InjectMocks
    private BooksController booksController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testFindAllBooks(){
        List<Books> booksList = new ArrayList<>();
        booksList.add(new Books("Penumbra", null, null, null, null, null, 2023, 15, true, 13, 300, true, true ));
        Page<Books> booksPage = new PageImpl<>(booksList);

        when(booksRepository.findAll(any(Pageable.class))).thenReturn(booksPage);

        Page<Books> result = booksController.findAllBooks(Pageable.unpaged());
        assertEquals(booksList.size(), result.getContent().size());
    }

    @Test
    public void testFindBooksById(){
        Books book = new Books("Penumbra", null, null, null, null, null, 2023, 15, true, 13, 300, true, true );
        when(booksRepository.findById(anyLong())).thenReturn(Optional.of(book));

        Books result = booksController.findBookById(1L);

        assertEquals(book.getName(),result.getName());
        assertEquals(book.getPublishing_year(),result.getPublishing_year());
        assertEquals(book.getPrice(),result.getPrice());
        assertEquals(book.isBestseller(),result.isBestseller());
        assertEquals(book.getIsbn(),result.getIsbn());
        assertEquals(book.getTotal_pages(),result.getTotal_pages());
        assertEquals(book.isIn_stock(),result.isIn_stock());
        assertEquals(book.isAvailability(),result.isAvailability());

    }
    @Test
    public void testFindBookByIdNotFound(){
        when(booksRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,()-> booksController.findBookById(1L));

    }
    @Test
    public void testCreateBook(){
        Books book = new Books("Alija Đerzelez", null, null, null, null, null, 2000, 35, true, 14, 350, true, true);
        when(booksRepository.save(any(Books.class))).thenReturn(book);

        Books result = booksController.createBook(book);

        assertEquals(book.getName(),result.getName());
        assertEquals(book.getPublishing_year(),result.getPublishing_year());
        assertEquals(book.getPrice(),result.getPrice());
        assertEquals(book.isBestseller(),result.isBestseller());
        assertEquals(book.getIsbn(),result.getIsbn());
        assertEquals(book.getTotal_pages(),result.getTotal_pages());
        assertEquals(book.isIn_stock(),result.isIn_stock());
        assertEquals(book.isAvailability(),result.isAvailability());

    }
    @Test
    public void testUpdateBook(){
        Books existingBook = new Books("Alija Đerzelez", null, null, null, null, null, 2000, 35, true, 14, 350, true, true);
        when(booksRepository.findById(anyLong())).thenReturn(Optional.of(existingBook));
        when(booksRepository.save(any(Books.class))).thenReturn(existingBook);

        Books updatedBook = new Books("Hasanaginica", null, null, null, null, null, 2008, 10, false, 15, 50, true, true);
        Books result = booksController.updateBook(updatedBook, 1L);

        assertEquals(existingBook.getName(),result.getName());
        assertEquals(existingBook.getPublishing_year(),result.getPublishing_year());
        assertEquals(existingBook.getPrice(),result.getPrice());
        assertEquals(existingBook.isBestseller(),result.isBestseller());
        assertEquals(existingBook.getIsbn(),result.getIsbn());
        assertEquals(existingBook.getTotal_pages(),result.getTotal_pages());
        assertEquals(existingBook.isIn_stock(),result.isIn_stock());
        assertEquals(existingBook.isAvailability(),result.isAvailability());

    }
    @Test
    public void testUpdateBookNotFound(){
        when(booksRepository.findById(anyLong())).thenReturn(Optional.empty());

        Books updatedBook = new Books("Hasanaginica", null, null, null, null, null, 2008, 10, false, 15, 50, true, true);

        assertThrows( ResourceNotFoundException.class, () -> booksController.updateBook(updatedBook,1L));

    }
    @Test
    public void testDeleteBook(){
        Books existingBook = new Books("Alija Đerzelez", null, null, null, null, null, 2000, 35, true, 14, 350, true, true);
        when(booksRepository.findById(anyLong())).thenReturn(Optional.of(existingBook));

        ResponseEntity<Books> result = booksController.deleteBook(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    @Test
    public void testDeleteBookNotFound(){
        when(booksRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()-> booksController.deleteBook(1L));


    }


}
