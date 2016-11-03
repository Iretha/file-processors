package com.smdev.model;

import static com.smdev.exc.DataModelMessageKey.inv_arg;
import static com.smdev.exc.DataModelMessageKey.inv_row_values;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.smdev.exc.ModelException;

/**
 * Model, representing the content of a file. This model can be used in order to
 * export the data in various file formats: csv, txt, pdf, xlsx, ect. The
 * content of the imported files is also transformed into Data object. This
 * allows users to import data from specific file type and export its content
 * into another file type.
 *
 * @author Ireth
 */
public final class Data {

	/** Delimiter between the lines in the implementation of toString() */
	private static final String NEW_LINE = "\n";

	/**
	 * The content of the file is represented as {@code List<DataCell[]>}. <br>
	 * Each element of the list represents a row from the file. <br>
	 * Each row consists of one or more {@link DataCell}.
	 */
	private final List<DataCell[]> content = new ArrayList<>();

	/** Number of header colums. Default value is 0. */
	private int headerCols = 0;

	/** Number of header rows. Default value is 0. */
	private int headerRows = 0;

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

	/**
	 * @param headerRows
	 *            - Number of header rows
	 * @param headerCols
	 *            - Number of header colums
	 * @param content
	 *            - content of a file
	 */
	public Data(int headerRows, int headerCols, List<DataCell[]> content) {
		super();
		this.headerRows = headerRows;
		this.headerCols = headerCols;
		getContent().addAll(content);
	}

	/**
	 * Adds a new row to the end of the content
	 *
	 * @param cells
	 */
	public void addRow(DataCell[] cells) {
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

		if (getRowsCount() != 0 && getColsCount() != values.length) {
			throw new ModelException(inv_row_values, getColsCount());
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
			throw new ModelException(inv_arg, "rowIdx");
		}

		if (colIdx < 0 || colIdx >= getColsCount()) {
			throw new ModelException(inv_arg, "colIdx");
		}
		return getContent().get(rowIdx)[colIdx];
	}

	/**
	 * @return number of columns
	 */
	public int getColsCount() {
		return getContent().isEmpty() ? 0 : getContentCopy().get(0).length;
	}

	/**
	 * @return returns a copy of the content
	 */
	private List<DataCell[]> getContent() {
		return this.content;
	}

	/**
	 * @return returns a copy of the content
	 */
	public List<DataCell[]> getContentCopy() {
		List<DataCell[]> copy = new ArrayList<>();
		for (DataCell[] r : getContent()) {
			copy.add(Arrays.copyOf(r, r.length));
		}
		return copy;
	}

	public int getHeaderCols() {
		return this.headerCols;
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
		getContent().forEach(row -> {
			Arrays.stream(row).forEach(col -> str.append(col.toString()));
			str.append(NEW_LINE);
		});
		return str.toString();
	}

}
