package com.smdev.exc;

/**
 * Generic exception thrown by the application
 * @author Ireth
 */
public abstract class ApplicationException extends Exception {

	/** UID */
	private static final long serialVersionUID = 2454942997151667868L;

	/**
	 * @param message
	 */
	protected ApplicationException(String message) {
		super(message);
	}

	/**
	 * @param throwable
	 */
	protected ApplicationException(Throwable throwable) {
		super(throwable);
	}

}
