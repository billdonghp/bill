package com.ait.utils;

/**
 * <p>
 * Title: AIT HOMEPAGE
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: AIT
 * </p>
 * 
 * @author AIT
 * @version 1.0
 */

public class NotExistException extends LException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * MessageException constructor comment.
	 */
	public NotExistException() {
		super();
	}

	/**
	 * MessageException constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 */
	public NotExistException(String s) {
		super(s);
	}

	/**
	 * MessageException constructor comment.
	 * 
	 * @param message
	 *            java.lang.String
	 * @param rootCause
	 *            java.lang.Throwable
	 */
	public NotExistException(String message, Throwable rootCause) {
		super(message, rootCause);
	}

	/**
	 * MessageException constructor comment.
	 * 
	 * @param rootCause
	 *            java.lang.Throwable
	 */
	public NotExistException(Throwable rootCause) {
		super(rootCause);
	}
}
