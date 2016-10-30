package com.smdev.exc;

/**
 * Exception thrown while writing a new target file
 * 
 * @author Ireth
 */
public class WriteFileExaception extends ApplicationException {

	/** */
	private static final long serialVersionUID = 7767882366459588593L;

	/**
	 * @param throwable
	 */
	public WriteFileExaception(Throwable throwable) {
		super(throwable);
	}

}
