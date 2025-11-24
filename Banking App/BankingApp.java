import java.io.Console;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class BankingApp {

    private static final String SAVED_HASH = hashPassword("12345");

    public static void main(String[] args) {

        double balance = 10000.00;
        double recipientBalance = 5000.00; // sample recipient account
        int attempts = 0;
        boolean authenticated = false;
        ArrayList<String> transactionHistory = new ArrayList<>();

        System.out.println("===== Secure Banking App =====");

        // --------------------
        // AUTHENTICATION
        // --------------------
        while (attempts < 3) {
            String password = readPassword("Enter password: ");

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

        // --------------------
        // MENU
        // --------------------
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\n==== Banking Menu ====");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Your current balance is: ₱" + balance);
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ₱");
                    double withdrawAmount = input.nextDouble();
                    if (withdrawAmount <= 0) {
                        System.out.println("Invalid amount!");
                    } else if (withdrawAmount > balance) {
                        System.out.println("Insufficient balance!");
                    } else {
                        balance -= withdrawAmount;
                        System.out.println("Withdrawal successful! Remaining balance: ₱" + balance);
                        addTransaction(transactionHistory, "Withdraw", withdrawAmount);
                    }
                    break;

                case 3:
                    System.out.print("Enter amount to deposit: ₱");
                    double depositAmount = input.nextDouble();
                    if (depositAmount <= 0) {
                        System.out.println("Invalid amount!");
                    } else {
                        balance += depositAmount;
                        System.out.println("Deposit successful! Current balance: ₱" + balance);
                        addTransaction(transactionHistory, "Deposit", depositAmount);
                    }
                    break;

                case 4:
                    System.out.print("Enter recipient account name: ");
                    input.nextLine(); // consume newline
                    String recipient = input.nextLine();
                    System.out.print("Enter amount to transfer: ₱");
                    double transferAmount = input.nextDouble();

                    if (transferAmount <= 0) {
                        System.out.println("Invalid amount!");
                    } else if (transferAmount > balance) {
                        System.out.println("Insufficient balance!");
                    } else {
                        balance -= transferAmount;
                        recipientBalance += transferAmount;
                        System.out.println("Transfer successful! Remaining balance: ₱" + balance);
                        addTransaction(transactionHistory, "Transfer to " + recipient, transferAmount);
                    }
                    break;

                case 5:
                    System.out.println("\n==== Transaction History ====");
                    if (transactionHistory.isEmpty()) {
                        System.out.println("No transactions yet.");
                    } else {
                        for (String transaction : transactionHistory) {
                            System.out.println(transaction);
                        }
                    }
                    break;

                case 6:
                    System.out.println("Thank you for using the Banking App!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // --------------------
    // ADD TRANSACTION WITH TIMESTAMP
    // --------------------
    public static void addTransaction(ArrayList<String> history, String type, double amount) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        history.add(timeStamp + " - " + type + ": ₱" + amount);
    }

    // --------------------
    // PASSWORD MASKING
    // --------------------
    public static String readPassword(String prompt) {
        Console console = System.console();
        if (console != null) {
            char[] passwordArray = console.readPassword(prompt);
            return new String(passwordArray);
        } else {
            // fallback for IDEs
            Scanner scanner = new Scanner(System.in);
            System.out.print(prompt);
            return scanner.nextLine();
        }
    }

    // --------------------
    // HASH PASSWORD
    // --------------------
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
