package life;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;


class FieldFromFile {

    private static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static Field makeFieldFromFile(String fileName) {
        try {
            String fileData = readFileAsString(fileName);
            String[] rows = fileData.split("\n");
            int size = rows.length;
            boolean[][] cells = new boolean[size][size];
            for (int row = 0; row < size; row++) {
            	for (int col = 0; col < size; col++) {
            		cells[row][col] = rows[row].charAt(col) == '1' ? true : false; 
            	}
            }
            return new Field(size, cells);
        } catch (IOException e) {
            System.out.println("Error: Cannot read file: " + e.getMessage());
        }
        System.out.println("Random field will be generated");
        return new RandomField();
    }

}
