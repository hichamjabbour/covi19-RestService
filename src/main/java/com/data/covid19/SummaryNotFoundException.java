package com.data.covid19;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND,reason= "Country not found") //modify the 500 internal server error code to 404 not found error
public class SummaryNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SummaryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}