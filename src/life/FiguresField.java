package life;

class FiguresField {

	public static boolean[][] glider() {
		return new boolean[][] {
			{false, true, false},
			{false, false, true},
			{true, true, true}
		};
	}

	public static Field makeFieldFromFigure(boolean[][] figure, int fieldSize) {
		boolean[][] cells = new boolean[fieldSize][fieldSize];
		int currenRow = 0;
		for (int row = 0; row < figure.length; row++) {
			currenRow = row;
			int currentCol = 0;
			for (int col = 0; col < figure[row].length; col++) {
				currentCol = col;
				cells[row][col] = figure[row][col];
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