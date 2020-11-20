package life;

class Field {

    protected int size;

    protected boolean[][] cells;

    protected Field(int size) {
        this.size = size;
    }

    public Field(int size, boolean[][] cells) {
        this.size = size;
        this.cells = cells;
    }

    public void printField() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (cells[row][col]) {
                    System.out.print("O");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public Field makeNextGeneration() {
        boolean[][] current = cells;
        boolean[][] field = new boolean[size][size];
        int alive = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (row == 0 && col == 0) {
                    alive = countAliveNeighbors(new boolean[] {current[0][1], current[1][0], current[1][1],
                            current[0][size - 1], current[1][size - 1],
                            current[size - 1][0], current[size - 1][1],
                            current[size - 1][size - 1]});
                } else if (row == 0 && col == size - 1) {
                    alive = countAliveNeighbors(new boolean[] {current[0][0], current[1][0], current[0][col - 1],
                            current[1][col - 1], current[1][col],
                            current[size - 1][0], current[size - 1][col - 1],
                            current[size - 1][col]});
                } else if (row == size - 1 && col == 0) {
                    alive = countAliveNeighbors(new boolean[] {current[0][0], current[0][1], current[0][size - 1],
                            current[row - 1][0], current[row - 1][1],
                            current[row - 1][size - 1], current[row][size - 1],
                            current[row][1]});
                } else if (row == size - 1 && col == size - 1) {
                    alive = countAliveNeighbors(new boolean[] {current[0][0], current[0][col - 1], current[0][col],
                            current[row - 1][0], current[row - 1][col - 1],
                            current[row - 1][col], current[row][0],
                            current[row][col - 1]});
                } else if (row == 0) {
                    alive = countAliveNeighbors(new boolean[] {current[row][col - 1], current[row][col + 1], current[row + 1][col - 1],
                            current[row + 1][col], current[row + 1][col + 1],
                            current[size - 1][col - 1], current[size - 1][col],
                            current[size - 1][col + 1]});
                } else if (row == size - 1) {
                    alive = countAliveNeighbors(new boolean[] {current[row][col - 1], current[row][col + 1], current[row - 1][col - 1],
                            current[row - 1][col], current[row - 1][col + 1],
                            current[0][col - 1], current[0][col],
                            current[0][col + 1]});
                } else if (col == 0) {
                    alive = countAliveNeighbors(new boolean[] {current[row - 1][0], current[row - 1][1], current[row - 1][size - 1],
                            current[row][1], current[row][size - 1],
                            current[row + 1][0], current[row + 1][1],
                            current[row + 1][size - 1]});
                } else if (col == size - 1) {
                    alive = countAliveNeighbors(new boolean[] {current[row - 1][0], current[row - 1][col - 1], current[row - 1][col],
                            current[row][0], current[row][col - 1],
                            current[row + 1][0], current[row + 1][col - 1],
                            current[row + 1][col]});
                } else {
                    alive = countAliveNeighbors(new boolean[] {current[row - 1][col - 1], current[row - 1][col], current[row - 1][col + 1],
                            current[row][col - 1], current[row][col + 1],
                            current[row + 1][col - 1], current[row + 1][col],
                            current[row + 1][col + 1]});
                }
                field[row][col] = evolveCeil(current[row][col], alive);
            }
        }
        return new Field(size, field);
    }

    public int totalAlive() {
        int alive = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (cells[row][col]) {
                    alive++;
                }
            }
        }
        return alive;
    }

    private int countAliveNeighbors(boolean[] neighbours) {
        int alive = 0;
        for (boolean n : neighbours) {
            if (n) {
                alive++;
            }
        }
        return alive;
    }

    private boolean evolveCeil(boolean current, int alivwNeighbours) {
        if (current) {
            if (alivwNeighbours == 2 || alivwNeighbours == 3) {
                return true;
            } else {
                return false;
            }
        } else {
            if (alivwNeighbours == 3) {
                return true;
            } else {
                return false;
            }
        }
    }
}
