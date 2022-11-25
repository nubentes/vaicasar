package br.com.vaicasar.exceptions;

import java.util.List;

/*
 * Classe de exceção para erros de persistência. 
 */
public class RepositoryException extends MultipleMessagesException {

	private static final long serialVersionUID = 1010560749627397608L;
	private Exception exception;

	public RepositoryException(String message, Exception e) {
		super(message);
		this.setException(e);
	}
	
	public RepositoryException(List<ErrorItem> messages) {
		super(messages);
	}
	
	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
	
}
