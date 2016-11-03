package com.smdev.exc;

import com.sm4j.exception.ApplicationException;
import com.sm4j.exception.IMessageKey;

/**
 * Exception thrown while creating new data model
 *
 * @author Ireth
 */
public class ModelException extends ApplicationException {

	/** */
	private static final long serialVersionUID = -4782860044719115331L;

	/**
	 * @param message
	 */
	public ModelException(IMessageKey key, Object... params) {
		super(key, params);
	}

}
