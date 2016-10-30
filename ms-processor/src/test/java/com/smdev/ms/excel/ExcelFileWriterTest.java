package com.smdev.ms.excel;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.smdev.model.Data;

public class ExcelFileWriterTest {
	
	private String destinationDir = null;
	private Data table = null;
	private ExcelFileWriter writer = null;

	@Before
	public void setUp() {
		this.writer = new ExcelFileWriter();
		File file = new File(getClass().getResource("files/short1.xlsx").getFile());
		ExcelProps props = new ExcelProps();
		props.setHeaderRows(2);
		props.setHeaderCols(1);
		props.setFirstRow(1);
		props.setLastRow(6);

		try {
			this.table = new ExcelFileReader().read(props, file);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		
		File dir = new File(getClass().getResource("files").getFile());
		this.destinationDir = dir.getAbsolutePath();
	}
	

	@Test
	public void testWrite(){
		try {
			ExcelProps props = new ExcelProps();
			props.setName(this.destinationDir + "/written1");
			
			File file = this.writer.write(props, this.table);
			System.out.println("File => " + file.getAbsolutePath());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
}
