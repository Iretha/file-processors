package com.smdev.ms.excel;

import com.smdev.model.FileProps;
import com.smdev.util.FileTypes;

public class ExcelProps extends FileProps {

	/**
	 * Index of the first column to be processed. Leave null to start from the
	 * beginning.
	 */
	private Integer firstCol = null;
	/**
	 * Index of the first row to be processed. Leave null to start from the
	 * beginning.
	 */
	private Integer firstRow = null;
	/**
	 * Number of header columns
	 */
	private int headerCols = 0;
	/**
	 * Number of header rows
	 */
	private int headerRows = 0;
	/**
	 * Index of the last column to be processed. Leave null to process columns
	 * to the end.
	 */
	private Integer lastCol = null;
	/**
	 * Index of the last row to be processed. Leave null to process row to the
	 * end.
	 */
	private Integer lastRow = null;

	public ExcelProps() {
		super(FileTypes.XLSX);
	}

	public int getFirstCol(short defaultValue) {
		if (firstCol == null || firstCol < defaultValue) {
			return defaultValue;
		}
		return firstCol;
	}

	public int getFirstRow(int defaultValue) {
		if (firstRow == null || firstRow < defaultValue) {
			return defaultValue;
		}
		return this.firstRow == null ? defaultValue : firstRow;
	}

	public int getHeaderCols() {
		return headerCols;
	}

	public int getHeaderRows() {
		return headerRows;
	}

	/**
	 * Starts from 0, returns <b>PLUS 1 </b>
	 * 
	 * @param defaultValue
	 * @return
	 */
	public int getLastCol(short defaultValue) {
		if (lastCol == null || lastCol > defaultValue) {
			return defaultValue;
		}
		return lastCol == null ? defaultValue : lastCol;
	}

	public int getLastRow(int defaultValue) {
		if (lastRow == null || lastRow > defaultValue) {
			return defaultValue;
		}
		return lastRow == null ? defaultValue : this.lastRow;
	}


	public boolean isXlsxFormat() {
		return getFileType().equals(FileTypes.XLSX);
	}

	public void setFirstCol(Integer firstCol) {
		this.firstCol = firstCol;
	}

	/**
	 * Index starts from 0.
	 * 
	 * @param firstRow
	 */
	public void setFirstRow(Integer firstRow) {
		this.firstRow = firstRow;
	}

	public void setHeaderCols(int headerCols) {
		this.headerCols = headerCols;
	}

	public void setHeaderRows(int headerRows) {
		this.headerRows = headerRows;
	}

	public void setLastCol(Integer lastCol) {
		this.lastCol = lastCol;
	}

	/**
	 * Index starts from 0.
	 * 
	 * @param lastRow
	 */
	public void setLastRow(Integer lastRow) {
		this.lastRow = lastRow;
	}
	
	public void setXlsxFormat(boolean xlsxFormat) {
		setFileType(xlsxFormat ? FileTypes.XLSX : FileTypes.XLS);
	}

}
