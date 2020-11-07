package life;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.lang.Thread;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter field size:");
        int n = scanner.nextInt();
        int numberOfGenerations = 50; // TODO: make adjustable
        boolean[][] field = initField(n);
        int alive = totalAlive(field, n);
        int currentGeneration = 1;

        while (alive != 0 && currentGeneration <= numberOfGenerations) {
            try {
                System.out.println("Generation #" + currentGeneration);
                System.out.println("Alive: " + alive);
                printField(field, n);
                field = makeNextGeneration(field, n);
                alive = totalAlive(field, n);
                currentGeneration++;
                Thread.sleep(500); // TODO: make adjustable
                if (System.getProperty("os.name").contains("Windows"))
                    new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
                else
                    Runtime.getRuntime().exec("clear");
            }
            catch (IOException | InterruptedException e) {}
        }
        printField(field, n);
    }

    private static int totalAlive(boolean[][] field, int size) {
        int alive = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (field[row][col]) {
                    alive++;
                }
            }
        }
        return alive;
    }

    private static int countAliveNeighbors(boolean[] neighbours) {
        int alive = 0;
        for (boolean n : neighbours) {
            if (n) {
                alive++;
            }
        }
        return alive;
    }

    private static boolean evolveCeil(boolean current, int alivwNeighbours) {
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

    private static boolean[][] makeNextGeneration(boolean[][] current, int size) {
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
        return field;
    }

    private static boolean[][] initField(int size) {
        Random random = new Random((long) (Math.random() * 100));
        boolean[][] field = new boolean[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                field[row][col] = random.nextBoolean();
            }
        }
        return field;
    }

    private static void printField(boolean[][] field, int size) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (field[row][col]) {
                    System.out.print("O");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
