package com.smdev.model;

public class Cell {

	private Object value;
	private int row;
	private int col;
	private int rowSpan = 1;
	private int colSpan = 1;

	public Cell(int row, int col, Object value) {
		super();
		this.row = row;
		this.col = col;
		this.value = value;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	public Object getValue() {
		return value;
	}

	public int getRowSpan() {
		return rowSpan;
	}

	public int getColSpan() {
		return colSpan;
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
