package com.smdev.model;

/**
 * Model of a table cell.
 * 
 * @author Ireth
 */
public class TCell {

	private static final String SEPARATOR = "#";
	private static final String EMPTY = "";
	private TCellType type = null;
	private Object value;

	public TCell(TCellType type, Object value) {
		super();
		this.type = type;
		this.value = value;
	}

	public TCellType getType() {
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
