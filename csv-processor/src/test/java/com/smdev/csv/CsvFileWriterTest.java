package com.smdev.csv;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.smdev.exc.ModelException;
import com.smdev.exc.ReadFileException;
import com.smdev.model.Table;

public class CsvFileWriterTest {

	private CsvFileWriter writer = null;
	private Table table = null;
	private String destinationDir = null;

	@Before
	public void setUp() {
		this.writer = new CsvFileWriter();
		File file = new File(getClass().getResource("files/comma.csv").getFile());
		CsvTableProps props = new CsvTableProps();
		props.addSeparator(",");
		try {
			this.table = new CsvFileReader().read(props, file);
		} catch (ReadFileException | ModelException e) {
			Assert.fail(e.getMessage());
		}
		
		File dir = new File(getClass().getResource("files").getFile());
		this.destinationDir = dir.getAbsolutePath();
	}

	@Test
	public void testWriteSemicolon() {

		try {
			CsvTableProps props = new CsvTableProps();
			props.addName(this.destinationDir + "/semicolon");
			props.addSeparator(";");
			props.addExportHeaders(true);
			
			File file = this.writer.write(props, this.table);
			System.out.println("File => " + file.getAbsolutePath());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}
