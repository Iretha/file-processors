package com.smdev.model;

import com.smdev.util.FileTypes;

/**
 * Model of a table properties. They are used while reading or writing files.
 * <br>
 * <table>
 * <tr>
 * <th>Property name</th>
 * <th>Default value</th>
 * <th>Possible values</th>
 * </tr>
 * <tr>
 * <td>name</td>
 * <td>simpleFile</td>
 * <td>custom</td>
 * </tr>
 * <tr>
 * <td>fileType</td>
 * <td>FileTypes.CSV</td>
 * <td>FileTypes.*</td>
 * </tr>
 * </table>
 * 
 * @author Ireth
 */
public abstract class FileProps {

	private FileTypes fileType = null;
	private String name = "simpleFile";

	protected FileProps(FileTypes fileType) {
		super();
		this.fileType = fileType;
	}

	public String getFileName() {
		return getName() + "." + getFileType().getExtension();
	}

	public FileTypes getFileType() {
		return fileType;
	}

	public String getName() {
		return name;
	}

	protected void setFileType(FileTypes fileType) {
		this.fileType = fileType;
	}

	public void setName(String name) {
		this.name = name;
	}

}
