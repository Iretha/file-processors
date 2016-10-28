package com.smdev.model;

import java.util.ArrayList;
import java.util.List;

import com.smdev.exc.ModelException;

/**
 * Model of a data, representing the content of a file. This model can be used
 * in order to export the data in various file formats: csv, txt, pdf, xlsx, ect.
 * 
 * @author Ireth
 */
public class Table {

	private static final String NEW_LINE = "\n";

	private final List<Cell[]> content = new ArrayList<>();

	private int horizontalheaderRows = 0;

	public Table(int horizontalheaderRows) {
		super();
		this.horizontalheaderRows = horizontalheaderRows;
	}

	public void addRow(Object... values) throws ModelException {
		int rowNum = getRowsCount();
		if (rowNum != 0 && getColsCount() != values.length) {
			throw new ModelException("Invalid Row!");
		}

		int colNum = 0;
		Cell[] row = new Cell[values.length];
		for (Object value : values) {
			row[colNum] = new Cell(rowNum, colNum, value);
			colNum++;
		}
		getContent().add(row);
	}

	public Cell getCell(int row, int col) throws ModelException {
		if (row < 0 || row >= getRowsCount()) {
			throw new ModelException("Invalid row idx!");
		}

		if (col < 0 || col >= getColsCount()) {
			throw new ModelException("Invalid col idx!");
		}
		return getContent().get(row)[col];
	}

	public int getColsCount() {
		return getContent().isEmpty() ? 0 : getContent().get(0).length;
	}

	public List<Cell[]> getContent() {
		return this.content;
	}

	public int getHorizontalHeaderRows() {
		return this.horizontalheaderRows;
	}

	public Cell[] getRow(int row) {
		// TODO validation
		return getContent().get(row);
	}

	public int getRowsCount() {
		return getContent().size();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		for (Cell[] row : content) {
			for (Cell c : row) {
				str.append(c.toString());
			}
			str.append(NEW_LINE);
		}
		return str.toString();
	}
}
