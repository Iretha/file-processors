package com.smdev.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.smdev.exc.ModelException;

/**
 * Model of data, representing the content of a file. This model can be used in
 * order to export the data in various file formats: csv, txt, pdf, xlsx, ect.
 * The content of the imported files is also transformed into Data object. This
 * allows users to import data from specific file type and export it's content
 * into another file type.
 * 
 * @author Ireth
 */
public class Data {

	/** Delimiter between the lines in the implementation of toString() */
	private static final String NEW_LINE = "\n";

	/**
	 * The content of the file is represented as {@code List<DataCell[]>}. <br>
	 * Each element of the list represents a row from the file. <br>
	 * Each row consists of one or more {@link DataCell}.
	 */
	private final List<DataCell[]> content = new ArrayList<>();

	/** Number of header rows. Default value is 0. */
	private int headerRows = 0;

	/** Number of header colums. Default value is 0. */
	private int headerCols = 0;

	/**
	 * @param headerRows
	 *            - Number of header rows
	 * @param headerCols
	 *            - Number of header colums
	 */
	public Data(int headerRows, int headerCols) {
		super();
		this.headerRows = headerRows;
		this.headerCols = headerCols;
	}

	public int getHeaderCols() {
		return headerCols;
	}

	/**
	 * Adds a new row to the end of the content
	 * 
	 * @param cells
	 * @throws ModelException
	 */
	public void addRow(DataCell[] cells) throws ModelException {
		if (cells != null) {
			getContent().add(cells);
		}
	}

	/**
	 * Adds a new row to the end of the content
	 * 
	 * @param type
	 * @param values
	 * @throws ModelException
	 */
	public void addRow(DataCellType type, Object... values) throws ModelException {
		if (values == null) {
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

	/**
	 * Returns the data cell, placed on the given position
	 * 
	 * @param rowIdx
	 *            - row index
	 * @param colIdx
	 *            - col index
	 * @return - data cell
	 * @throws ModelException
	 *             - thrown if the given row or col index is invalid
	 */
	public DataCell getCell(int rowIdx, int colIdx) throws ModelException {
		if (rowIdx < 0 || rowIdx >= getRowsCount()) {
			throw new ModelException("Invalid row idx!");
		}

		if (colIdx < 0 || colIdx >= getColsCount()) {
			throw new ModelException("Invalid col idx!");
		}
		return getContent().get(rowIdx)[colIdx];
	}

	/**
	 * @return number of columns
	 */
	public int getColsCount() {
		return getContent().isEmpty() ? 0 : getContent().get(0).length;
	}

	/**
	 * @return content of a file
	 */
	public List<DataCell[]> getContent() {
		return this.content;
	}

	/**
	 * @return number of header rows
	 */
	public int getHeaderRows() {
		return this.headerRows;
	}

	/**
	 * Retrieves the row by its index. If the index is invalid, returns
	 * <code>null</code>.
	 * 
	 * @param rowIdx
	 *            - row number
	 * @return row - the row at the given index
	 */
	public DataCell[] getRow(int rowIdx) {
		if (rowIdx > -1 && rowIdx < getContent().size()) {
			return getContent().get(rowIdx);
		}
		return null;
	}

	/**
	 * @return number of rows
	 */
	public int getRowsCount() {
		return getContent().size();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		this.content.forEach(row -> {
			Arrays.stream(row).forEach(col -> str.append(col.toString()));
			str.append(NEW_LINE);
		});
		return str.toString();
	}

	/**
	 * @param rowOrder
	 * @throws ModelException
	 */
	public void reorderRows(int[] rowOrder) throws ModelException {
		if (rowOrder == null || rowOrder.length != getRowsCount()) {
			throw new ModelException("Invalid row number");
		}

		List<DataCell[]> ordered = new ArrayList<>();
		for (int row : rowOrder) {
			ordered.add(this.content.get(row));
		}
		this.content.clear();
		this.content.addAll(ordered);
	}

	public void reorderCols(int[] colOrder) throws ModelException {
		if (colOrder == null || colOrder.length != getColsCount()) {
			throw new ModelException("Invalid col number");
		}
		DataCell[] original = null;
		DataCell[] ordered = null;
		for (int rowIdx = 0; rowIdx < this.content.size(); rowIdx++) {
			original = this.content.get(rowIdx);
			ordered = new DataCell[original.length];
			for (int col = 0; col < original.length; col++) {
				ordered[col] = original[colOrder[col]];
			}
			this.content.set(rowIdx, ordered);
		}
	}

	/**
	 * @param rowOrder
	 * @throws ModelException
	 */
	public void swapRows(int rowIdx1, int rowIdx2) throws ModelException {
		//TODO validation
		DataCell[] row1 = this.content.get(rowIdx1);
		this.content.set(rowIdx1, this.content.get(rowIdx2));
		this.content.set(rowIdx2, row1);
	}

	public void swapCols(int colIdx1, int colIdx2) throws ModelException {
		//TODO validation
		DataCell tempCell = null;
		for (DataCell[] row : this.content) {
			tempCell = row[colIdx1];
			row[colIdx1] = row[colIdx2];
			row[colIdx2] = tempCell;
		}
	}
	
	public void transpose(){
		DataCell[] row = null;
		DataCell[] transposedRow = null;
		for(int rowIdx = 0; rowIdx < this.content.size(); rowIdx++){
			row = this.content.get(rowIdx);
			transposedRow = new DataCell[this.content.size()];
			for(int colIdx = 0; colIdx < row.length; colIdx++){
				
			}
		}
	}
}
