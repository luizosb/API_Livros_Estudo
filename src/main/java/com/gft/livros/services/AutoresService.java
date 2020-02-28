package com.gft.livros.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.livros.domain.Autor;
import com.gft.livros.repository.AutoresRepository;
import com.gft.livros.services.exceptions.AutorExistenteException;
import com.gft.livros.services.exceptions.AutorNaoEncontradoException;

@Service
public class AutoresService {

	@Autowired
	private AutoresRepository autoresRepo;
	
	public List<Autor> listar(){
		return autoresRepo.findAll();
	}
	
	public Autor salvar(Autor autor) {
		if(autor.getId() != null) {
			Optional<Autor> a = autoresRepo.findById(autor.getId());
			if (a.isPresent()) {
				throw new AutorExistenteException("Autor já existe!");
			}
		}
		return autoresRepo.save(autor);
	}
	
	public Autor buscar(Long id) {
		Autor autor = autoresRepo.findById(id).get();
		
		if(autor == null) {
			throw new AutorNaoEncontradoException("O autor não pode ser encontrado!");
		}
		return autor; 
		
	}
}
