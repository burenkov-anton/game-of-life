package life;

class Figure {

	private boolean[][] cells;
	private int size;

	public Figure(boolean[][] cells, int size) {
		this.cells = cells;
		this.size = size;
	}

	public boolean[][] getCells() {
		return cells;
	}

	public int getSize() {
		return size;
	}

}
