package com.smdev.exc;

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
	public ModelException(String message) {
		super(message);
	}

}
