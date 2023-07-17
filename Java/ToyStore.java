import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ToyStore {
    private PriorityQueue<Toy> toys;
    private List<Toy> wonToys;
    private Random random = ThreadLocalRandom.current();

    public ToyStore() {
        this.toys = new PriorityQueue<>(Comparator.comparing(Toy::getWeight).reversed());
        this.wonToys = new ArrayList<>();
    }

    public void addToy(Toy toy) {
        this.toys.add(toy);
        Main.saveToysToCSV(getToys(), "toys.csv");
    }

    public void setToys(List<Toy> toys) {
        this.toys.clear();
        this.toys.addAll(toys);
    }

    public Toy getRandomToy() {
        if (toys.isEmpty()) {
            System.out.println("No more toys left.");
            return null;
        }
        Toy randomToy = toys.poll();
        wonToys.add(randomToy);
        return randomToy;
    }

    public List<Toy> getToys() {
        return new ArrayList<>(this.toys);
    }

    public List<Toy> getWonToys() {
        return this.wonToys;
    }

    public void editToy(int id, String name, int weight, String manufacturer, String ageRestriction,
            boolean availability) {
        Toy toy = getToyById(id);
        if (toy != null) {
            // Remove the old toy from the queue
            toys.remove(toy);
            // Create a new toy with the updated information
            Toy updatedToy = new Toy(name, weight, manufacturer, ageRestriction, availability);
            // Add the new toy to the queue
            toys.add(updatedToy);
            Main.saveToysToCSV(getToys(), "toys.csv");
        }
    }

    public void deleteToy(int id) {
        Toy toyToDelete = getToyById(id);
        if (toyToDelete != null) {
            toys.remove(toyToDelete);
            Main.saveToysToCSV(getToys(), "toys.csv");
        }
    }

    public boolean isValidId(int id) {
        return id >= 1 && id <= toys.size();
    }

    public Toy getToyById(int id) {
        for (Toy toy : toys) {
            if (toy.getId() == id) {
                return toy;
            }
        }
        return null;
    }

    public void refreshToys() {
        this.toys.clear();
        List<Toy> toysFromFile = Main.loadToysFromCSV("toys.csv");
        for (Toy toy : toysFromFile) {
            this.addToy(toy);
        }
    }

    public void viewAllToys(ToyStore toyStore) {
        List<Toy> toys = toyStore.getToys();
        for (int i = 0; i < toys.size(); i++) {
            System.out.println((i + 1) + ". " + toys.get(i));
        }
    }
}
