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
import net.shoul.bookManager.model.Book;
import net.shoul.bookManager.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	
	@GetMapping
	public ResponseEntity<List<Book>> getBooks() {
		return new ResponseEntity<>(bookService.findAllBooks(), HttpStatus.OK);
	}

	@GetMapping("/{bookId}")
	public ResponseEntity<Book> getBookById(@PathVariable Integer bookId) {
		return new ResponseEntity<>(bookService.findBookById(bookId), HttpStatus.OK);		 
	}
	
	@PostMapping
	public ResponseEntity<Book> createBook(@RequestBody Book bookRequest) {
		return new ResponseEntity<>(bookService.addBook(bookRequest), HttpStatus.CREATED);
	}
	
	@PutMapping("/{bookId}")
	public ResponseEntity<Book> updateBook(@PathVariable Integer bookId, @Valid @RequestBody Book bookRequest) {
		return new ResponseEntity<>(bookService.updateBook(bookId, bookRequest), HttpStatus.OK);	
	}	
		
	
	@DeleteMapping("/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable Integer bookId) {
		bookService.deleteBook(bookId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
