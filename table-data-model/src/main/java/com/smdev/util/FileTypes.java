package com.smdev.util;

public enum FileTypes {
	
	CSV("csv");
	
	private String extension;

	private FileTypes(String extension) {
		this.extension = extension;
	}

	public String getExtension() {
		return extension;
	}
	

}
