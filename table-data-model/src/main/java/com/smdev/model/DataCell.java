package com.smdev.model;

/**
 * Model of a table cell.
 * 
 * @author Ireth
 */
public class DataCell {

	private static final String SEPARATOR = "#";
	private static final String EMPTY = "";
	private DataCellType type = null;
	private Object value;

	public DataCell(DataCellType type, Object value) {
		super();
		this.type = type;
		this.value = value;
	}

	public DataCellType getType() {
		return type;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public String toString() {
		String str = this.value != null ? String.valueOf(getValue()) : EMPTY;
		return str.concat(SEPARATOR);
	}
	
	
}
