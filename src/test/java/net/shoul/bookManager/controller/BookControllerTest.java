package net.shoul.bookManager.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import net.shoul.bookManager.BookManagerApplication;
import net.shoul.bookManager.model.Book;
import net.shoul.bookManager.model.BookCategory;
import net.shoul.bookManager.service.BookService;
import net.shoul.bookManager.utils.Utils;

@RunWith(SpringRunner.class)
@WebMvcTest(BookManagerApplication.class)
public class BookControllerTest {
	
	private MockMvc mockMvc;
	

	@Mock
	private BookService bookService;

	@InjectMocks
	private BookController bookController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
	}
	
	
	@Test
	public void getBookTest() throws Exception {
		// given
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss z");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		BookCategory bookcategory = new BookCategory("BookCategory 1");
		List<Book> bookes = Arrays.asList(new Book("Book 1","Author 1", "Language 1", "ISBN 1", date, "Publisher 1", 150L, 1.054, bookcategory),
				new Book("Book 2","Author 2", "Language 2", "ISBN 2", date, "Publisher 2", 160L, 1.344, bookcategory));

		// when
		when(bookService.findAllBooks()).thenReturn(bookes);
		mockMvc.perform(get("/api/books")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].name", is("Book 1")))
				.andExpect(jsonPath("$[0].author", is("Author 1")))
				.andExpect(jsonPath("$[0].language", is("Language 1")))
				.andExpect(jsonPath("$[0].isbn", is("ISBN 1")))
				.andExpect(jsonPath("$[0].releaseDate", is(sdf.format(date))))
				.andExpect(jsonPath("$[0].publisher", is("Publisher 1")))
				.andExpect(jsonPath("$[0].length", is(150)))
				.andExpect(jsonPath("$[0].weight", is(1.054)))
				.andExpect(jsonPath("$[0].bookCategory.name", is("BookCategory 1")))				
				
				.andExpect(jsonPath("$[1].name", is("Book 2")))
				.andExpect(jsonPath("$[1].author", is("Author 2")))
				.andExpect(jsonPath("$[1].language", is("Language 2")))
				.andExpect(jsonPath("$[1].isbn", is("ISBN 2")))
				.andExpect(jsonPath("$[1].releaseDate", is(sdf.format(date))))
				.andExpect(jsonPath("$[1].publisher", is("Publisher 2")))
				.andExpect(jsonPath("$[1].length", is(160)))
				.andExpect(jsonPath("$[1].weight", is(1.344)))
				.andExpect(jsonPath("$[1].bookCategory.name", is("BookCategory 1")));
		verify(bookService, times(1)).findAllBooks();
		verifyNoMoreInteractions(bookService);
	}

	@Test
	public void getBookByIdTest() throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss z");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		BookCategory bookcategory = new BookCategory("BookCategory 1");
		Book book = new Book("Book 1","Author 1", "Language 1", "ISBN 1", date, "Publisher 1", 150L, 1.054, bookcategory);
		when(bookService.findBookById(1)).thenReturn(book);
		mockMvc.perform(get("/api/books/{bookId}", 1)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.name", is("Book 1")))
				.andExpect(jsonPath("$.author", is("Author 1")))
				.andExpect(jsonPath("$.language", is("Language 1")))
				.andExpect(jsonPath("$.isbn", is("ISBN 1")))
				.andExpect(jsonPath("$.releaseDate", is(sdf.format(date))))
				.andExpect(jsonPath("$.publisher", is("Publisher 1")))
				.andExpect(jsonPath("$.length", is(150)))
				.andExpect(jsonPath("$.weight", is(1.054)))
				.andExpect(jsonPath("$.bookCategory.name", is("BookCategory 1")));
		verify(bookService, times(1)).findBookById(1);
		verifyNoMoreInteractions(bookService);
	}

	
	@Test
	public void addBookTest() throws Exception {
		Date date = new Date();
		BookCategory bookcategory = new BookCategory("BookCategory 1");
		Book bookRequest = new Book("Book 1","Author 1", "Language 1", "ISBN 1", date, "Publisher 1", 150L, 1.054, bookcategory);
		Book book = new Book("Book 1","Author 1", "Language 1", "ISBN 1", date, "Publisher 1", 150L, 1.054, bookcategory);
		when(bookService.addBook(bookRequest)).thenReturn(book);
		mockMvc.perform(
				post("/api/books").contentType(MediaType.APPLICATION_JSON).content(Utils.asJsonString(book)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void updateBookTest() throws Exception {
		Date date = new Date();
		BookCategory bookcategory = new BookCategory("BookCategory 1");
		Book bookRequest = new Book("Book 1","Author 1", "Language 1", "ISBN 1", date, "Publisher 1", 150L, 1.054, bookcategory);
		Book book = new Book("Book 1","Author 1", "Language 1", "ISBN 1", date, "Publisher 1", 150L, 1.054, bookcategory);
		when(bookService.updateBook(book.getId(), bookRequest)).thenReturn(book);
		mockMvc.perform(put("/api/books/{bookId}", book.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(Utils.asJsonString(book))).andExpect(status().isOk());
	}	
	
	@Test
	public void deleteBookTest() throws Exception {
		Date date = new Date();
		BookCategory bookcategory = new BookCategory("BookCategory 1");
		Book book = new Book("Book 1","Author 1", "Language 1", "ISBN 1", date, "Publisher 1", 150L, 1.054, bookcategory);
		when(bookService.deleteBook(book.getId())).thenReturn(true);
		mockMvc.perform(delete("/api/books/{bookId}", book.getId())).andExpect(status().isOk());
		verify(bookService, times(1)).deleteBook(book.getId());
		verifyNoMoreInteractions(bookService);
	}

	@Test
	public void badRequestGetBookByIdTest() throws Exception {
		mockMvc.perform(get("/api/books/{bookId}", "f")).andExpect(status().isBadRequest());
	}

}
