package com.smdev.ms.excel;

import java.math.BigDecimal;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import com.smdev.exc.ModelException;
import com.smdev.model.TCell;
import com.smdev.model.TCellType;
import com.smdev.model.Table;

public class ExcelSheetParser {

	private static final String EMPTY_STR = "";
	private ExcelProps props = null;
	private Table table = null;
	private Sheet sheet = null;

	public ExcelSheetParser(ExcelProps props, Sheet sheet) {
		super();
		this.props = props;
		this.sheet = sheet;
		this.table = new Table(props.getHeaderRows(), props.getHeaderCols());
	}

	public Table parse() throws ModelException {
		int firstRow = props.getFirstRow(sheet.getFirstRowNum());
		int lastRow = props.getLastRow(sheet.getLastRowNum());
		TCell[] tableRow = null;
		for (int rowIdx = firstRow; rowIdx <= lastRow; rowIdx++) {
			tableRow = parseRow(sheet.getRow(rowIdx));
			this.table.addRow(tableRow);
		}
		return this.table;
	}

	private TCell[] parseRow(Row row) {
		if(row == null){
			return null;
		}
		int firstCol = props.getFirstCol(row.getFirstCellNum());
		int lastCol = props.getLastCol(row.getLastCellNum());

		TCell[] tableRow = new TCell[lastCol - firstCol];
		int colCounter = 0;
		for (int colIdx = firstCol; colIdx < lastCol; colIdx++) {
			tableRow[colCounter++] = parseCell(row.getCell(colIdx));
		}
		return tableRow;
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
	private TCell parseCell(Cell cell) {
		// TODO refactor
		Object value = null;
		TCellType cellType = TCellType.STRING;
		if (cell != null) {
			int type = cell.getCellType();
			if (type == CellType.BLANK.getCode()) {
				value = EMPTY_STR;
			} else if (type == CellType.BOOLEAN.getCode()) {
				value = Boolean.valueOf(cell.getBooleanCellValue());
				cellType = TCellType.BOOLEAN;
			} else if (type == CellType.NUMERIC.getCode() && DateUtil.isCellDateFormatted(cell)) {
				value = cell.getDateCellValue();
				cellType = TCellType.DATE;
			} else if (type == CellType.NUMERIC.getCode() && !DateUtil.isCellDateFormatted(cell)) {
				BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
				if (bd.scale() != 0) {
					value = bd;
					cellType = TCellType.BIGDECIMAL;
				} else {
					value = bd.toBigInteger();
					cellType = TCellType.BIGINTEGER;
				}
			} else if (type == CellType.STRING.getCode()) {
				value = cell.getStringCellValue();
			} else if (type == CellType.ERROR.getCode()) {
				value = "ERR";
			} else if (type == CellType.FORMULA.getCode()) {
				// TODO test
			}
		}
		TCell tCell = new TCell(cellType, value);
		return tCell;
	}
}
