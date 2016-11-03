package com.smdev.csv;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.smdev.model.Data;

public class CsvFileWriterTest {

	private String destinationDir = null;
	private Data table = null;
	private CsvFileWriter writer = null;

	private File resolveFile(String name) {
		return new File(getClass().getClassLoader().getResource(name).getFile());
	}

	@Before
	public void setUp() {
		this.writer = new CsvFileWriter();
		File file = resolveFile("comma.csv");
		CsvProps props = new CsvProps();
		props.setSeparator(',');
		try {
			this.table = new CsvFileReader().read(props, file);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		this.destinationDir = file.getParentFile().getAbsolutePath();
	}

	@Test
	public void testWriteCsvWithComma() {

		try {
			CsvProps props = new CsvProps();
			props.setName(this.destinationDir + "/comma2");
			props.setSeparator(',');
			props.setExportHeaders(true);
			// props.setEscape('\'');

			File file = this.writer.write(props, this.table);
			System.out.println("File => " + file.getAbsolutePath());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testWriteCsvWithSemicolon() {

		try {
			CsvProps props = new CsvProps();
			props.setName(this.destinationDir + "/semicolon");
			props.setSeparator(';');
			props.setExportHeaders(true);
			props.setEscape('\'');

			File file = this.writer.write(props, this.table);
			System.out.println("File => " + file.getAbsolutePath());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testWriteCsvWithTab() {

		try {
			CsvProps props = new CsvProps();
			props.setName(this.destinationDir + "/tab");
			props.setSeparator('\t');
			props.setExportHeaders(true);

			File file = this.writer.write(props, this.table);
			System.out.println("File => " + file.getAbsolutePath());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testWriteTxtWithComma() {

		try {
			CsvProps props = new CsvProps();
			props.setName(this.destinationDir + "/comma");
			props.setSeparator(',');
			props.setExportHeaders(true);
			props.setTxtFileType(true);

			File file = this.writer.write(props, this.table);
			System.out.println("File => " + file.getAbsolutePath());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
}
