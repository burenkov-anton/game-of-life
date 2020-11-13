package life;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Thread;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter field size:");
        int n = scanner.nextInt();
        int numberOfGenerations = 50; // TODO: make adjustable
        Field field = new Field(n);
        int alive = field.totalAlive();
        int currentGeneration = 1;

        while (alive != 0 && currentGeneration <= numberOfGenerations) {
            try {
                System.out.println("Generation #" + currentGeneration);
                System.out.println("Alive: " + alive);
                field.printField();
                field = field.makeNextGeneration();
                alive = field.totalAlive();
                currentGeneration++;
                Thread.sleep(500); // TODO: make adjustable
                if (System.getProperty("os.name").contains("Windows"))
                    new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
                else
                    Runtime.getRuntime().exec("clear");
            }
            catch (IOException | InterruptedException e) {}
        }
        field.printField();
    }
}
