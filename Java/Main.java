import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        File file = new File("toys.csv");
        if (!file.exists()) {
            for (int i = 0; i < 13; i++) {
                toyStore.addToy(ToyData.toys.get((int) (Math.random() * ToyData.toys.size())));
            }
            saveToysToCSV(toyStore.getToys(), "toys.csv");
        } else {
            toyStore.setToys(loadToysFromCSV("toys.csv"));
        }

        View view = new View(toyStore);
        view.showMenu();

        List<Toy> toys = toyStore.getWonToys();
        saveToysToCSV(toys, "won_toys.csv");
    }

    public static void saveToysToCSV(List<Toy> toys, String filename) {
        // теперь мы не выводим сообщение о сохранении в CSV
        try {
            FileWriter csvWriter = new FileWriter(filename);
            csvWriter.append("name");
            csvWriter.append(",");
            csvWriter.append("weight");
            csvWriter.append(",");
            csvWriter.append("manufacturer");
            csvWriter.append(",");
            csvWriter.append("ageRestriction");
            csvWriter.append(",");
            csvWriter.append("availability");
            csvWriter.append("\n");

            for (Toy toy : toys) {
                csvWriter.append(toy.getName());
                csvWriter.append(",");
                csvWriter.append(String.valueOf(toy.getWeight()));
                csvWriter.append(",");
                csvWriter.append(toy.getManufacturer());
                csvWriter.append(",");
                csvWriter.append(toy.getAgeRestriction());
                csvWriter.append(",");
                csvWriter.append(String.valueOf(toy.isAvailability()));
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Toy> loadToysFromCSV(String fileName) {
        List<Toy> toys = new ArrayList<>();
        try (Scanner scanner = new Scanner(Files.newBufferedReader(Paths.get(fileName)))) {
            scanner.nextLine(); // skip header line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                if (fields.length == 6) {
                    int id = Integer.parseInt(fields[0]);
                    String name = fields[1];
                    int weight = Integer.parseInt(fields[2]);
                    String manufacturer = fields[3];
                    String ageRestriction = fields[4];
                    boolean availability = Boolean.parseBoolean(fields[5]);
                    toys.add(new Toy(name, weight, manufacturer, ageRestriction, availability));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return toys;
    }
    
}
