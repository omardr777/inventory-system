package classes;

import java.util.Scanner;

public class Inventory {
    private final Authorization authorization = new Authorization();
    private User currentUser;

    public void start() {
        loginPage();
    }

    private void loginPage() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nLogin Page:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = getUserInput(scanner);
            switch (choice) {
                case 1 -> login(scanner);
                case 2 -> register(scanner);
                case 3 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    isRunning = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Storages");
            System.out.println("2. Products");
            System.out.println("3. History");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");

            int choice = getUserInput(scanner);
            switch (choice) {
                case 1 -> storagesSection(scanner);
                case 2 -> productsSection(scanner);
                case 3 -> historySection();
                case 4 -> {
                    System.out.println("Logging out...");
                    currentUser = null;
                    isRunning = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }

        loginPage();
    }

    private void storagesSection(Scanner scanner) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nStorages Section:");
            System.out.println("1. Create Storage");
            System.out.println("2. Read Storages");
            System.out.println("3. Update Storage");
            System.out.println("4. Delete Storage");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = getUserInput(scanner);
            switch (choice) {
                case 1 -> System.out.println("Creating a new storage...");
                case 2 -> System.out.println("Reading all storages...");
                case 3 -> System.out.println("Updating a storage...");
                case 4 -> System.out.println("Deleting a storage...");
                case 5 -> isRunning = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void productsSection(Scanner scanner) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nProducts Section:");
            System.out.println("1. Create Product");
            System.out.println("2. Read Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = getUserInput(scanner);
            switch (choice) {
                case 1 -> currentUser.createProduct();
                case 2 -> System.out.println("Reading all products...");
                case 3 -> System.out.println("Updating a product...");
                case 4 -> System.out.println("Deleting a product...");
                case 5 -> isRunning = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void historySection() {
        System.out.println("\nHistory Section:");
        System.out.println("Displaying user actions history...");
    }

    private void login(Scanner scanner) {
        System.out.print("\nEnter your username: ");
        String username = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        User loggedUser = authorization.login(username, password);
        if (loggedUser != null) {
            System.out.println("Login successful! Welcome, " + loggedUser.getUsername() + ".");
            currentUser = loggedUser;
            mainMenu();
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private void register(Scanner scanner) {
        System.out.print("\nEnter your desired username: ");
        String username = scanner.next();
        System.out.print("Enter your desired password: ");
        String password = scanner.next();
        System.out.print("Enter your role (admin/employee): ");
        String role = scanner.next();

        User registeredUser = authorization.register(username, password, role);
        if (registeredUser != null) {
            System.out.println("Registration successful! Welcome, " + registeredUser.getUsername() + ".");
            currentUser = registeredUser;
            mainMenu();
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }

    private int getUserInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
