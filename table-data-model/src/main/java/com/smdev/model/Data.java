package com.smdev.model;

import java.util.ArrayList;
import java.util.List;

import com.smdev.exc.ModelException;

/**
 * Model of a data, representing the content of a file. This model can be used
 * in order to export the data in various file formats: csv, txt, pdf, xlsx,
 * ect.
 * 
 * @author Ireth
 */
public class Data {

	private static final String NEW_LINE = "\n";

	private final List<DataCell[]> content = new ArrayList<>();

	private int headerRows = 0;
	private int headerCols = 0;

	public Data(int headerRows, int headerCols) {
		super();
		this.headerRows = headerRows;
		this.headerCols = headerCols;
	}

	public int getHeaderCols() {
		return headerCols;
	}

	public void addRow(DataCell[] cells) throws ModelException {
		if(cells != null){
			getContent().add(cells);	
		}
	}

	public void addRow(DataCellType type, Object... values) throws ModelException {
		if(values == null){
			return;
		}
		
		int rowNum = getRowsCount();
		if (rowNum != 0 && getColsCount() != values.length) {
			throw new ModelException("Invalid Row!");
		}

		int colNum = 0;
		DataCell[] row = new DataCell[values.length];
		for (Object value : values) {
			row[colNum] = new DataCell(type, value);
			colNum++;
		}
		getContent().add(row);
	}

	public DataCell getCell(int row, int col) throws ModelException {
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

	public List<DataCell[]> getContent() {
		return this.content;
	}

	public int getHeaderRows() {
		return this.headerRows;
	}

	public DataCell[] getRow(int row) {
		if(row > -1 && row < getContent().size()){
			return getContent().get(row);
		}
		return null;
	}

	public int getRowsCount() {
		return getContent().size();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (DataCell[] row : content) {
			for (DataCell c : row) {
				str.append(c.toString());
			}
			str.append(NEW_LINE);
		}
		return str.toString();
	}
}
