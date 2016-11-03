package com.smdev.ms.excel;

import java.math.BigDecimal;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.smdev.exc.ModelException;
import com.smdev.model.Data;
import com.smdev.model.DataCell;
import com.smdev.model.DataCellType;

public class ExcelSheetParser {

	private static final String EMPTY_STR = "";
	private ExcelProps props = null;
	private Sheet sheet = null;
	private Data table = null;

	public ExcelSheetParser(ExcelProps props, Sheet sheet) {
		super();
		this.props = props;
		this.sheet = sheet;
	}

	public Data parse() throws ModelException {
		this.table = new Data(this.props.getHeaderRows(), this.props.getHeaderCols());
		int firstRow = this.props.getFirstRow(this.sheet.getFirstRowNum());
		int lastRow = this.props.getLastRow(this.sheet.getLastRowNum());
		DataCell[] tableRow = null;
		for (int rowIdx = firstRow; rowIdx <= lastRow; rowIdx++) {
			tableRow = parseRow(this.sheet.getRow(rowIdx));
			this.table.addRow(tableRow);
		}
		return this.table;
	}

	/**
	 * Cell.getCellType will be changed in version 4.0. Instead int it will
	 * return enum CellType.
	 *
	 * @param rowNum
	 * @param colNum
	 * @param cell
	 * @return table cell
	 */
	@SuppressWarnings("deprecation")
	private DataCell parseCell(Cell cell) {
		// TODO refactor
		Object value = null;
		DataCellType cellType = DataCellType.STRING;
		if (cell != null) {
			int type = cell.getCellType();
			if (type == CellType.BLANK.getCode()) {
				value = EMPTY_STR;
			} else if (type == CellType.BOOLEAN.getCode()) {
				value = Boolean.valueOf(cell.getBooleanCellValue());
				cellType = DataCellType.BOOLEAN;
			} else if (type == CellType.NUMERIC.getCode() && DateUtil.isCellDateFormatted(cell)) {
				value = cell.getDateCellValue();
				cellType = DataCellType.DATE;
			} else if (type == CellType.NUMERIC.getCode() && !DateUtil.isCellDateFormatted(cell)) {
				BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
				if (bd.scale() != 0) {
					value = bd;
					cellType = DataCellType.BIGDECIMAL;
				} else {
					value = bd.toBigInteger();
					cellType = DataCellType.BIGINTEGER;
				}
			} else if (type == CellType.STRING.getCode()) {
				value = cell.getStringCellValue();
			} else if (type == CellType.ERROR.getCode()) {
				value = "ERR";
			} else if (type == CellType.FORMULA.getCode()) {
				// TODO test
			}
		}
		DataCell tCell = new DataCell(cellType, value);
		return tCell;
	}

	private DataCell[] parseRow(Row row) {
		if (row == null) {
			return null;
		}
		int firstCol = this.props.getFirstCol(row.getFirstCellNum());
		int lastCol = this.props.getLastCol(row.getLastCellNum());

		DataCell[] tableRow = new DataCell[lastCol - firstCol];
		int colCounter = 0;
		for (int colIdx = firstCol; colIdx < lastCol; colIdx++) {
			tableRow[colCounter++] = parseCell(row.getCell(colIdx));
		}
		return tableRow;
	}
}
