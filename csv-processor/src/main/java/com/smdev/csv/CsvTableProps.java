package com.smdev.csv;

import com.smdev.model.TableProps;
import com.smdev.util.FileTypes;

/**
 * Specific CSV table properties, extending the {@link TableProps}
 * 
 * <table>
 * <tr>
 * <th>Property name</th>
 * <th>Default value</th>
 * <th>Possible values</th>
 * <th>Description</th>
 * </tr>
 * <tr>
 * <td>escape</td>
 * <td>no char</td>
 * <td>custom i.e. '\'', '\"', etc.</td>
 * <td>character used for escaping special symbols</td>
 * </tr>
 * <tr>
 * <td>exportHeaders</td>
 * <td>true</td>
 * <td>true | false</td>
 * <td>exportHeaders == false => headers won't be exported</td>
 * </tr>
 * <tr>
 * <td>quote</td>
 * <td>no char</td>
 * <td>custom char i.e. '\'', '\"', etc.</td>
 * <td>character used for quoting values</td>
 * </tr>
 * <tr>
 * <td>separator</td>
 * <td>,</td>
 * <td>custom char i.e. ',', ';', '\t', '|', etc.</td>
 * <td>character used for separating values from a single line</td>
 * </tr>
 * <tr>
 * <td>skipFirstRows</td>
 * <td>0</td>
 * <td>custom number</td>
 * <td>skipFirstRows != 0 => first X rows will be skipped while reading the
 * file</td>
 * </tr>
 * </table>
 * 
 * @author Ireth
 */
public class CsvTableProps extends TableProps {

	/**
	 * Character used for escaping values: '\'', '\"', etc. Default behavior is
	 * not to escape values.
	 */
	private char escape = Character.MIN_VALUE;
	/**
	 * Character used as separator between cell values: , ; \t. Default value is
	 * <code>true<code>.
	 */
	private boolean exportHeaders = true;
	/**
	 * Character used for quoting values: '\'', '\"', etc Default behavior is
	 * not to quote values.
	 */
	private char quote = Character.MIN_VALUE;
	/** Character used as separator between cell values: , ; \t etc... */
	private char separator = ',';
	/**
	 * Skipping first rows, depending on the given number. Default value is
	 * <code>0<code>.
	 */
	private int skipFirstRows = 0;

	/** Construct */
	protected CsvTableProps() {
		super(FileTypes.CSV);
	}

	public char getEscape() {
		return escape;
	}

	public boolean getExportHeaders() {
		return exportHeaders;
	}

	public char getQuote() {
		return quote;
	}

	public char getSeparator() {
		return separator;
	}

	public int getSkipFirstRows() {
		return skipFirstRows;
	}

	public void setEscape(char escape) {
		this.escape = escape;
	}

	public void setExportHeaders(boolean exportHeaders) {
		this.exportHeaders = exportHeaders;
	}

	public void setQuote(char quote) {
		this.quote = quote;
	}

	public void setSeparator(char separator) {
		this.separator = separator;
	}

	public void setSkipFirstRows(int skipFirstRows) {
		this.skipFirstRows = skipFirstRows;
	}

}
