package br.com.vaicasar.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Exceções com múltiplas mensages de erro 
 */
@JsonIgnoreProperties(value = {"message", "localizedMessage", "cause", "stackTrace", "suppressed"})
public class MultipleMessagesException extends RuntimeException {

	private static final long serialVersionUID = -8252517260728250736L;

	private List<ErrorItem> messages;
	
	public MultipleMessagesException(String message) {
		this(Arrays.asList(new ErrorItem(message, 0)));
	}
	
	public MultipleMessagesException(ErrorItem message) {
		this(Arrays.asList(message));
	}
	
	public MultipleMessagesException(List<ErrorItem> messages) {
		setMessages(messages);
	}
	
	public MultipleMessagesException(ConstraintViolationException ex) {
		messages = new ArrayList<>();
		for (ConstraintViolation<?> constraint : ex.getConstraintViolations()) {
			messages.add(new ErrorItem(constraint.getMessage(), 0));
		}
		
		orderMessages();
	}
	
	public MultipleMessagesException(MethodArgumentNotValidException ex) {
		messages = new ArrayList<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			messages.add(new ErrorItem(error.getDefaultMessage(), 0));
		}
		
		orderMessages();
	}
	
	private void orderMessages() {
		Collections.sort(messages, (ErrorItem e1, ErrorItem e2)->e1.getMessage().compareTo(e2.getMessage()));
	}
	
	/*
	 * Lista de mensagens de erro dessa exceção 
	 */
	public List<ErrorItem> getMenssages() {
		return this.messages;
	}
	
	public void setMessages(List<ErrorItem> messages) {
		if (messages == null) {
			throw new IllegalArgumentException("Exception argument list canoot be null");
		}
		
		this.messages = messages;
		orderMessages();
	}
	
	@Override
	@JsonIgnore
	public String getMessage() {
		return toString();
	}
	
	@Override
	@JsonIgnore
	public String getLocalizedMessage() {
		return toString();
	}
	
	@Override
	@JsonIgnore
	public String toString() {
		return this.messages.toString();
	}
}
