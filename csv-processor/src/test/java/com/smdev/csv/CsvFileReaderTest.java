package com.smdev.csv;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.smdev.model.Table;

public class CsvFileReaderTest {

	private CsvFileReader reader = null;

	@Before
	public void setUp() {
		this.reader = new CsvFileReader();
	}
	
	@Test
	public void testReadCsvSkipFirstRows() {
		File file = new File(getClass().getResource("files/comma.csv").getFile());
		try {
			int rowsToSkip = 10;

			CsvProps props = new CsvProps();
			props.setSeparator(','); // optional
			props.setSkipFirstRows(rowsToSkip); // optional

			Table table = this.reader.read(props, file);
			Assert.assertEquals(1, table.getHeaderRows());
			Assert.assertEquals(36635 - rowsToSkip, table.getRowsCount());
			Assert.assertEquals(18, table.getColsCount());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testReadCsvWithComma() {
		File file = new File(getClass().getResource("files/comma.csv").getFile());
		try {
			CsvProps props = new CsvProps();
			props.setSeparator(',');

			Table table = this.reader.read(props, file);
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
		File file = new File(getClass().getResource("files/semicolon.csv").getFile());
		try {
			CsvProps props = new CsvProps();
			props.setSeparator(';');

			Table table = this.reader.read(props, file);
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
		File file = new File(getClass().getResource("files/tab.csv").getFile());
		try {
			CsvProps props = new CsvProps();
			props.setSeparator('\t');

			Table table = this.reader.read(props, file);
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
		File file = new File(getClass().getResource("files/comma.csv").getFile());
		try {
			CsvProps props = new CsvProps();
			props.setSeparator(',');

			Table table = this.reader.read(props, file);
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
