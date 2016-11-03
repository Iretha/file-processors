package com.smdev.exc;

import com.sm4j.exception.ApplicationException;
import com.sm4j.exception.IMessageKey;

/**
 * Application thrown by handling the content of a file
 *
 * @author Ireth
 */
public class ValidationException extends ApplicationException {

	/** */
	private static final long serialVersionUID = -1753232504936086969L;

	public ValidationException(IMessageKey key, Object... params) {
		super(key, params);
	}

}
