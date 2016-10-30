package com.smdev.ms.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.smdev.exc.ApplicationException;
import com.smdev.exc.WriteFileExaception;
import com.smdev.model.Data;
import com.smdev.processor.SMFileWriter;
import com.smdev.util.ResourceUtils;

public class ExcelFileWriter implements SMFileWriter<ExcelProps> {

	@Override
	public File write(ExcelProps props, Data data) throws ApplicationException {
		Workbook workbook = null;
		FileOutputStream fileOut = null;
		File file = null;
		try {
			workbook = new XSSFWorkbook();
			
			ExcelSheetWriter sheetWriter = new ExcelSheetWriter(data, workbook.createSheet());
			sheetWriter.writeData();
			
			fileOut = new FileOutputStream(props.getFileName());
			workbook.write(fileOut);
			file = new File(props.getFileName());
		} catch (IOException e) {
			throw new WriteFileExaception(e);
		} finally {
			ResourceUtils.silentClose(fileOut);
			ResourceUtils.silentClose(workbook);
		}
		return file;
	}

}
