package br.com.vaicasar.restcontrollers;

import java.nio.file.AccessDeniedException;
import java.util.Collection;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.vaicasar.exceptions.MultipleMessagesException;
import br.com.vaicasar.exceptions.NotAcceptableException;
import br.com.vaicasar.exceptions.NotFoundException;
import br.com.vaicasar.exceptions.RepositoryException;
import br.com.vaicasar.exceptions.RulesException;

public abstract class AbstractRestController {

	private Logger log = LoggerFactory.getLogger(AbstractRestController.class);
	
	/*
	 * Tarta erros de negócio. 
	 */
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(RulesException.class)
	public @ResponseBody MultipleMessagesException handleRulesException(RulesException ex) {
		return ex;
	}
	
	/*
	 * Trata erros de validação de constraint (Hibernate Validator). 
	 */
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(ConstraintViolationException.class)
	public @ResponseBody MultipleMessagesException handleRulesException(ConstraintViolationException ex) {
		return new MultipleMessagesException(ex);
	}
	
	/*
	 * Trata erros de validação de constraint (Hibernate Validator). 
	 */
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(TransactionException.class)
	public @ResponseBody MultipleMessagesException handleRulesException(TransactionException ex) {
		if (ex.getCause().getCause() instanceof ConstraintViolationException) {
			return new MultipleMessagesException((ConstraintViolationException) ex.getCause().getCause());
		}
		
		return new MultipleMessagesException(ex.getMessage());
	}
	
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public @ResponseBody MultipleMessagesException handleRulesException (MethodArgumentNotValidException ex) {
		return new MultipleMessagesException(ex);
	}
	
	/*
	 * Trata erros de negócio. 
	 */
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(HttpClientErrorException.class)
	public @ResponseBody MultipleMessagesException handleRulesException(HttpClientErrorException ex) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(ex.getResponseBodyAsString(), MultipleMessagesException.class);
		} catch (Exception e) {
			return new MultipleMessagesException(ex.getMessage());
		}
	}
	
	/*
	 * Trata erros de entidades não encontradas (NotFoundException) 
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public @ResponseBody MultipleMessagesException handleNotFoundException(NotFoundException ex) {
		return ex;
	}
	
	/*
	 * Trata erros de entidades não aceitas (NotAcceptableException)
	 */
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(NotAcceptableException.class)
	public @ResponseBody MultipleMessagesException handleNotFoundException(NotAcceptableException ex) {
		return ex;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(RepositoryException.class)
	public @ResponseBody MultipleMessagesException handleException(RepositoryException ex) {
		log.error("REST controller: exceção no repositório (500). " + ex.getMessage(), ex.getException());
		return new MultipleMessagesException(ex.getMessage());
	}
	
	/*
	 * Trata erros genéricos 
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public @ResponseBody MultipleMessagesException handleException(Exception ex) {
		log.error("REST controller: exceção genérica retornada (500). ", ex);
		return new MultipleMessagesException(ex.getMessage());
	}
	
	/*
	 * Trata erro de databind, ao transformar JSON em DTO 
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(JsonMappingException.class)
	public @ResponseBody MultipleMessagesException handleJsonMappingException(JsonMappingException ex) {
		log.error("REST controller: erro ao converter JSON em DTO (400)", ex);
		return new MultipleMessagesException(ex.getMessage());
	}
	
	/*
	 * Trata erro de databind, ao transformar JSON em DTO 
	 */
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(AccessDeniedException.class)
	public @ResponseBody MultipleMessagesException handleUnalthorized(JsonMappingException ex) {
		return new MultipleMessagesException(ex.getMessage());
	}
	
}
