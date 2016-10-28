package com.smdev.model;

/**
 * Model of a table cell.
 * 
 * @author Ireth
 */
public class Cell {

	private int col;
	private int colSpan = 1;
	private int row;
	private int rowSpan = 1;
	private Object value;

	public Cell(int row, int col, Object value) {
		super();
		this.row = row;
		this.col = col;
		this.value = value;
	}

	public int getCol() {
		return col;
	}

	public int getColSpan() {
		return colSpan;
	}

	public int getRow() {
		return row;
	}

	public int getRowSpan() {
		return rowSpan;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("(").append(this.row).append(",").append(this.col).append(")");
		if (this.value != null) {
			str.append("=>").append(getValue());
		}
		return str.append("; ").toString();
	}
}
