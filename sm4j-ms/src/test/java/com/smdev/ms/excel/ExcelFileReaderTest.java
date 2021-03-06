package com.smdev.ms.excel;

import java.io.File;
import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sm4j.exception.ApplicationException;
import com.smdev.model.Data;

public class ExcelFileReaderTest {

	private ExcelFileReader reader = null;

	private File resolveFile(String name) {
		return new File(getClass().getClassLoader().getResource(name).getFile());
	}

	@Before
	public void setUp() {
		this.reader = new ExcelFileReader();
	}

	@Test
	public void testReadShort1Xlsx1() {
		File file = resolveFile("short1.xlsx");
		ExcelProps props = new ExcelProps();
		props.setHeaderRows(2);
		props.setHeaderCols(1);
		props.setFirstRow(1);
		props.setLastRow(6);

		try {
			Data t = this.reader.read(props, file);
			Assert.assertTrue(2 == t.getHeaderRows());
			Assert.assertTrue(1 == t.getHeaderCols());
			Assert.assertEquals("", t.getRow(0)[0].getValue());
			Assert.assertEquals(new BigInteger("4461035"),
					t.getRow(t.getRowsCount() - 1)[t.getColsCount() - 1].getValue());

		} catch (ApplicationException e) {
			Assert.fail(e.getMessage());
		}

	}

	@Test
	public void testReadShort1Xlsx2() {
		File file = resolveFile("short1.xlsx");
		ExcelProps props = new ExcelProps();
		props.setHeaderRows(2);
		props.setFirstRow(1);
		props.setLastRow(6);
		props.setFirstCol(1);
		props.setLastCol(2);

		try {
			Data t = this.reader.read(props, file);
			Assert.assertTrue(2 == t.getHeaderRows());
			Assert.assertTrue(0 == t.getHeaderCols());
			Assert.assertEquals("(хиляди левове)", t.getRow(0)[0].getValue());
			Assert.assertEquals(new BigInteger("4516895"),
					t.getRow(t.getRowsCount() - 1)[t.getColsCount() - 1].getValue());
		} catch (ApplicationException e) {
			Assert.fail(e.getMessage());
		}

	}
}
