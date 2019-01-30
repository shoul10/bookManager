package net.shoul.bookManager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.shoul.bookManager.exception.ResourceNotFoundException;
import net.shoul.bookManager.model.Book;
import net.shoul.bookManager.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	
	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	public Book findBookById(Integer bookId) {
		return bookRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book", "ID", bookId));
	}
	
	public Book addBook(Book bookRequest) {	
		return bookRepository.save(bookRequest);
	}
	
	public Book updateBook(Integer bookId, Book bookRequest) {
		return bookRepository.findById(bookId).map(book -> {
			book.setName(bookRequest.getName());
			book.setAuthor(bookRequest.getAuthor());
			book.setIsbn(bookRequest.getIsbn());
			book.setLanguage(bookRequest.getLanguage());
			book.setLength(bookRequest.getLength());
			book.setPublisher(bookRequest.getPublisher());
			book.setReleaseDate(bookRequest.getReleaseDate());
			book.setWeight(bookRequest.getWeight());
			book.setBookCategory(bookRequest.getBookCategory());
			return bookRepository.save(book);
		}).orElseThrow(() -> new ResourceNotFoundException("Mission", "id", bookId));
	}	
	
	public Boolean deleteBook(Integer bookId) {
		return bookRepository.findById(bookId).map(book -> {
			bookRepository.delete(book);
			return true;
		}).orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));		
	}

}
