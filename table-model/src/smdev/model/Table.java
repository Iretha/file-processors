package smdev.model;

import java.util.ArrayList;
import java.util.List;

import smdev.exc.ModelException;

public class Table {

	private final List<List<Cell>> content = new ArrayList<>();

	public Table() {
		super();
	}

	public void addRow(Object... values) throws ModelException {
		int rowNum = getRowsCount();
		if(rowNum != 0 && getColsCount() != values.length){
			throw new ModelException("Invalid Row!");
		}
		
		int colNum = 0;
		List<Cell> row = new ArrayList<>();
		for (Object value : values) {
			row.add(new Cell(rowNum, colNum++, value));
		}
		getContent().add(row);
	}

	public Cell getCell(int row, int col) throws ModelException {
		if (row < 0 || row >= getRowsCount()) {
			throw new ModelException("Invalid row idx!");
		}

		if (col < 0 || col >= getColsCount()) {
			throw new ModelException("Invalid col idx!");
		}
		return getContent().get(row).get(col);
	}

	public int getColsCount() {
		return getContent().isEmpty() ? 0 : getContent().get(0).size();
	}

	private List<List<Cell>> getContent() {
		return this.content;
	}

	public int getRowsCount() {
		return getContent().size();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (List<Cell> row : content) {
			for (Cell c : row) {
				str.append(c.toString());
			}
			str.append("\n");
		}
		return str.toString();
	}
}
