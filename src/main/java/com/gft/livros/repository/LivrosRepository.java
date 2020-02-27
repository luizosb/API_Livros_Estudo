package com.gft.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.livros.domain.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long>{

}
