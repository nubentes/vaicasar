package br.com.vaicasar.exceptions;

import java.util.List;

/*
 * Dispara quando algum registro não é encontrado. 
 */
public class RulesException extends MultipleMessagesException {

	private static final long serialVersionUID = -2380705318975616103L;

	public RulesException(String message) {
		super(message);
	}
	
	public RulesException(ErrorItem message) {
		super(message);
	}
	
	public RulesException(List<ErrorItem> messages) {
		super(messages);
	}
	
}
