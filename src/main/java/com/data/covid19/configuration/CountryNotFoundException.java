package com.data.covid19.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Modify the 500 internal server error code to 404 not found error
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND,reason= "Country not found") 
public class CountryNotFoundException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CountryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
