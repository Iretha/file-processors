package com.smdev.ms.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import com.smdev.exc.ApplicationException;
import com.smdev.model.Data;
import com.smdev.model.DataCell;
import com.smdev.model.DataCellType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class ExcelSheetWriter {

	private Data table;
	private Sheet sheet;

	public ExcelSheetWriter(Data data, Sheet sheet) {
		super();
		this.table = data;
		this.sheet = sheet;
	}

	public void writeData() throws ApplicationException {
		int rows = table.getRowsCount();
		int cols = table.getColsCount();

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

}
