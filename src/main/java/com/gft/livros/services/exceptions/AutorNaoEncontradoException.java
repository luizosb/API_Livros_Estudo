package com.gft.livros.services.exceptions;

public class AutorNaoEncontradoException extends RuntimeException{

	
	private static final long serialVersionUID = -6914769072363653212L;

	public AutorNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public AutorNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
