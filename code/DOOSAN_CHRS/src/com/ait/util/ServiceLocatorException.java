package com.ait.util;

public class ServiceLocatorException extends NestedException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an exception with the given message
	 * 
	 * @param message
	 */
	public ServiceLocatorException(String message) {
		super(message);
	}

	public ServiceLocatorException(String message, Throwable cause) {
		super(message, cause);
	}

}
