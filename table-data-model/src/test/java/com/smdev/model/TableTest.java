package com.smdev.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.smdev.model.exc.ModelException;

public class TableTest {

	private Table table;

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() {
		this.table = new Table();
	}

	@Test
	public void testAddRow() {
		try {
			this.table.addRow(1, 2, 3);
			this.table.addRow(11, 22, 33);
			this.table.addRow(null, null, null);

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
			this.table.addRow(1, 2, 3);
			this.table.addRow(1, 2);
			fail("Expected exception not occured");
		} catch (ModelException e) {
			//
		}
	}

	@Test
	public void testToString() {
		try {
			this.table.addRow(1, 2, 3);
			this.table.addRow(11, 22, 33);
			this.table.addRow(null, null, null);

			System.out.println(this.table.toString());
		} catch (ModelException e) {
			fail(e.getMessage());
		}
	}
}
