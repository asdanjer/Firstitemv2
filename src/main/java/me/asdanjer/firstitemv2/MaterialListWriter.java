package me.asdanjer.firstitemv2;
import org.bukkit.Bukkit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MaterialListWriter {

    public static void writeMaterialListToFile(List<String> materialList, String fileName) {
        File file = new File(fileName);

        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            for (String materialInfo : materialList) {
                bufferedWriter.write(materialInfo);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            Bukkit.getLogger().info("An error occurred while writing the material list to the file.");
            e.printStackTrace();
        }
    }
}