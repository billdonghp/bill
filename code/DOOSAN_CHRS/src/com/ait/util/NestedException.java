package com.ait.util;

public class NestedException extends Exception{


  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Throwable cause;

  /**
   * @param message
   */
  public NestedException(String message) {
          super(message);
  }

  /**
   * @param message
   * @param cause
   */
  public NestedException(String message, Throwable cause) {
          super(message);
          this.cause = cause;
  }

  /**
   * @param cause
   */
  public NestedException(Throwable cause) {
          this.cause = cause;
  }


  /**
   * @see java.lang.Throwable#printStackTrace()
   */
  public void printStackTrace() {
          System.err.println("Nested exception is " + cause.getMessage());
          super.printStackTrace();
  }

}
