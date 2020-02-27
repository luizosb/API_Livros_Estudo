package com.gft.livros.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.livros.domain.Comentario;
import com.gft.livros.domain.Livro;
import com.gft.livros.repository.ComentariosRepository;
import com.gft.livros.repository.LivrosRepository;
import com.gft.livros.services.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService{
	
	@Autowired
	private LivrosRepository livrosRepo;
	
	@Autowired
	private ComentariosRepository comentRepo;
	
	public List<Livro> listar(){
		return livrosRepo.findAll();
	}
	
	public Livro buscar(Long id) {
		Livro livro = livrosRepo.findById(id).get();
		
		if(livro == null) {
			throw new LivroNaoEncontradoException("O livro não foi encontrado.");
		}
		
		return livro;
	}
	
	public Livro salvar(Livro livro) {
		livro.setId(null);
		return livrosRepo.save(livro);
		
	}
	
	public void deletar(Long id) {
		try {
			livrosRepo.deleteById(id);				
		} catch (EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoException("O livro não pode ser encontrado.");
		}
	}
	
	public void atualizar(Livro livro) {
		verificarExistencia(livro);
		livrosRepo.save(livro);
	}
	
	private void verificarExistencia(Livro livro) {
		buscar(livro.getId());
	}
	
	public Comentario salvarComentario(Long livroId, Comentario comentario) {
		Livro livro = buscar(livroId);
		
		comentario.setLivro(livro);
		comentario.setData(new Date());
		
		return comentRepo.save(comentario);
		
	}
	
	public List<Comentario> listarComentario(Long livroId){
		Livro livro = buscar(livroId);
		
		return livro.getComentarios();
	}
	
} 







