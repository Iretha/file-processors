package com.smdev.model;

/**
 * Model of a data cell with value and type.
 * 
 * @author Ireth
 */
public final class DataCell {

	private static final String EMPTY = "";
	private static final String SEPARATOR = "#";
	private DataCellType type = null;
	private Object value;

	/**
	 * @param type
	 *            - data type
	 * @param value
	 *            - data to be stored in the cell
	 */
	public DataCell(DataCellType type, Object value) {
		super();
		this.type = type;
		this.value = value;
	}

	/**
	 * @return type of the cell data
	 */
	public DataCellType getType() {
		return type;
	}

	/**
	 * @return data, stored in the cell
	 */
	public Object getValue() {
		return value;
	}

	@Override
	public String toString() {
		String str = this.value != null ? String.valueOf(getValue()) : EMPTY;
		return str.concat(SEPARATOR);
	}

}
