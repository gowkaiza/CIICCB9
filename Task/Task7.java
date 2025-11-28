package Task;

import java.util.Scanner;

public class Task7 {

    static double add(double a, double b){ return a + b; }
    static double subtract(double a, double b){ return a - b; }
    static double multiply(double a, double b){ return a * b; }
    static double divide(double a, double b){ return a / b; }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter first number: ");
        double x = input.nextDouble();

        System.out.print("Enter second number: ");
        double y = input.nextDouble();

        System.out.println("\n--- Results ---");
        System.out.println("Addition: " + add(x, y));
        System.out.println("Subtraction: " + subtract(x, y));
        System.out.println("Multiplication: " + multiply(x, y));

        if (y != 0)
            System.out.println("Division: " + divide(x, y));
        else
            System.out.println("Division: Error! Cannot divide by zero.");

        input.close();
    }
}
