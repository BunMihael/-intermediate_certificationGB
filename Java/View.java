import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class View {
    private ToyStore toyStore;
    private Scanner scanner;
    private Random random = ThreadLocalRandom.current();

    public View(ToyStore toyStore) {
        this.toyStore = toyStore;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        boolean running = true;
        while (running) {
            System.out.println("1. Get a random toy");
            System.out.println("2. View all toys");
            System.out.println("3. Manage toys (Password: Admin )");
            System.out.println("4. Save results");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline left-over
            switch (choice) {
                case 1:
                    getRandomToy();
                    break;
                case 2:
                    showToys();
                    break;
                case 3:
                    if (checkPassword()) {
                        showManageMenu();
                    } else {
                        System.out.println("Wrong password. Try again.");
                    }
                    break;
                case 4:
                    saveResults();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private boolean checkPassword() {
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        return password.equals("Admin");
    }

    private void showManageMenu() {
        boolean running = true;
        while (running) {
            System.out.println("1. Add a toy");
            System.out.println("2. Edit a toy(don't working)");
            System.out.println("3. Delete a toy(don't working)");
            System.out.println("4. Go back");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addToy();
                    break;
                case 2:
                    editToy();
                    break;
                case 3:
                    deleteToy();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void saveResults() {
        Main.saveToysToCSV(toyStore.getWonToys(), "won_toys.csv");
    }

    private void addToy() {
        System.out.print("Enter toy name: ");
        String name = scanner.nextLine();
        System.out.print("Enter toy weight (1-6): ");
        int weight = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter toy manufacturer: ");
        String manufacturer = scanner.nextLine();
        System.out.print("Enter toy age restriction: ");
        String ageRestriction = scanner.nextLine();
        System.out.print("Is the toy available? (yes/no): ");
        boolean availability = scanner.nextLine().toLowerCase().equals("yes");
        Toy toy = new Toy(name, weight, manufacturer, ageRestriction, availability);
        toyStore.addToy(toy);
        // После того как добавили игрушку в магазин, сохраняем все игрушки обратно в CSV файл
        Main.saveToysToCSV(toyStore.getToys(), "toys.csv");
        // Обновляем PriorityQueue с новыми данными из файла CSV
        toyStore.refreshToys();
    }

    private void getRandomToy() {
        Toy toy = toyStore.getRandomToy();
        if (toy != null) {
            System.out.println("You got: " + toy);
        } else {
            System.out.println("No more toys left.");
        }
    }

    private void showToys() {
        List<Toy> toys = toyStore.getToys();
        for (int i = 0; i < toys.size(); i++) {
            System.out.println((i + 1) + ". " + toys.get(i));
        }
    }

    private void editToy() {
        System.out.println("Existing toys:");
        showToys();
        System.out.print("Enter the toy id: ");
        int id = scanner.nextInt();
        if (!toyStore.isValidId(id)) {
            System.out.println("Invalid toy id. Returning to the menu.");
            return;
        }
        scanner.nextLine();
        System.out.print("Enter new toy name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new toy weight (1-6): ");
        int weight = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new toy manufacturer: ");
        String manufacturer = scanner.nextLine();
        System.out.print("Enter new toy age restriction: ");
        String ageRestriction = scanner.nextLine();
        System.out.print("Is the toy available? (yes/no): ");
        boolean availability = scanner.next().equalsIgnoreCase("yes");
        toyStore.editToy(id, name, weight, manufacturer, ageRestriction, availability);
        toyStore.setToys(Main.loadToysFromCSV("toys.csv"));
    }

    private void deleteToy() {
        System.out.println("Existing toys:");
        showToys();
        System.out.print("Enter the toy id: ");
        int id = scanner.nextInt();
        if (!toyStore.isValidId(id)) {
            System.out.println("Invalid toy id. Returning to the menu.");
            return;
        }
        toyStore.deleteToy(id);
        toyStore.setToys(Main.loadToysFromCSV("toys.csv"));
    }
}
