package com.smdev.model;

import com.smdev.util.FileTypes;

public abstract class TableProps {

	private String name = "simpleFile";
	private FileTypes fileType = FileTypes.CSV;

	protected TableProps(FileTypes fileType) {
		super();
		this.fileType = fileType;
	}

	public String getName() {
		return name;
	}
	
	public String getFileName() {
		return getName() + "." + getFileType().getExtension();
	}

	public FileTypes getFileType() {
		return fileType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFileType(FileTypes fileType) {
		this.fileType = fileType;
	}

}
