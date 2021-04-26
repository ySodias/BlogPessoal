package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Postagem;
import java.util.List;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{
	
	
	public List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);
}
