package com.ait.utils;

/**
 * <p>Title: AIT  HOMEPAGE</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: AIT</p>
 * @author AIT
 * @version 1.0
 */

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class LException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Throwable rootCause;

	private boolean isFirst;

	public LException() {
		isFirst = true;
	}

	public LException(String s) {
		super(s);
		isFirst = true;
	}

	public LException(String s, Throwable throwable) {
		super(s);
		isFirst = true;
		rootCause = throwable;
		isFirst = false;
	}

	public LException(Throwable throwable) {
		this();
		rootCause = throwable;
		isFirst = false;
	}

	public Throwable getRootCause() {
		return rootCause;
	}

	public String getStackTraceString() {
		StringWriter stringwriter = new StringWriter();
		printStackTrace(new PrintWriter(stringwriter));
		return stringwriter.toString();
	}

	public void printStackTrace() {
		printStackTrace(System.err);
	}

	public void printStackTrace(PrintStream printstream) {
		synchronized (printstream) {
			super.printStackTrace(printstream);
			if (rootCause != null)
				rootCause.printStackTrace(printstream);
			if (isFirst || !(rootCause instanceof LException))
				printstream.println("-----------------------------");
		}
	}

	public void printStackTrace(PrintWriter printwriter) {
		synchronized (printwriter) {
			super.printStackTrace(printwriter);
			if (rootCause != null)
				rootCause.printStackTrace(printwriter);
			if (isFirst || !(rootCause instanceof LException))
				printwriter.println("-----------------------------");
		}
	}
}
