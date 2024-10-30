package com.in28minutes.springboot.rest.example.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.in28minutes.springboot.rest.example.configuration.FlmAddressUpdateConstants;

import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

	@ExceptionHandler(InternalServiceException.class)
	public ResponseEntity<ErrorMessage> internalServiceException(InternalServiceException ex, WebRequest request) {
		Map<String, String> details = new HashMap<>();
		if (ex.getMessage().toLowerCase()
				.contains(FlmAddressUpdateConstants.EMPTY_RESPONSE_ADDRESS_API_TAG.toLowerCase())
				|| ex.getMessage().toLowerCase()
						.contains(FlmAddressUpdateConstants.EMPTY_ACCESS_TOKEN_SERVICE.toLowerCase())) {
			details.put(FlmAddressUpdateConstants.ERROR_LABEL,
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR) + StringUtils.SPACE + ex.getMessage());
			ErrorMessage message = ErrorMessage.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.timestamp(new Date()).message(details).build();
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		details.put(FlmAddressUpdateConstants.ERROR_LABEL,
				String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR) + StringUtils.SPACE + ex.getMessage());
		ErrorMessage message = ErrorMessage.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.timestamp(new Date()).message(details).build();
		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		Map<String, String> details = new HashMap<>();
		details.put(FlmAddressUpdateConstants.ERROR_LABEL,
				String.valueOf(HttpStatus.NOT_FOUND) + StringUtils.SPACE + ex.getMessage());
		ErrorMessage message = ErrorMessage.builder().statusCode(HttpStatus.NOT_FOUND.value()).timestamp(new Date())
				.message(details).build();
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RestTemplateException.class)
	public ResponseEntity<ErrorMessage> restTemplateException(RestTemplateException ex, WebRequest request) {
		Map<String, String> details = new HashMap<>();
		if (ex.getMessage().toLowerCase().contains(FlmAddressUpdateConstants.BAD_REQUEST_400.toLowerCase())
				&& ex.getMessage().toLowerCase().contains(FlmAddressUpdateConstants.VINTAGE_LABEL.toLowerCase())) {
			details.put(FlmAddressUpdateConstants.ERROR_LABEL, String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY)
					+ StringUtils.SPACE + FlmAddressUpdateConstants.VINTAGE_ERROR);
			ErrorMessage message = ErrorMessage.builder().statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
					.timestamp(new Date()).message(details).build();
			return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (ex.getMessage().toLowerCase().contains(FlmAddressUpdateConstants.BAD_REQUEST_400.toLowerCase())) {
			details.put(FlmAddressUpdateConstants.ERROR_LABEL,
					String.valueOf(HttpStatus.BAD_REQUEST) + StringUtils.SPACE + ex.getMessage());
			ErrorMessage message = ErrorMessage.builder().statusCode(HttpStatus.BAD_REQUEST.value())
					.timestamp(new Date()).message(details).build();
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		details.put(FlmAddressUpdateConstants.ERROR_LABEL,
				String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR) + StringUtils.SPACE + ex.getMessage());
		ErrorMessage message = ErrorMessage.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.timestamp(new Date()).message(details).build();
		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
		Map<String, String> details = new HashMap<>();
		if (ex.getMessage().toLowerCase().contains(FlmAddressUpdateConstants.JSON_ERROR.toLowerCase()) || ex
				.getMessage().toLowerCase().contains(FlmAddressUpdateConstants.REQUEST_MISSING_ERROR.toLowerCase())) {
			details.put(FlmAddressUpdateConstants.ERROR_LABEL,
					String.valueOf(HttpStatus.BAD_REQUEST) + StringUtils.SPACE + ex.getMessage());
			ErrorMessage message = ErrorMessage.builder().statusCode(HttpStatus.BAD_REQUEST.value())
					.timestamp(new Date()).message(details).build();
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		if (ex.getMessage().toLowerCase().contains(FlmAddressUpdateConstants.REQUEST_METHOD_LABLE.toLowerCase()) && ex
				.getMessage().toLowerCase().contains(FlmAddressUpdateConstants.NOT_SUPPORTED_LABEL.toLowerCase())) {
			details.put(FlmAddressUpdateConstants.ERROR_LABEL,
					String.valueOf(HttpStatus.METHOD_NOT_ALLOWED) + StringUtils.SPACE + ex.getMessage());
			ErrorMessage message = ErrorMessage.builder().statusCode(HttpStatus.METHOD_NOT_ALLOWED.value())
					.timestamp(new Date()).message(details).build();
			return new ResponseEntity<>(message, HttpStatus.METHOD_NOT_ALLOWED);
		}
		details.put(FlmAddressUpdateConstants.ERROR_LABEL,
				String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR) + StringUtils.SPACE + ex.getMessage());
		ErrorMessage message = ErrorMessage.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.timestamp(new Date()).message(details).build();
		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			WebRequest request) {
		ErrorMessage errors = ErrorMessage.builder().build();
		Map<String, String> details = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> details.put(error.getField(), error.getDefaultMessage()));
		errors.setMessage(details);
		errors.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errors.setTimestamp(new Date());
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}
