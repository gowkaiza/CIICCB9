import java.io.Console;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Scanner;

public class BankingApp {

    private static final String SAVED_HASH = hashPassword("12345");

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);
        double balance = 10000.00;
        int attempts = 0;
        boolean authenticated = false;

        System.out.println("===== Secure Banking App =====");

        // ---- AUTH ----
        while (attempts < 3) {

            String password = readPasswordAsterisk("Enter password: ");

            if (hashPassword(password).equals(SAVED_HASH)) {
                System.out.println("\nLogin successful!");
                authenticated = true;
                break;
            } else {
                attempts++;
                System.out.println("\nIncorrect password! Attempts: " + attempts + "/3");
            }
        }

        if (!authenticated) {
            System.out.println("\n⚠ Account locked due to too many failed attempts.");
            return;
        }

        // ---- MENU ----
        while (true) {
            System.out.println("\n==== Banking Menu ====");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Your current balance is: ₱" + balance);
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ₱");
                    double amount = input.nextDouble();

                    if (amount <= 0) {
                        System.out.println("Invalid amount!");
                    } else if (amount > balance) {
                        System.out.println("Insufficient balance!");
                    } else {
                        balance -= amount;
                        System.out.println("Withdrawal successful!");
                        System.out.println("Remaining balance: ₱" + balance);
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using the Banking App!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // =======================================
    // PURE CONSOLE PASSWORD MASKING (WORKS)
    // =======================================
    public static String readPasswordAsterisk(String prompt) throws IOException {
        System.out.print(prompt);

        StringBuilder password = new StringBuilder();

        while (true) {
            int ch = System.in.read();

            // ENTER pressed → stop
            if (ch == '\n' || ch == '\r') {
                break;
            }

            // BACKSPACE support
            if (ch == 8 || ch == 127) {
                if (password.length() > 0) {
                    System.out.print("\b \b"); // delete *
                    password.deleteCharAt(password.length() - 1);
                }
                continue;
            }

            // Normal character
            System.out.print('*');
            password.append((char) ch);
        }

        return password.toString();
    }

    // HASHING FUNCTION (SHA-256)
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) sb.append(String.format("%02x", b));
            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException("Hash error");
        }
    }
}
