package com.smdev.ms.excel;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.sm4j.exception.ApplicationException;
import com.smdev.model.Data;
import com.smdev.model.DataCell;
import com.smdev.model.DataCellType;

public class ExcelSheetWriter {

	private Sheet sheet;
	private Data table;

	public ExcelSheetWriter(Data data, Sheet sheet) {
		super();
		this.table = data;
		this.sheet = sheet;
	}

	private void copyData(DataCell data, Cell cell) {
		if (data == null || data.getValue() == null) {
			return;
		}

		Object value = data.getValue();
		DataCellType type = data.getType();
		switch (type) {
		case BIGDECIMAL:
			cell.setCellValue(BigDecimal.class.cast(value).doubleValue());
			break;
		case BIGINTEGER:
			cell.setCellValue(BigInteger.class.cast(value).intValue());
			break;
		case BOOLEAN:
			cell.setCellValue(Boolean.class.cast(value).booleanValue());
			break;
		case DATE:
			cell.setCellValue(Date.class.cast(value).getTime());
			break;
		default:
			cell.setCellValue(String.class.cast(value));
			break;
		}
	}

	public void writeData() throws ApplicationException {
		int rows = this.table.getRowsCount();
		int cols = this.table.getColsCount();

		Row row = null;
		Cell cell = null;
		for (int rowIdx = 0; rowIdx < rows; rowIdx++) {
			row = this.sheet.createRow(rowIdx);
			for (int colIdx = 0; colIdx < cols; colIdx++) {
				cell = row.createCell(colIdx);
				copyData(this.table.getCell(rowIdx, colIdx), cell);
			}
		}
	}

}
