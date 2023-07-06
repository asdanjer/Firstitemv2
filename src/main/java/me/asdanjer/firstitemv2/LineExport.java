package me.asdanjer.firstitemv2;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LineExport {
    private String filePath;

    public LineExport(String filePath) {
        this.filePath = filePath;
    }

    public void appendToDocument(String input) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(input);
            writer.newLine(); // Add a new line after the input
        } catch (IOException e) {
            System.err.println("An error occurred while appending to the text document: " + e.getMessage());
        }
    }
}