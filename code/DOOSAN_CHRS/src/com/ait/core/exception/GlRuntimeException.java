package com.ait.core.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 运行期异常类，所有的cmd、biz、dao都必须将catch的异常转化为此类，然后throw
 * @author lzj
 *
 */
public class GlRuntimeException extends RuntimeException{
	
	private static final long serialVersionUID = 264300063347740193L;
	
	public GlRuntimeException(String message){
		super(message);
	}
	
	public GlRuntimeException(String message, Throwable rootCause) {
		super(message,rootCause);
	}
	
	public String getStackTraceString(){
		StringWriter sw = new StringWriter();
		printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
	
	public void printStackTrace(PrintStream ps){		
		synchronized(ps){
			super.printStackTrace(ps);
			ps.println("-----------------------------");
		}
		
	}
	
	public void printStackTrace(PrintWriter pw){
		synchronized(pw){
			super.printStackTrace(pw);
			pw.println("-----------------------------");
		}
	}
	

}
