package me.asdanjer.firstitemv2;
import org.bukkit.Material;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MaterialImporter {
    public static List<Material> importMaterials(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found: " + filePath);
            return new ArrayList<>(); // Return an empty list if the file doesn't exist
        }
        List<Material> materials = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Material material = Material.getMaterial(line);
                if (material != null) {
                    materials.add(material);
                } else {
                    System.out.println("Unknown material: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return materials;
    }
}