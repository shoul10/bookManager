package net.shoul.bookManager.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import net.shoul.bookManager.model.BookCategory;

@RunWith(SpringJUnit4ClassRunner.class)
public class BookCategoryServiceTest {
	
	@Mock
	private BookCategoryService bookCategoryService;
	
	@Before
	public void setup(){
		// With this call to initMocks we tell Mockito to process the annotations
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void findAllBookCategorysTest(){
		// given
		List<BookCategory> bookCategoryList = new ArrayList<BookCategory>();
		bookCategoryList.add(new BookCategory("BookCategory 1"));
		bookCategoryList.add(new BookCategory("BookCategory 2"));
		bookCategoryList.add(new BookCategory("BookCategory 3"));
		
		// when
		when(bookCategoryService.findAllBookCategories()).thenReturn(bookCategoryList);
		
		// assert
		List<BookCategory> result = bookCategoryService.findAllBookCategories();
		assertEquals(3, result.size());		
		assertThat(result.get(0).getName()).isEqualTo("BookCategory 1");        
		assertThat(result.get(1).getName()).isEqualTo("BookCategory 2");        
		assertThat(result.get(2).getName()).isEqualTo("BookCategory 3");
		
	}
	
	@Test
	public void findBookCategoryByIdTest(){
		// given		
		BookCategory bookCategory = new BookCategory("BookCategory 1");
		
		// when
		when(bookCategoryService.findBookCategoryById(1)).thenReturn(bookCategory);
		
		// assert
		BookCategory result = bookCategoryService.findBookCategoryById(1);
		assertThat(result.getName()).isEqualTo("BookCategory 1");
		
		
	}
	
	@Test
    public void createBookCategoryTest() {
        // given		
		BookCategory bookCategoryRequest = new BookCategory("BookCategory 1");		
		BookCategory bookCategory = new BookCategory("BookCategory 1");
		
        // when
        when(bookCategoryService.addBookCategory(bookCategoryRequest)).thenReturn(bookCategory);

        // assert
        BookCategory result = bookCategoryService.addBookCategory(bookCategoryRequest);
        assertThat(result.getName()).isEqualTo("BookCategory 1");
		
    }
	
	@Test
    public void updateBookCategoryTest() {
        // given		
		BookCategory bookCategoryRequest = new BookCategory("BookCategory 1");		
		BookCategory bookCategory = new BookCategory("BookCategory 1");
		
        // when
        when(bookCategoryService.updateBookCategory(1, bookCategoryRequest)).thenReturn(bookCategory);

        // assert
        BookCategory result = bookCategoryService.updateBookCategory(1, bookCategoryRequest);
        assertThat(result.getName()).isEqualTo("BookCategory 1");		
    }	
	
	@Test
    public void deleteBookCategoryTest() {
        // given		
		BookCategory bookCategory = new BookCategory("BookCategory 1");
		
        // when
        when(bookCategoryService.deleteBookCategory(bookCategory.getId())).thenReturn(true);

        // assert
        Boolean result = bookCategoryService.deleteBookCategory(bookCategory.getId());
        assertThat(result).isEqualTo(true);
    }


}
