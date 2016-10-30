package com.smdev.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.smdev.exc.ApplicationException;
import com.smdev.exc.ReadFileException;
import com.smdev.model.TCellType;
import com.smdev.model.Table;
import com.smdev.processor.Importable;

/**
 * Implementation of a CSV file reader.
 * 
 * Example usage:
 * 
 * @author Ireth
 */
public class CsvFileReader implements Importable<CsvProps> {

	@Override
	public Table read(CsvProps props, File file) throws ApplicationException {
		char sep = props.getSeparator();
		char escape = props.getEscape();
		int skipRows = props.getSkipFirstRows();
		Table table = new Table(1, 0);
		try (CSVReader reader = new CSVReader(new FileReader(file), sep, escape, skipRows)) {
			List<String[]> rows = reader.readAll();
			for (Object[] row : rows) {
				table.addRow(TCellType.STRING, row);
			}
		} catch (IOException e) {
			throw new ReadFileException(e);
		}
		return table;
	}

}
