package br.com.vaicasar.exceptions;

import java.util.List;

public class NotAcceptableException extends MultipleMessagesException {

	private static final long serialVersionUID = -1130286227485708236L;

	public NotAcceptableException(String message) {
		super(message);
	}
	
	public NotAcceptableException(List<ErrorItem> messages) {
		super(messages);
	}
}
