package com.brenodelgado.stefanini.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brenodelgado.stefanini.model.Book;
import com.brenodelgado.stefanini.repositorie.BookRepository;


@RestController
@RequestMapping("/book")
public class BookController {
	
	
	private BookRepository bookRepository;
	
	public BookController(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	@GetMapping
	public List<Book> getAll() {
		return bookRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getById(@PathVariable Long id) {
		return bookRepository.findById(id)
				.map(book -> ResponseEntity.ok(book))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Book saveBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book newBook) {
		if (!bookRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		newBook.setId(id);
		newBook = bookRepository.save(newBook);
		
		return ResponseEntity.ok(newBook);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (!bookRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		bookRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
		
	}
	


	
}
