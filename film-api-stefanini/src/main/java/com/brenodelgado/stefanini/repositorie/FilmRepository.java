package com.brenodelgado.stefanini.repositorie;



import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brenodelgado.stefanini.model.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long>{
	
   
    public List<Film> findByTitle(String title);
    public List<Film> findByTitleContaining(String title);
		
}