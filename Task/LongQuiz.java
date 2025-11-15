package Task;

public class LongQuiz {
    public static void main(String[] args) {
        String s1 = "java";                       // s1 is a String
        StringBuilder s2 = new StringBuilder("java"); // s2 is a StringBuilder

        // This compares references, but s1 and s2 are different types/objects
        if (s1 == s2) {
            System.out.print("1");                // This will NOT execute
        }

        // String.equals() checks if the argument is a String. s2 is StringBuilder, so returns false
        if (s1.equals(s2)) {
            System.out.print("2");                // This will NOT execute
        }

        // Therefore, no output will be printed
    }
}
}
