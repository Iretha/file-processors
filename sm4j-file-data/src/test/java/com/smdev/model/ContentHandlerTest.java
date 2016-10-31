package com.smdev.model;

import org.junit.Assert;
import org.junit.Test;

import com.sm4j.exception.ApplicationException;

public class ContentHandlerTest {

	@Test
	public void testReorderCols() {
		try {
			Data original = new Data(0, 0);
			original.addRow(DataCellType.BIGINTEGER, 1, 2, 3);
			original.addRow(DataCellType.BIGINTEGER, 11, 22, 33);
			original.addRow(DataCellType.BIGINTEGER, 111, 222, 333);

			Data ordered = ContentHandler.reorderCols(original, new int[] { 2, 1, 0 });

			// col 0 -> col 2
			Assert.assertEquals(original.getCell(0, 0), ordered.getCell(0, 2));
			Assert.assertEquals(original.getCell(1, 0), ordered.getCell(1, 2));
			Assert.assertEquals(original.getCell(2, 0), ordered.getCell(2, 2));

			// col 1 -> col 1
			Assert.assertEquals(original.getCell(0, 1), ordered.getCell(0, 1));
			Assert.assertEquals(original.getCell(1, 1), ordered.getCell(1, 1));
			Assert.assertEquals(original.getCell(2, 1), ordered.getCell(2, 1));

			// col 2 -> col 0
			Assert.assertEquals(original.getCell(0, 2), ordered.getCell(0, 0));
			Assert.assertEquals(original.getCell(1, 2), ordered.getCell(1, 0));
			Assert.assertEquals(original.getCell(2, 2), ordered.getCell(2, 0));
		} catch (ApplicationException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testReorderRows() {
		try {
			Data original = new Data(0, 0);
			original.addRow(DataCellType.BIGINTEGER, 1, 2, 3);
			original.addRow(DataCellType.BIGINTEGER, 11, 22, 33);
			original.addRow(DataCellType.BIGINTEGER, 111, 222, 333);

			Data ordered = ContentHandler.reorderRows(original, new int[] { 2, 1, 0 });

			// row 0 -> row 2
			Assert.assertEquals(original.getCell(0, 0), ordered.getCell(2, 0));
			Assert.assertEquals(original.getCell(0, 1), ordered.getCell(2, 1));
			Assert.assertEquals(original.getCell(0, 2), ordered.getCell(2, 2));

			// row 1 -> row 1
			Assert.assertEquals(original.getCell(1, 0), ordered.getCell(1, 0));
			Assert.assertEquals(original.getCell(1, 1), ordered.getCell(1, 1));
			Assert.assertEquals(original.getCell(1, 2), ordered.getCell(1, 2));

			// row 2 -> row 0
			Assert.assertEquals(original.getCell(2, 0), ordered.getCell(0, 0));
			Assert.assertEquals(original.getCell(2, 1), ordered.getCell(0, 1));
			Assert.assertEquals(original.getCell(2, 2), ordered.getCell(0, 2));
		} catch (ApplicationException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testSwapCols() {
		try {
			Data original = new Data(0, 0);
			original.addRow(DataCellType.BIGINTEGER, 1, 2, 3);
			original.addRow(DataCellType.BIGINTEGER, 11, 22, 33);
			original.addRow(DataCellType.BIGINTEGER, 111, 222, 333);

			Data ordered = ContentHandler.swapCols(original, 0, 2);

			// col 0 -> col 2
			Assert.assertEquals(original.getCell(0, 0), ordered.getCell(0, 2));
			Assert.assertEquals(original.getCell(1, 0), ordered.getCell(1, 2));
			Assert.assertEquals(original.getCell(2, 0), ordered.getCell(2, 2));

			// col 2 -> col 0
			Assert.assertEquals(original.getCell(0, 2), ordered.getCell(0, 0));
			Assert.assertEquals(original.getCell(1, 2), ordered.getCell(1, 0));
			Assert.assertEquals(original.getCell(2, 2), ordered.getCell(2, 0));
		} catch (ApplicationException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testSwapRows() {
		try {
			Data original = new Data(0, 0);
			original.addRow(DataCellType.BIGINTEGER, 1, 2, 3);
			original.addRow(DataCellType.BIGINTEGER, 11, 22, 33);
			original.addRow(DataCellType.BIGINTEGER, 111, 222, 333);

			Data ordered = ContentHandler.swapRows(original, 0, 2);

			// row 0 -> row 2
			Assert.assertEquals(original.getCell(0, 0), ordered.getCell(2, 0));
			Assert.assertEquals(original.getCell(0, 1), ordered.getCell(2, 1));
			Assert.assertEquals(original.getCell(0, 2), ordered.getCell(2, 2));

			// row 2 -> row 0
			Assert.assertEquals(original.getCell(2, 0), ordered.getCell(0, 0));
			Assert.assertEquals(original.getCell(2, 1), ordered.getCell(0, 1));
			Assert.assertEquals(original.getCell(2, 2), ordered.getCell(0, 2));
		} catch (ApplicationException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testTranspose() {
		try {
			Data original = new Data(0, 0);
			original.addRow(DataCellType.BIGINTEGER, 1, 2, 3);
			original.addRow(DataCellType.BIGINTEGER, 11, 22, 33);
			original.addRow(DataCellType.BIGINTEGER, 111, 222, 333);

			Data ordered = ContentHandler.transpose(original);

			// col 0 -> row 0
			Assert.assertEquals(original.getCell(0, 0), ordered.getCell(0, 0));
			Assert.assertEquals(original.getCell(1, 0), ordered.getCell(0, 1));
			Assert.assertEquals(original.getCell(2, 0), ordered.getCell(0, 2));

			// col 1 -> row 1
			Assert.assertEquals(original.getCell(0, 1), ordered.getCell(1, 0));
			Assert.assertEquals(original.getCell(1, 1), ordered.getCell(1, 1));
			Assert.assertEquals(original.getCell(2, 1), ordered.getCell(1, 2));

			// col 2 -> row 2
			Assert.assertEquals(original.getCell(0, 2), ordered.getCell(2, 0));
			Assert.assertEquals(original.getCell(1, 2), ordered.getCell(2, 1));
			Assert.assertEquals(original.getCell(2, 2), ordered.getCell(2, 2));
			
			// headers
			Assert.assertEquals(original.getHeaderCols(), ordered.getHeaderRows());
			Assert.assertEquals(original.getHeaderRows(), ordered.getHeaderCols());
		} catch (ApplicationException e) {
			Assert.fail(e.getMessage());
		}
	}

}
