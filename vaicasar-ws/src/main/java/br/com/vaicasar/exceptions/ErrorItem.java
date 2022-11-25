package br.com.vaicasar.exceptions;

import java.io.Serializable;

/* 
 * Item de erro com descrição e um código. 
 */
public class ErrorItem implements Serializable {

	private static final long serialVersionUID = -1432240658276136013L;

	private String message;
	private int code;
	
	public ErrorItem() {}
	
	public ErrorItem(String message, PositionMessage code) {
		setMessage(message);
		setCode(code.ordinal());
	}
	
	public ErrorItem(String message, int code) {
		setMessage(message);
		setCode(code);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return getMessage();
	}
	
}
