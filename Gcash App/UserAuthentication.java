import java.util.HashMap;
import java.util.Scanner;

class User {
    int id;
    String name;
    String email;
    String number;
    String pin;

    public User(int id, String name, String email, String number, String pin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
        this.pin = pin;
    }
}

public class UserAuthentication {
    private static HashMap<Integer, User> users = new HashMap<>();
    private static int userIdCounter = 1;
    private static Scanner scanner = new Scanner(System.in);
    private static Integer loggedInUserId = null;

    // Registration
    public static void registerUser() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter Number: ");
        String number = scanner.nextLine().trim();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine().trim();

        if (name.isEmpty() || email.isEmpty() || number.isEmpty() || pin.isEmpty()) {
            System.out.println("All fields are required!");
            return;
        }

        // Check if email already exists
        for (User u : users.values()) {
            if (u.email.equals(email)) {
                System.out.println("Email already registered!");
                return;
            }
        }

        User user = new User(userIdCounter, name, email, number, pin);
        users.put(userIdCounter, user);
        System.out.println("Registration successful! Your ID: " + userIdCounter);
        userIdCounter++;
    }

    // Login
    public static void loginUser() {
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine().trim();

        for (User user : users.values()) {
            if (user.email.equals(email) && user.pin.equals(pin)) {
                loggedInUserId = user.id;
                System.out.println("Login successful! Welcome " + user.name);
                return;
            }
        }
        System.out.println("Invalid email or PIN.");
    }

    // Change PIN
    public static void changePin() {
        if (loggedInUserId == null) {
            System.out.println("You need to login first!");
            return;
        }

        System.out.print("Enter new PIN: ");
        String newPin = scanner.nextLine().trim();
        if (newPin.isEmpty()) {
            System.out.println("PIN cannot be empty!");
            return;
        }

        users.get(loggedInUserId).pin = newPin;
        System.out.println("PIN updated successfully!");
    }

    // Logout
    public static void logout() {
        if (loggedInUserId == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        loggedInUserId = null;
        System.out.println("Logout successful!");
    }

    // Main menu
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Change PIN\n4. Logout\n5. Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
                continue;
            }

            switch (choice) {
                case 1: registerUser(); break;
                case 2: loginUser(); break;
                case 3: changePin(); break;
                case 4: logout(); break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        }
    }
}
