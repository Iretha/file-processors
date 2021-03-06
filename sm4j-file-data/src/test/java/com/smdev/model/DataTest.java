package com.smdev.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.smdev.exc.ModelException;

public class DataTest {

	private Data table;

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() {
		this.table = new Data(0, 0);
	}

	@Test
	public void testAddRow() {
		try {
			this.table.addRow(DataCellType.BIGINTEGER, 1, 2, 3);
			this.table.addRow(DataCellType.BIGINTEGER, 11, 22, 33);
			this.table.addRow(DataCellType.BIGINTEGER, null, null, null);

			assertEquals(3, this.table.getRowsCount());
			assertEquals(1, this.table.getCell(0, 0).getValue());
			assertNull(this.table.getCell(2, 0).getValue());
		} catch (ModelException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testAddRowException() {
		try {
			this.table.addRow(DataCellType.BIGINTEGER, 1, 2, 3);
			this.table.addRow(DataCellType.BIGINTEGER, 1, 2);
			fail("Expected exception not occured");
		} catch (ModelException e) {
			// it's expected
		}
	}

	@Test
	public void testToString() {
		try {
			this.table.addRow(DataCellType.BIGINTEGER, 1, 2, 3);
			this.table.addRow(DataCellType.BIGINTEGER, 11, 22, 33);
			this.table.addRow(DataCellType.BIGINTEGER, null, null, null);

			System.out.println(this.table.toString());
		} catch (ModelException e) {
			fail(e.getMessage());
		}
	}
}
