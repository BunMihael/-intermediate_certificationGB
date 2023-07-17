import java.util.Random;

public class Toy implements Comparable<Toy> {
    private int id;
    private String name;
    private int weight;
    private String manufacturer;
    private String ageRestriction;
    private boolean availability;

    private static final String[] manufacturers = { "USA", "China", "Germany", "Japan", "Russia" };
    private static final Random random = new Random();

    public Toy(String name,
            int weight,
            String manufacturer,
            String ageRestriction,
            boolean availability) {
        this.name = name;
        this.weight = weight;
        this.manufacturer = manufacturer;
        this.ageRestriction = ageRestriction;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(String ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Toy{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", manufacturer='" + manufacturer + '\'' +
                ", ageRestriction='" + ageRestriction + '\'' +
                ", availability=" + availability +
                '}';
    }

    @Override
    public int compareTo(Toy other) {
        return Integer.compare(this.weight, other.weight);
    }
}
