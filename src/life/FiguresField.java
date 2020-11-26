package life;

class FiguresField {

	public static Figure glider() {
		return new Figure(
			new boolean[][] {
				{false, true, false},
				{false, false, true},
				{true, true, true}
			}, 3);
	}

	public static Figure block() {
		return new Figure(
			new boolean[][] {
				{true, true},
				{true, true}
			}, 2);
	}

	public static Field makeFieldFromFigure(Figure figure, int fieldSize) {
		boolean[][] cells = new boolean[fieldSize][fieldSize];
		int currenRow = 0;
		for (int row = 0; row < figure.getSize(); row++) {
			currenRow = row;
			int currentCol = 0;
			for (int col = 0; col < figure.getCells()[row].length; col++) {
				currentCol = col;
				cells[row][col] = figure.getCells()[row][col];
			}
			if (currentCol < fieldSize) {
				for (int i = currentCol + 1; i < fieldSize; i++) {
					cells[currenRow][i] = false;
				}
			}
		}
		if (currenRow < fieldSize) {
			for (int i = currenRow + 1; i < fieldSize; i++) {
				for (int j = 0; j < fieldSize; j++) {
					cells[i][j] = false;
				}
			}
		}
		return new Field(fieldSize, cells);
	}

}