package com.smdev.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.smdev.exc.ApplicationException;
import com.smdev.exc.ReadFileException;
import com.smdev.model.DataCellType;
import com.smdev.model.Data;
import com.smdev.processor.SMFileReader;

/**
 * Implementation of a CSV file reader.
 * 
 * Example usage:
 * 
 * @author Ireth
 */
public class CsvFileReader implements SMFileReader<CsvProps> {

	@Override
	public Data read(CsvProps props, File file) throws ApplicationException {
		char sep = props.getSeparator();
		char escape = props.getEscape();
		int skipRows = props.getSkipFirstRows();
		Data table = new Data(1, 0);
		try (CSVReader reader = new CSVReader(new FileReader(file), sep, escape, skipRows)) {
			List<String[]> rows = reader.readAll();
			for (Object[] row : rows) {
				table.addRow(DataCellType.STRING, row);
			}
		} catch (IOException e) {
			throw new ReadFileException(e);
		}
		return table;
	}

}
