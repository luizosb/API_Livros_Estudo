package com.gft.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.livros.domain.Autor;

public interface AutoresRepository extends JpaRepository<Autor, Long>{

}
