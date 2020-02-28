package com.gft.livros.services.exceptions;

public class AutorExistenteException extends RuntimeException{

	
	private static final long serialVersionUID = -6914769072363653212L;

	public AutorExistenteException(String mensagem) {
		super(mensagem);
	}
	
	public AutorExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
