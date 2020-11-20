package life;

import java.util.Random;

class RandomField extends Field {

	public RandomField() {
        super(40);
        this.cells = initField(size);
    }

	private boolean[][] initField(int size) {
        Random random = new Random((long) (Math.random() * 100));
        boolean[][] field = new boolean[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                field[row][col] = random.nextBoolean();
            }
        }
        return field;
    }

}