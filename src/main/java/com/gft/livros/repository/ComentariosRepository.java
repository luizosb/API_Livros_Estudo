package com.gft.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.livros.domain.Comentario;

public interface ComentariosRepository extends JpaRepository<Comentario, Long> {

}
