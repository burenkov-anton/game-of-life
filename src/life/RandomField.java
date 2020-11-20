package life;

import java.util.Random;

class RandomField {

	static public Field makeRandomField() {
        final int size = 40;
        return new Field(size, initField(size));
    }

	static private boolean[][] initField(int size) {
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