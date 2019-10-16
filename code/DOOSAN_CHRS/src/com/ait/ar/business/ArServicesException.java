package com.ait.ar.business;

import com.ait.util.NestedException;

public class ArServicesException extends NestedException {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public ArServicesException(String message) {
    super(message);
  }
  /**
   * @param message
   * @param cause
   */
  public ArServicesException(String message, Throwable cause) {
          super(message, cause);
  }

  /**
   * @param cause
   */
  public ArServicesException(Throwable cause) {
          super(cause);
  }

}
