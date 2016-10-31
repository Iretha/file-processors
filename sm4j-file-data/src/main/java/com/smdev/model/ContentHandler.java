package com.smdev.model;

import java.util.ArrayList;
import java.util.List;

import com.sm4j.exception.ApplicationException;
import static com.smdev.exc.DataModelMessageKey.*;

import com.smdev.exc.DataModelMessageKey;
import com.smdev.exc.ValidationException;

/**
 * Different operations for handling content data
 * 
 * @author Ireth
 */
public final class ContentHandler {

	/**
	 * Returns new instance of a data object with the given parameters
	 * 
	 * @param headerRows
	 *            - total number of header rows
	 * @param headerCols
	 *            - total number of header cols
	 * @param content
	 *            - content of a file
	 * @return Data
	 */
	private static Data createData(int headerRows, int headerCols, List<DataCell[]> content) {
		return new Data(headerRows, headerCols, content);
	}

	/**
	 * Reorders columns by given ordering. Index starts from 0. All columns must
	 * be present in the array.<br>
	 * Example: <code>int[] colOrder = new int[]{2, 0, 1}</code> will affect the
	 * result in following way: <br>
	 * - column with index 0 will go on second position <br>
	 * - column with index 1 will go on third position <br>
	 * - column with index 2 will go on first position <br>
	 * 
	 * @param data
	 *            - original data
	 * @param colOrder
	 *            - ordering
	 * @return new instance of the data with the required changes
	 * @throws ApplicationException
	 *             - will be trown if given aruments are invalid
	 */
	public static Data reorderCols(Data data, int[] colOrder) throws ApplicationException {
		validate(data == null, arg_null, "data");
		validate(colOrder == null, arg_null, "colOrder");
		validate(colOrder.length != data.getColsCount(), inv_arr_length, data.getColsCount());

		List<DataCell[]> content = data.getContentCopy();
		DataCell[] originalRow = null;
		DataCell[] orderedRow = null;
		for (int rowIdx = 0; rowIdx < content.size(); rowIdx++) {
			originalRow = content.get(rowIdx);
			orderedRow = new DataCell[originalRow.length];
			for (int col = 0; col < originalRow.length; col++) {
				orderedRow[col] = originalRow[colOrder[col]];
			}
			content.set(rowIdx, orderedRow);
		}

		return createData(data.getHeaderRows(), data.getHeaderCols(), content);
	}

	/**
	 * Reorders rows by given ordering. Index starts from 0. All rows must be
	 * present in the array.<br>
	 * Example: <code>int[] rowOrder = new int[]{2, 0, 1}</code> will affect the
	 * result in following way: <br>
	 * - row with index 0 will go on second position <br>
	 * - row with index 1 will go on third position <br>
	 * - row with index 2 will go on first position <br>
	 * 
	 * @param data
	 *            - original data
	 * @param rowOrder
	 *            - ordering
	 * @return new instance of the data with the required changes
	 * @throws ApplicationException
	 *             - will be trown if given aruments are invalid
	 */
	public static Data reorderRows(Data data, int[] rowOrder) throws ApplicationException {
		validate(data == null, arg_null, "data");
		validate(rowOrder == null, arg_null, "rowOrder");
		validate(rowOrder.length != data.getRowsCount(), inv_arr_length, data.getRowsCount());

		List<DataCell[]> content = data.getContentCopy();
		List<DataCell[]> ordered = new ArrayList<>();
		for (int row : rowOrder) {
			ordered.add(content.get(row));
		}

		return createData(data.getHeaderRows(), data.getHeaderCols(), ordered);
	}

	/**
	 * Swaps the position of the given columns. Index starts from 0.
	 * 
	 * @param data
	 *            - original data
	 * @param colIdx1
	 *            - col1 to swap
	 * @param colIdx2
	 *            - col2 to swap
	 * @return new instance of the data with the required changes
	 * @throws ApplicationException
	 *             - will be trown if given aruments are invalid
	 */
	public static Data swapCols(Data data, int colIdx1, int colIdx2) throws ApplicationException {
		validate(data == null, arg_null, "data");
		validate(colIdx1 < 0 || colIdx1 > data.getColsCount(), inv_arg, "colIdx1");
		validate(colIdx2 < 0 || colIdx2 > data.getColsCount(), inv_arg, "colIdx2");

		List<DataCell[]> content = data.getContentCopy();
		DataCell tempCell = null;
		for (DataCell[] row : content) {
			tempCell = row[colIdx1];
			row[colIdx1] = row[colIdx2];
			row[colIdx2] = tempCell;
		}

		return createData(data.getHeaderRows(), data.getHeaderCols(), content);
	}

	/**
	 * Swaps the position of the given rows. Index starts from 0.
	 * 
	 * @param data
	 *            - original data
	 * @param rowIdx1
	 *            - row1 to swap
	 * @param rowIdx2
	 *            - row2 to swap
	 * @return new instance of the data with the required changes
	 * @throws ApplicationException
	 *             - will be trown if given aruments are invalid
	 */
	public static Data swapRows(Data data, int rowIdx1, int rowIdx2) throws ApplicationException {
		validate(data == null, arg_null, "data");
		validate(rowIdx1 < 0 || rowIdx1 > data.getRowsCount(), inv_arg, "rowIdx1");
		validate(rowIdx2 < 0 || rowIdx2 > data.getRowsCount(), inv_arg, "rowIdx2");

		List<DataCell[]> content = data.getContentCopy();
		DataCell[] row1 = content.get(rowIdx1);
		content.set(rowIdx1, content.get(rowIdx2));
		content.set(rowIdx2, row1);

		return createData(data.getHeaderRows(), data.getHeaderCols(), content);
	}

	/**
	 * Transposes the matrix. Rows transform into colums and vice versa.
	 * @param data
	 *            - original data
	 * @return new instance of the data with the required changes
	 * @throws ApplicationException
	 *             - will be trown if given aruments are invalid
	 */
	public static Data transpose(Data data) throws ApplicationException {
		validate(data == null, arg_null, "data");

		int cols = data.getColsCount();
		int rows = data.getRowsCount();
		List<DataCell[]> transposedMatrix = new ArrayList<>();
		DataCell[] transposedRow = null;
		for (int colIdx = 0; colIdx < cols; colIdx++) {
			transposedRow = new DataCell[cols];
			for (int rowIdx = 0; rowIdx < rows; rowIdx++) {
				transposedRow[rowIdx] = data.getCell(rowIdx, colIdx);
			}
			transposedMatrix.add(transposedRow);
		}

		return createData(data.getHeaderCols(), data.getHeaderRows(), transposedMatrix);
	}

	private static void validate(boolean throwExc, DataModelMessageKey key, Object... params)
			throws ApplicationException {
		if (throwExc) {
			throw new ValidationException(key, params);
		}
	}

	private ContentHandler() {
		super();
	}

}
