package com.smdev.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;
import com.smdev.exc.WriteFileExaception;
import com.smdev.model.Cell;
import com.smdev.model.Table;
import com.smdev.processor.Exportable;

public class CsvFileWriter implements Exportable<CsvTableProps> {

	@Override
	public File write(CsvTableProps tableProps, Table table) throws WriteFileExaception {
		String filePath = tableProps.getNameWithExtension();
		char separator = tableProps.getSeparator();
		boolean exportHeaders = tableProps.getExportHeaders();

		try (CSVWriter writer = new CSVWriter(new FileWriter(filePath), separator);) {
			int headerRows = table.getHorizontalHeaderRows();
			int rows = table.getRowsCount();
			int cols = table.getColsCount();

			Cell[] originalRow = null;
			String[] stringRow = null;
			List<String[]> data = new ArrayList<>();
			for (int row = 0; row < rows; row++) {
				if (!exportHeaders && row < headerRows) {
					continue; // skipping header rows
				}
				originalRow = table.getRow(row);
				stringRow = new String[cols];
				for (int col = 0; col < cols; col++) {
					stringRow[col] = String.valueOf(originalRow[col].getValue());
				}
				data.add(stringRow);
			}
			writer.writeAll(data);
		} catch (IOException e) {
			throw new WriteFileExaception(e);
		}
		return new File(filePath);
	}

	public File write(CsvTableProps tableProps, ResultSet rs) throws WriteFileExaception {
		String filePath = tableProps.getNameWithExtension();
		char separator = tableProps.getSeparator();
		boolean exportHeaders = tableProps.getExportHeaders();

		try (CSVWriter writer = new CSVWriter(new FileWriter(filePath), separator)) {
			writer.writeAll(rs, exportHeaders);
		} catch (IOException | SQLException e) {
			throw new WriteFileExaception(e);
		}
		return new File(filePath);
	}

}