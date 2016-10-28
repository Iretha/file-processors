package com.smdev.csv;

import com.smdev.model.TablePropKey;
import com.smdev.model.TableProps;
import com.smdev.util.FileTypes;

public class CsvTableProps extends TableProps {

	private enum CsvPropsKeys implements TablePropKey{
		/** */
		SEPARATOR,
		/** */
		EXPORT_HEADERS,
		;
	}

	protected CsvTableProps() {
		super(FileTypes.CSV);
	}

	public void addExportHeaders(boolean write) {
		put(CsvPropsKeys.EXPORT_HEADERS, write);
	}
	
	public void addSeparator(String type) {
		put(CsvPropsKeys.SEPARATOR, type);
	}
	
	public boolean getExportHeaders(){
		return getBoolean(CsvPropsKeys.EXPORT_HEADERS, "true");
	}
	
	public char getSeparator(){
		return get(CsvPropsKeys.SEPARATOR, ",").charAt(0);
	}
}
