package com.smdev.util;

/**
 * Supported file types
 * 
 * @author Ireth
 */
public enum FileTypes {

	/** CSV file type */
	CSV("csv"),
	/** TXT file type */
	TXT("txt");

	private String extension;

	private FileTypes(String extension) {
		this.extension = extension;
	}

	public String getExtension() {
		return extension;
	}

}
