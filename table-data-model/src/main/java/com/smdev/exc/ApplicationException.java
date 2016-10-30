package com.smdev.exc;

public abstract class ApplicationException extends Exception {

	/** */
	private static final long serialVersionUID = 2454942997151667868L;

	protected ApplicationException(String message) {
		super(message);
	}

	protected ApplicationException(Throwable throwable) {
		super(throwable);
	}

}
