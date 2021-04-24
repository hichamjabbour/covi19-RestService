package com.data.covid19;

public class SummaryNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SummaryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}