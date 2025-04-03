import java.io.*;
import java.nio.file.*;
import java.util.*;

public class App {
    public static void main(String[] args) {
        // Path to the arrivingAnimals.txt file
        Path path = Paths.get("/Users/gerardotamayo/Java_CIT-63/Projects/src/Java_Zoo/App/src/arrivingAnimals.txt");
        List<String> lines = new ArrayList<>();

        try {
            // Read lines from arrivingAnimals.txt
            lines = Files.readAllLines(path);
            System.out.println("File read successfully.");
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return;
        }

        // Create an ArrayList to hold the animal objects
        List<Animal> animals = new ArrayList<>();

        // Read each line and parse the animal details
        for (String line : lines) {
            try {
                // Split the line based on commas
                String[] parts = line.split(",");

                if (parts.length == 7) {  // Expecting 7 fields per line
                    String name = parts[0].trim();
                    int age = Integer.parseInt(parts[1].trim()); // Parse the age
                    String gender = parts[2].trim();
                    String birthSeason = parts[3].trim();
                    String color = parts[4].trim();
                    double weight = Double.parseDouble(parts[5].trim()); // Parse weight
                    String habitat = parts[6].trim();

                    // Generate the animal ID
                    String uniqueID = generateUniqueID(habitat);
                    // Calculate the birthdate
                    String birthdate = calculateBirthdate(age);

                    // Create Animal object
                    Animal animal = new Animal(name, age, gender, birthSeason, color, weight, habitat, uniqueID, birthdate);
                    animals.add(animal);

                    System.out.println("Added animal: " + animal.getName() + " (" + animal.getSpecies() + ")");
                } else {
                    System.out.println("Skipping malformed line: " + line);
                }
            } catch (Exception e) {
                System.out.println("Error processing line: " + e.getMessage());
            }
        }

        // Ensure animals were added successfully
        if (animals.isEmpty()) {
            System.out.println("No animals to report.");
            return;
        }

        // Full path to the report file (change this path as needed)
        Path reportPath = Paths.get("/Users/gerardotamayo/Java_CIT-63/Projects/src/Java_Zoo/App/src/zooPopulation.txt");

        // Print the full path of the report file for debugging
        System.out.println("Report will be written to: " + reportPath.toAbsolutePath());

        try (BufferedWriter writer = Files.newBufferedWriter(reportPath)) {
            writer.write("Zoo Population Report\n");
            writer.write("======================\n");

            // Group animals by species
            Map<String, List<Animal>> groupedBySpecies = new HashMap<>();
            for (Animal animal : animals) {
                groupedBySpecies.putIfAbsent(animal.getSpecies(), new ArrayList<>());
                groupedBySpecies.get(animal.getSpecies()).add(animal);
            }

            // Write details for each species
            for (Map.Entry<String, List<Animal>> entry : groupedBySpecies.entrySet()) {
                writer.write("\nSpecies: " + entry.getKey() + "\n");
                writer.write("Total Count: " + entry.getValue().size() + "\n");
                writer.write("Animals:\n");
                for (Animal animal : entry.getValue()) {
                    writer.write(" - " + animal.getName() + ", Age: " + animal.getAge() + ", Gender: " + animal.getGender() + ", Color: " + animal.getColor() + ", Weight: " + animal.getWeight() + " lbs\n");
                }
            }

            writer.write("\nEnd of Report\n");
            System.out.println("Writing report to: zooPopulation.txt");
        } catch (IOException e) {
            System.out.println("Error writing the report: " + e.getMessage());
        }
    }

    // Helper method to generate a unique ID
    private static String generateUniqueID(String habitat) {
        Map<String, Integer> speciesCount = new HashMap<>();
        speciesCount.put(habitat, speciesCount.getOrDefault(habitat, 0) + 1);
        return habitat.substring(0, 2).toUpperCase() + String.format("%02d", speciesCount.get(habitat));
    }

    // Helper method to calculate the birthdate
    private static String calculateBirthdate(int age) {
        int year = 2025 - age;
        return year + "-01-01"; // Simplified birthdate calculation
    }
}







