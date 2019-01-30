package net.shoul.bookManager.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.shoul.bookManager.model.BookCategory;
import net.shoul.bookManager.service.BookCategoryService;


@RestController
@RequestMapping("/api/bookCategories")
public class BookCategoryController {
	
	@Autowired
	private BookCategoryService bookCategoryService;
	
	
	@GetMapping
	public ResponseEntity<List<BookCategory>> getBookCategories() {
		return new ResponseEntity<>(bookCategoryService.findAllBookCategories(), HttpStatus.OK);
	}

	@GetMapping("/{bookCategoryId}")
	public ResponseEntity<BookCategory> getBookCategoryById(@PathVariable Integer bookCategoryId) {
		return new ResponseEntity<>(bookCategoryService.findBookCategoryById(bookCategoryId), HttpStatus.OK);		 
	}
	
	@PostMapping
	public ResponseEntity<BookCategory> createBookCategory(@RequestBody BookCategory bookCategoryRequest) {
		return new ResponseEntity<>(bookCategoryService.addBookCategory(bookCategoryRequest), HttpStatus.CREATED);
	}
	
	@PutMapping("/{bookCategoryId}")
	public ResponseEntity<BookCategory> updateBookCategory(@PathVariable Integer bookCategoryId, @Valid @RequestBody BookCategory bookCategoryRequest) {
		return new ResponseEntity<>(bookCategoryService.updateBookCategory(bookCategoryId, bookCategoryRequest), HttpStatus.OK);
	}	
		
	
	@DeleteMapping("/{bookCategoryId}")
	public ResponseEntity<Void> deleteBookCategory(@PathVariable Integer bookCategoryId) {
		bookCategoryService.deleteBookCategory(bookCategoryId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
