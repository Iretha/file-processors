package com.smdev.csv;

import com.smdev.model.TableProps;
import com.smdev.util.FileTypes;

public class CsvTableProps extends TableProps {

	/** Character used as separator between cell values: , ; \t etc... */
	private char separator = ',';
	/**
	 * Character used as separator between cell values: , ; \t. Default value is
	 * <code>true<code>.
	 */
	private boolean exportHeaders = true;
	/**
	 * Skipping first rows, depending on the given number. Default value is
	 * <code>0<code>.
	 */
	private int skipFirstRows = 0;
	/**
	 * Character used for escaping values: '\'', '\"', etc. Default behavior is
	 * not to escape values.
	 */
	private char escape = Character.MIN_VALUE;
	/**
	 * Character used for quoting values: '\'', '\"', etc Default behavior is
	 * not to quote values.
	 */
	private char quote = Character.MIN_VALUE;

	/** Construct */
	protected CsvTableProps() {
		super(FileTypes.CSV);
	}

	public char getSeparator() {
		return separator;
	}

	public void setSeparator(char separator) {
		this.separator = separator;
	}

	public boolean getExportHeaders() {
		return exportHeaders;
	}

	public void setExportHeaders(boolean exportHeaders) {
		this.exportHeaders = exportHeaders;
	}

	public int getSkipFirstRows() {
		return skipFirstRows;
	}

	public void setSkipFirstRows(int skipFirstRows) {
		this.skipFirstRows = skipFirstRows;
	}

	public char getEscape() {
		return escape;
	}

	public void setEscape(char escape) {
		this.escape = escape;
	}

	public char getQuote() {
		return quote;
	}

	public void setQuote(char quote) {
		this.quote = quote;
	}

}
