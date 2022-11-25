package br.com.vaicasar.exceptions;

import java.util.List;

/*
 * Disparada quando algum registro não é encontrado.
 */
public class NotFoundException extends MultipleMessagesException {

	private static final long serialVersionUID = 5586439908562906511L;

	public NotFoundException(String message) {
		super(message);
	}
	
	public NotFoundException(List<ErrorItem> messages) {
		super(messages);
	}

}
