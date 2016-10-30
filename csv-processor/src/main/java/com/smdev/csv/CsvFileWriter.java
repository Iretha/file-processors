package com.smdev.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;
import com.smdev.exc.ApplicationException;
import com.smdev.exc.WriteFileExaception;
import com.smdev.file.SMFileWriter;
import com.smdev.model.DataCell;
import com.smdev.model.Data;

/**
 * Implementation of a CSV file writer.
 * @author Ireth
 */
public class CsvFileWriter implements SMFileWriter<CsvProps> {

	@Override
	public File write(CsvProps props, Data table) throws ApplicationException {
		String filePath = props.getFileName();
		char separator = props.getSeparator();
		char escape = props.getEscape();
		char quote = props.getQuote();
		boolean exportHeaders = props.getExportHeaders();

		try (CSVWriter writer = new CSVWriter(new FileWriter(filePath), separator, quote, escape);) {
			int headerRows = table.getHeaderRows();
			int rows = table.getRowsCount();
			int cols = table.getColsCount();

			DataCell[] originalRow = null;
			String[] stringRow = null;
			List<String[]> content = new ArrayList<>();
			for (int row = 0; row < rows; row++) {
				if (!exportHeaders && row < headerRows) {
					continue; // skipping header rows
				}
				originalRow = table.getRow(row);
				stringRow = new String[cols];
				for (int col = 0; col < cols; col++) {
					stringRow[col] = String.valueOf(originalRow[col].getValue());
				}
				content.add(stringRow);
			}
			writer.writeAll(content);
		} catch (IOException e) {
			throw new WriteFileExaception(e);
		}
		return new File(filePath);
	}

	public File write(CsvProps props, ResultSet rs) throws ApplicationException {
		String filePath = props.getFileName();
		char separator = props.getSeparator();
		char escape = props.getEscape();
		char quote = props.getQuote();
		boolean exportHeaders = props.getExportHeaders();

		try (CSVWriter writer = new CSVWriter(new FileWriter(filePath), separator, quote, escape)) {
			writer.writeAll(rs, exportHeaders);
		} catch (IOException | SQLException e) {
			throw new WriteFileExaception(e);
		}
		return new File(filePath);
	}

}
