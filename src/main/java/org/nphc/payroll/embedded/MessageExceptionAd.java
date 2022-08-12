package org.nphc.payroll.embedded;

import java.io.IOException;
import java.sql.SQLException;

import org.nphc.payroll.data.MessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/* Used for spring boot sequence */

@ControllerAdvice
public class MessageExceptionAd extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(MessageException.class)
	public @ResponseBody ResponseEntity<Object> handleMessageException(MessageException mesex) {
		JsonMapper mapper = new JsonMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("message", mesex.getMessage());
		return new ResponseEntity<>(objectNode, mesex.getHttpStatus());
	}
	
	@ExceptionHandler(SQLException.class)
	public @ResponseBody ResponseEntity<Object> handleSQLException(SQLException sqlex) {
		JsonMapper mapper = new JsonMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("message", sqlex.getMessage());
		return new ResponseEntity<>(objectNode,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IOException.class)
	public @ResponseBody ResponseEntity<Object> handleIOException(IOException sqlex) {
		JsonMapper mapper = new JsonMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("message", sqlex.getMessage());
		return new ResponseEntity<>(objectNode,HttpStatus.BAD_REQUEST);
	}
}
