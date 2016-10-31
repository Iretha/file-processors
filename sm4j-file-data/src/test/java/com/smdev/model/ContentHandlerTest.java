package com.smdev.model;

import org.junit.Assert;
import org.junit.Test;

import com.sm4j.exception.ApplicationException;
import com.smdev.exc.ModelException;

public class ContentHandlerTest {

	@Test
	public void testReorderCols() {
		// TODO
		Data table = new Data(0, 0);
		try {
			table.addRow(DataCellType.BIGINTEGER, 1, 2, 3);
			table.addRow(DataCellType.BIGINTEGER, 11, 22, 33);
			table.addRow(DataCellType.BIGINTEGER, null, null, null);

			System.out.println(table.toString());
		} catch (ModelException e) {
			Assert.fail(e.getMessage());
		}
		try {
			Data ordered = ContentHandler.reorderCols(table, new int[] { 2, 1, 0 });

			System.out.println(ordered.toString());
		} catch (ApplicationException e) {
			Assert.fail(e.getMessage());
		}

	}

}
