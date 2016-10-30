package com.smdev.ms.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.smdev.exc.ApplicationException;
import com.smdev.exc.ReadFileException;
import com.smdev.model.Table;
import com.smdev.processor.Importable;
import com.smdev.util.ResourceUtils;

public class ExcelFileReader implements Importable<ExcelProps> {

	@Override
	public Table read(ExcelProps props, File file) throws ApplicationException {
		Table data = null;
		InputStream inputStream = null;
		Workbook workbook = null;
		try {
			inputStream = new FileInputStream(file);
			workbook = getWorkbook(props.isXlsxFormat(), inputStream);
			workbook.getCreationHelper().createFormulaEvaluator().evaluateAll(); // formulas
			ExcelSheetParser parser = new ExcelSheetParser(props, workbook.getSheetAt(0));
			data = parser.parse();
		} catch (IOException e) {
			throw new ReadFileException(e);
		} finally {
			ResourceUtils.silentClose(workbook);
			ResourceUtils.silentClose(inputStream);
		}
		return data;
	}

	private static Workbook getWorkbook(boolean xlsx, InputStream inputStream) throws IOException {
		Workbook workbook = null;
		if (xlsx) {
			workbook = new XSSFWorkbook(inputStream);
		} else {
			workbook = new HSSFWorkbook(inputStream);
		}
		return workbook;
	}

}
