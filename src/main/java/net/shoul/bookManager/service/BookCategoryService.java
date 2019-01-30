package net.shoul.bookManager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.shoul.bookManager.exception.ResourceNotFoundException;
import net.shoul.bookManager.model.BookCategory;
import net.shoul.bookManager.repository.BookCategoryRepository;


@Service
public class BookCategoryService {
	
	@Autowired
	private BookCategoryRepository bookCategoryRepository;
	
	
	public List<BookCategory> findAllBookCategories() {
		return bookCategoryRepository.findAll();
	}

	public BookCategory findBookCategoryById(Integer bookCategoryId) {
		return bookCategoryRepository.findById(bookCategoryId)
				.orElseThrow(() -> new ResourceNotFoundException("BookCategory", "ID", bookCategoryId));
	}
	
	public BookCategory addBookCategory(BookCategory bookCategoryRequest) {		
		return bookCategoryRepository.save(bookCategoryRequest);
	}
	
	public BookCategory updateBookCategory(Integer bookCategoryId, BookCategory bookCategoryRequest) {
		return bookCategoryRepository.findById(bookCategoryId).map(bookCategory -> {
			bookCategory.setName(bookCategoryRequest.getName());
			return bookCategoryRepository.save(bookCategory);
		}).orElseThrow(() -> new ResourceNotFoundException("Mission", "id", bookCategoryId));
	}	
	
	public Boolean deleteBookCategory(Integer bookCategoryId) {
		return bookCategoryRepository.findById(bookCategoryId).map(bookCategory -> {
			bookCategoryRepository.delete(bookCategory);
			return true;
		}).orElseThrow(() -> new ResourceNotFoundException("BookCategory", "id", bookCategoryId));
		
	}

}
