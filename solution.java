import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ScoreProcessor {

    public int processScoreFile(String filePath) throws FileNotFoundException {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(filePath));

            String line = reader.readLine();

            int score = Integer.parseInt(line);

            return score * 10;

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            throw e;

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format in file.");
            throw e;

        } catch (IOException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
            return -1;

        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing file.");
            }

            System.out.println("File cleanup completed");
        }
    }
}
