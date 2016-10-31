package com.smdev.ms.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sm4j.exception.ApplicationException;
import com.smdev.exc.ReadFileException;
import com.smdev.file.SMFileReader;
import com.smdev.model.Data;
import com.smdev.util.ResourceUtils;

public class ExcelFileReader implements SMFileReader<ExcelProps> {

	private static Workbook getWorkbook(boolean xlsx, InputStream inputStream) throws IOException {
		Workbook workbook = null;
		if (xlsx) {
			workbook = new XSSFWorkbook(inputStream);
		} else {
			workbook = new HSSFWorkbook(inputStream);
		}
		return workbook;
	}

	@Override
	public Data read(ExcelProps props, File file) throws ApplicationException {
		Data data = null;
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

}
