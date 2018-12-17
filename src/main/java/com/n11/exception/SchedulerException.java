package com.n11.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class SchedulerException extends RuntimeException {

	public SchedulerException(String message) {
		super(message);
	}
}
