
package Task;
import java.lang.Math;
import java.util.Scanner;

public class Task9 {
    public static int add (int a,int b) {
        return Math.addExact(a,b);
    }
    public static int subtract (int a,int b) {
        return Math.subtractExact(a,b);
    }
    public static int multiply (int a,int b) {
        return Math.multiplyExact(a,b);
    }
    public static float divide (int a,int b) {
        return Math.floorDiv(a,b);
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);   

        System.out.print("Enter first number: ");
        int a = input.nextInt();  // use int since your methods use int parameters

        System.out.print("Enter second number: ");
        int b = input.nextInt();

        System.out.println("\n--- Results ---");
        System.out.println("Addition: " + add(a, b));
        System.out.println("Subtraction: " + subtract(a, b));
        System.out.println("Multiplication: " + multiply(a, b));
               
        if (b != 0)
            System.out.println("Division (floor): " + divide(a, b));
        else
            System.out.println("Division (floor): Error! Cannot divide by zero.");

        input.close();
    }
}

    