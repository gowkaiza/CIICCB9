package Task;                  // Package name (optional depending on your setup)
import java.util.Scanner;      // Import Scanner for user input

public class Task5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);  // Create Scanner object

        System.out.print("Enter first number: ");
        int num1 = input.nextInt();              // Read first number

        System.out.print("Enter second number: ");
        int num2 = input.nextInt();              // Read second number
2
        System.out.print("Enter third number: ");
        int num3 = input.nextInt();              // Read third number

        if (num1 == num2 && num2 == num3) {
            System.out.println("All numbers are equal"); // All equal
        } else if (num1 >= num2 && num1 >= num3) {
            System.out.println("The largest number is: " + num1); // num1 is largest
        } else if (num2 >= num1 && num2 >= num3) {
            System.out.println("The largest number is: " + num2); // num2 is largest
        } else {
            System.out.println("The largest number is: " + num3); // num3 is largest
        }

        input.close(); // Close Scanner
    }
}