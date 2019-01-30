package net.shoul.bookManager.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;
import java.util.List;
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
import net.shoul.bookManager.model.BookCategory;
import net.shoul.bookManager.service.BookCategoryService;
import net.shoul.bookManager.utils.Utils;

@RunWith(SpringRunner.class)
@WebMvcTest(BookManagerApplication.class)
public class BookCategoryControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private BookCategoryService bookCategoryService;

	@InjectMocks
	private BookCategoryController bookCategoryController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(bookCategoryController).build();
	}
	
	
	@Test
	public void getBookCategorysTest() throws Exception {
		// given
		List<BookCategory> bookCategories = Arrays.asList(new BookCategory("BookCategory 1"),
				new BookCategory("BookCategory 2"));

		// when
		when(bookCategoryService.findAllBookCategories()).thenReturn(bookCategories);
		mockMvc.perform(get("/api/bookCategories")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].name", is("BookCategory 1")))				
				.andExpect(jsonPath("$[1].name", is("BookCategory 2")));
		verify(bookCategoryService, times(1)).findAllBookCategories();
		verifyNoMoreInteractions(bookCategoryService);
	}
	
	@Test
	public void getBookCategoryByIdTest() throws Exception {
		BookCategory bookCategory = new BookCategory("BookCategory 1");
		when(bookCategoryService.findBookCategoryById(1)).thenReturn(bookCategory);
		mockMvc.perform(get("/api/bookCategories/{bookCategoryId}", 1)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.name", is("BookCategory 1")));
		verify(bookCategoryService, times(1)).findBookCategoryById(1);
		verifyNoMoreInteractions(bookCategoryService);
	}
	
	@Test
	public void addBookCategoryTest() throws Exception {
		BookCategory bookCategoryRequest = new BookCategory("BookCategory 1");
		BookCategory bookCategory = new BookCategory("BookCategory 1");
		when(bookCategoryService.addBookCategory(bookCategoryRequest)).thenReturn(bookCategory);
		mockMvc.perform(
				post("/api/bookCategories").contentType(MediaType.APPLICATION_JSON).content(Utils.asJsonString(bookCategory)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void updateBookCategoryTest() throws Exception {
		BookCategory bookCategoryRequest = new BookCategory("BookCategory 1");
		BookCategory bookCategory = new BookCategory("BookCategory 1");
		when(bookCategoryService.updateBookCategory(bookCategory.getId(), bookCategoryRequest)).thenReturn(bookCategory);
		mockMvc.perform(put("/api/bookCategories/{bookCategoryId}", bookCategory.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(Utils.asJsonString(bookCategory))).andExpect(status().isOk());
	}
	
	@Test
	public void deleteBookCategoryTest() throws Exception {
		BookCategory bookCategory = new BookCategory("BookCategory 1");
		when(bookCategoryService.deleteBookCategory(bookCategory.getId())).thenReturn(true);
		mockMvc.perform(delete("/api/bookCategories/{bookCategoryId}", bookCategory.getId())).andExpect(status().isOk());
		verify(bookCategoryService, times(1)).deleteBookCategory(bookCategory.getId());
		verifyNoMoreInteractions(bookCategoryService);		
	}
	
	@Test
	public void badRequestGetBookCategoryByIdTest() throws Exception {
		mockMvc.perform(get("/api/bookCategories/{bookId}", "f")).andExpect(status().isBadRequest());
	}
	

}
