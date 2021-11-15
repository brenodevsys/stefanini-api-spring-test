package com.brenodelgado.stefanini.repositorie;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brenodelgado.stefanini.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

		
}