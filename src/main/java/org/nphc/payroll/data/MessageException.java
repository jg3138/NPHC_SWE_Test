package org.nphc.payroll.data;

import org.springframework.http.HttpStatus;

/* Exception Handle */

public class MessageException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5922691849660140534L;
	private final HttpStatus status;
	
	public MessageException (String message) {
		super(message);
		status = HttpStatus.BAD_REQUEST;
	}
	
	public MessageException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}
	
	public HttpStatus getHttpStatus() {
		return status;
	}
}
