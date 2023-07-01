import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NotepadFileTransformer {
    public static void main(String[] args) {
        String inputFilePath = "notepadFile.txt";
        String outputFilePath = "outputFile.txt";

        transformNotepadFile(inputFilePath, outputFilePath);

        System.out.println("Transformation complete!");
    }

    public static void transformNotepadFile(String inputFilePath, String outputFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");

                // Extract customer details
                String customerId = fields[0];
                String customerIC = fields[1];

                // Write customer details to the output file
                writer.write("C" + customerId + ";" + customerIC);

                // Process item details
                for (int i = 2; i < fields.length; i += 4) {
                    String itemId = "I" + fields[i];
                    String itemName = fields[i + 1];
                    double itemPrice = Double.parseDouble(fields[i + 2]);
                    String purchaseDate = fields[i + 3];

                    // Write transformed item details to the output file
                    writer.write(";" + itemId + ";" + itemName + ";" + String.format("%.2f", itemPrice) +
                            ";" + purchaseDate);
                }

                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
