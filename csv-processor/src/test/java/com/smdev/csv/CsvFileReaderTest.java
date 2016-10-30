package com.smdev.csv;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.smdev.model.Data;

public class CsvFileReaderTest {

	private CsvFileReader reader = null;

	private File resolveFile(String name){
		return new File(getClass().getClassLoader().getResource(name).getFile());
	}

	@Before
	public void setUp() {
		this.reader = new CsvFileReader();
	}

	@Test
	public void testReadCsvSkipFirstRows() {
		File file = resolveFile("comma.csv");
		try {
			int rowsToSkip = 10;

			CsvProps props = new CsvProps();
			props.setSeparator(','); // optional
			props.setSkipFirstRows(rowsToSkip); // optional

			Data table = this.reader.read(props, file);
			Assert.assertEquals(1, table.getHeaderRows());
			Assert.assertEquals(36635 - rowsToSkip, table.getRowsCount());
			Assert.assertEquals(18, table.getColsCount());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testReadCsvWithComma() {
		File file = resolveFile("comma.csv");
		try {
			CsvProps props = new CsvProps();
			props.setSeparator(',');

			Data table = this.reader.read(props, file);
			int firstColIdx = 0;
			int lastColIdx = 17;

			Assert.assertEquals(1, table.getHeaderRows());
			Assert.assertEquals(36635, table.getRowsCount());
			Assert.assertEquals(18, table.getColsCount());

			// validate header row
			Assert.assertEquals("policyID", table.getRow(0)[firstColIdx].getValue());
			Assert.assertEquals("point_granularity", table.getRow(0)[lastColIdx].getValue());

			// validate first content row
			Assert.assertEquals("119736", table.getRow(1)[firstColIdx].getValue());
			Assert.assertEquals("1", table.getRow(1)[lastColIdx].getValue());

			// validate last content row
			Assert.assertEquals("398149", table.getRow(36634)[firstColIdx].getValue());
			Assert.assertEquals("1", table.getRow(36634)[lastColIdx].getValue());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testReadCsvWithSemicolon() {
		File file = resolveFile("semicolon.csv");
		try {
			CsvProps props = new CsvProps();
			props.setSeparator(';');

			Data table = this.reader.read(props, file);
			int firstColIdx = 0;
			int lastColIdx = 17;

			Assert.assertEquals(1, table.getHeaderRows());
			Assert.assertEquals(36635, table.getRowsCount());
			Assert.assertEquals(18, table.getColsCount());

			// validate header row
			Assert.assertEquals("policyID", table.getRow(0)[firstColIdx].getValue());
			Assert.assertEquals("point_granularity", table.getRow(0)[lastColIdx].getValue());

			// validate first content row
			Assert.assertEquals("119736", table.getRow(1)[firstColIdx].getValue());
			Assert.assertEquals("1", table.getRow(1)[lastColIdx].getValue());

			// validate last content row
			Assert.assertEquals("398149", table.getRow(36634)[firstColIdx].getValue());
			Assert.assertEquals("1", table.getRow(36634)[lastColIdx].getValue());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testReadCsvWithTab() {
		File file = resolveFile("tab.csv");
		try {
			CsvProps props = new CsvProps();
			props.setSeparator('\t');

			Data table = this.reader.read(props, file);
			int firstColIdx = 0;
			int lastColIdx = 17;

			Assert.assertEquals(1, table.getHeaderRows());
			Assert.assertEquals(36635, table.getRowsCount());
			Assert.assertEquals(18, table.getColsCount());

			// validate header row
			Assert.assertEquals("policyID", table.getRow(0)[firstColIdx].getValue());
			Assert.assertEquals("point_granularity", table.getRow(0)[lastColIdx].getValue());

			// validate first content row
			Assert.assertEquals("119736", table.getRow(1)[firstColIdx].getValue());
			Assert.assertEquals("1", table.getRow(1)[lastColIdx].getValue());

			// validate last content row
			Assert.assertEquals("398149", table.getRow(36634)[firstColIdx].getValue());
			Assert.assertEquals("1", table.getRow(36634)[lastColIdx].getValue());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testReadTxtWithComma() {
		File file = resolveFile("comma.txt");
		try {
			CsvProps props = new CsvProps();
			props.setSeparator(',');

			Data table = this.reader.read(props, file);
			int firstColIdx = 0;
			int lastColIdx = 17;

			Assert.assertEquals(1, table.getHeaderRows());
			Assert.assertEquals(36635, table.getRowsCount());
			Assert.assertEquals(18, table.getColsCount());

			// validate header row
			Assert.assertEquals("policyID", table.getRow(0)[firstColIdx].getValue());
			Assert.assertEquals("point_granularity", table.getRow(0)[lastColIdx].getValue());

			// validate first content row
			Assert.assertEquals("119736", table.getRow(1)[firstColIdx].getValue());
			Assert.assertEquals("1", table.getRow(1)[lastColIdx].getValue());

			// validate last content row
			Assert.assertEquals("398149", table.getRow(36634)[firstColIdx].getValue());
			Assert.assertEquals("1", table.getRow(36634)[lastColIdx].getValue());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}
