package life;
import java.io.IOException;
import java.lang.Thread;

public class Main {
    public static void main(String[] args) {
        int numberOfGenerations = 50; // TODO: make adjustable
        Field field = makeField(args);
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

    public static Field makeField(String[] args) {
        String fileName = getFileName(args);
        if (fileName.equals("")) {
            return RandomField.makeRandomField();
        } else {
            return FieldFromFile.makeFieldFromFile(fileName);
        }
    }

    public static String getFileName(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--file")) {
                try {
                    return args[i + 1];
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        return "";
    }
}
