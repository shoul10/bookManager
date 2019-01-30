package net.shoul.bookManager.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import net.shoul.bookManager.model.Book;
import net.shoul.bookManager.model.BookCategory;

@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceTest {
	
	
	@Mock
	private BookService bookService;
	
	@Before
	public void setup(){
		// With this call to initMocks we tell Mockito to process the annotations
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void findAllBooksTest(){
		// given
		Date date = new Date();
		BookCategory bookcategory = new BookCategory("BookCategory 1");
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(new Book("Book 1","Autor 1", "Language 1", "ISBN 1", date, "Publisher 1", 150L, 1.054, bookcategory));
		bookList.add(new Book("Book 2","Autor 2", "Language 2", "ISBN 2", date, "Publisher 2", 160L, 1.344, bookcategory));
		bookList.add(new Book("Book 3","Autor 3", "Language 3", "ISBN 3", date, "Publisher 3", 170L, 1.94, bookcategory));
		
		// when
		when(bookService.findAllBooks()).thenReturn(bookList);
		
		// assert
		List<Book> result = bookService.findAllBooks();
		assertEquals(3, result.size());
		
		assertThat(result.get(0).getName()).isEqualTo("Book 1");
		assertThat(result.get(0).getAuthor()).isEqualTo("Autor 1");
        assertThat(result.get(0).getLanguage()).isEqualTo("Language 1");
        assertThat(result.get(0).getIsbn()).isEqualTo("ISBN 1");
        assertThat(result.get(0).getReleaseDate()).isEqualTo(date);
		assertThat(result.get(0).getPublisher()).isEqualTo("Publisher 1");
        assertThat(result.get(0).getLength()).isEqualTo(150L);
        assertThat(result.get(0).getWeight()).isEqualTo(1.054);
        assertThat(result.get(0).getBookCategory()).isEqualTo(bookcategory);
        assertThat(result.get(0).getBookCategory().getName()).isEqualTo(bookcategory.getName());
        
		assertThat(result.get(1).getName()).isEqualTo("Book 2");
		assertThat(result.get(1).getAuthor()).isEqualTo("Autor 2");
        assertThat(result.get(1).getLanguage()).isEqualTo("Language 2");
        assertThat(result.get(1).getIsbn()).isEqualTo("ISBN 2");
        assertThat(result.get(1).getReleaseDate()).isEqualTo(date);
		assertThat(result.get(1).getPublisher()).isEqualTo("Publisher 2");
        assertThat(result.get(1).getLength()).isEqualTo(160L);
        assertThat(result.get(1).getWeight()).isEqualTo(1.344);
        assertThat(result.get(1).getBookCategory()).isEqualTo(bookcategory);
        assertThat(result.get(1).getBookCategory().getName()).isEqualTo(bookcategory.getName());
        
		assertThat(result.get(2).getName()).isEqualTo("Book 3");
		assertThat(result.get(2).getAuthor()).isEqualTo("Autor 3");
        assertThat(result.get(2).getLanguage()).isEqualTo("Language 3");
        assertThat(result.get(2).getIsbn()).isEqualTo("ISBN 3");
        assertThat(result.get(2).getReleaseDate()).isEqualTo(date);
		assertThat(result.get(2).getPublisher()).isEqualTo("Publisher 3");
        assertThat(result.get(2).getLength()).isEqualTo(170L);
        assertThat(result.get(2).getWeight()).isEqualTo(1.94);
        assertThat(result.get(2).getBookCategory()).isEqualTo(bookcategory);
        assertThat(result.get(2).getBookCategory().getName()).isEqualTo(bookcategory.getName());
	}
	
	@Test
	public void findBookByIdTest(){
		// given
		Date date = new Date();
		BookCategory bookcategory = new BookCategory("BookCategory 1");
		Book book = new Book("Book 1","Autor 1", "Language 1", "ISBN 1", date, "Publisher 1", 150L, 1.054, bookcategory);
		
		// when
		when(bookService.findBookById(1)).thenReturn(book);
		
		// assert
		Book result = bookService.findBookById(1);
		assertThat(result.getName()).isEqualTo("Book 1");
		assertThat(result.getAuthor()).isEqualTo("Autor 1");
        assertThat(result.getLanguage()).isEqualTo("Language 1");
        assertThat(result.getIsbn()).isEqualTo("ISBN 1");
        assertThat(result.getReleaseDate()).isEqualTo(date);
		assertThat(result.getPublisher()).isEqualTo("Publisher 1");
        assertThat(result.getLength()).isEqualTo(150L);
        assertThat(result.getWeight()).isEqualTo(1.054);
        assertThat(result.getBookCategory()).isEqualTo(bookcategory);
        assertThat(result.getBookCategory().getName()).isEqualTo(bookcategory.getName());
		
	}
	
	@Test
    public void createBookTest() {
        // given
		Date date = new Date();
		BookCategory bookcategory = new BookCategory("BookCategory 1");
		Book bookRequest = new Book("Book 1","Autor 1", "Language 1", "ISBN 1",date, "Publisher 1", 150L, 1.054, bookcategory);		
		Book book = new Book("Book 1","Autor 1", "Language 1", "ISBN 1", date, "Publisher 1", 150L, 1.054, bookcategory);
		
        // when
        when(bookService.addBook(bookRequest)).thenReturn(book);

        // assert
        Book result = bookService.addBook(bookRequest);
        assertThat(result.getName()).isEqualTo("Book 1");
		assertThat(result.getAuthor()).isEqualTo("Autor 1");
        assertThat(result.getLanguage()).isEqualTo("Language 1");
        assertThat(result.getIsbn()).isEqualTo("ISBN 1");
        assertThat(result.getReleaseDate()).isEqualTo(date);
		assertThat(result.getPublisher()).isEqualTo("Publisher 1");
        assertThat(result.getLength()).isEqualTo(150L);
        assertThat(result.getWeight()).isEqualTo(1.054);
        assertThat(result.getBookCategory()).isEqualTo(bookcategory);
        assertThat(result.getBookCategory().getName()).isEqualTo(bookcategory.getName());
    }
	
	@Test
    public void updateBookTest() {
        // given
		Date date = new Date();
		BookCategory bookcategory = new BookCategory("BookCategory 1");
		Book bookRequest = new Book("Book 1","Autor 1", "Language 1", "ISBN 1",date, "Publisher 1", 150L, 1.054, bookcategory);		
		Book book = new Book("Book 1","Autor 1", "Language 1", "ISBN 1", date, "Publisher 1", 150L, 1.054, bookcategory);
		
        // when
        when(bookService.updateBook(1, bookRequest)).thenReturn(book);

        // assert
        Book result = bookService.updateBook(1, bookRequest);
        assertThat(result.getName()).isEqualTo("Book 1");
		assertThat(result.getAuthor()).isEqualTo("Autor 1");
        assertThat(result.getLanguage()).isEqualTo("Language 1");
        assertThat(result.getIsbn()).isEqualTo("ISBN 1");
        assertThat(result.getReleaseDate()).isEqualTo(date);
		assertThat(result.getPublisher()).isEqualTo("Publisher 1");
        assertThat(result.getLength()).isEqualTo(150L);
        assertThat(result.getWeight()).isEqualTo(1.054);
        assertThat(result.getBookCategory()).isEqualTo(bookcategory);
        assertThat(result.getBookCategory().getName()).isEqualTo(bookcategory.getName());
    }	
	
	@Test
    public void deleteBookTest() {
        // given
		Date date = new Date();
		BookCategory bookcategory = new BookCategory("BookCategory 1");
		Book book = new Book("Book 1","Autor 1", "Language 1", "ISBN 1", date, "Publisher 1", 150L, 1.054, bookcategory);
		
        // when
        when(bookService.deleteBook(book.getId())).thenReturn(true);

        // assert
        Boolean result = bookService.deleteBook(book.getId());
        assertThat(result).isEqualTo(true);
    }

}
