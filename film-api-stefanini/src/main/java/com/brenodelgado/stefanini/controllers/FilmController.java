package com.brenodelgado.stefanini.controllers;

import java.util.List;
import java.util.Scanner;


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

import com.brenodelgado.stefanini.model.Film;
import com.brenodelgado.stefanini.model.Film;
import com.brenodelgado.stefanini.repositorie.FilmRepository;


@RestController
@RequestMapping("/film")
public class FilmController {
		
	private FilmRepository filmRepository;
	
	public FilmController(FilmRepository filmRepository) {
		super();
		this.filmRepository = filmRepository;
	}
	

	@GetMapping
	public List<Film> getAll() {
		return filmRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Film> getById(@PathVariable Long id) {
		return filmRepository.findById(id)
				.map(book -> ResponseEntity.ok(book))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Film saveBook(@RequestBody Film film) {
		return filmRepository.save(film);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Film> update(@PathVariable Long id, @RequestBody Film newFilm) {
		if (!filmRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		newFilm.setId(id);
		newFilm = filmRepository.save(newFilm);
		
		return ResponseEntity.ok(newFilm);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (!filmRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		filmRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping("/findByTitle")
	public List<Film> getByTitle(@RequestBody Film film) {
		return filmRepository.findByTitle(film.getTitle());
	} 

}
