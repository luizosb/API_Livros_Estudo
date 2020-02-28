package com.gft.livros.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gft.livros.domain.DetalhesErro;
import com.gft.livros.services.exceptions.AutorExistenteException;
import com.gft.livros.services.exceptions.AutorNaoEncontradoException;
import com.gft.livros.services.exceptions.LivroNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(LivroNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleLivroNaoEncontradoException(LivroNaoEncontradoException e, 
																	HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("O livro não pode ser encontrado!");
		erro.setMensagemDesenvolvedor("http://erros.livros.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(AutorExistenteException.class)
	public ResponseEntity<DetalhesErro> handleAutorExistenteException(AutorExistenteException e, 
																	HttpServletRequest request){
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("O autor já existe!");
		erro.setMensagemDesenvolvedor("http://erros.livros.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(AutorNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleAutorNaoEncontradoException(AutorNaoEncontradoException e, 
																	HttpServletRequest request){
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("O autor não existe!");
		erro.setMensagemDesenvolvedor("http://erros.livros.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException(DataIntegrityViolationException e, 
																	HttpServletRequest request){
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("Requisição inválida.");
		erro.setMensagemDesenvolvedor("http://erros.livros.com/400");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}
