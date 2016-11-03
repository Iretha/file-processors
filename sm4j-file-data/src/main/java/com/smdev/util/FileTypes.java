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
	TXT("txt"),
	/** XLS file type */
	XLS("xls"),
	/** XLSX file type */
	XLSX("xlsx"),
	/** XML file type */
	XML("xml");

	private String extension;

	private FileTypes(String extension) {
		this.extension = extension;
	}

	public String getExtension() {
		return this.extension;
	}

}
