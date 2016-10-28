package com.smdev.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.smdev.exc.ModelException;
import com.smdev.exc.ReadFileException;
import com.smdev.model.Table;
import com.smdev.processor.Importable;

public class CsvFileReader implements Importable<CsvTableProps> {

	@Override
	public Table read(CsvTableProps props, File file) throws ReadFileException, ModelException {	
		char separator = props.getSeparator();
		Table table = new Table(1);
		try (CSVReader reader = new CSVReader(new FileReader(file), separator)) {
			List<String[]> rows = reader.readAll();
			for(Object[] row : rows){
				table.addRow(row);
			}
		} catch (IOException e) {
			throw new ReadFileException(e);
		}
		return table;
	}

}
