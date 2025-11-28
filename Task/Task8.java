package Task;

public class Task8 {
    // Method with variable arguments
    static int sumCumulative(int... params) {
        int totalSum = 0;

        for (int num : params) {
            int cumSum = 0;
            for (int i = 1; i <= num; i++) {
                cumSum += i;  // sum 1+2+...+num
            }
            System.out.println("Cumulative sum for " + num + " = " + cumSum);
            totalSum += cumSum;
        }

        return totalSum;
    }

    public static void main(String[] args) {
        int total = sumCumulative(4, 5, 10);
        System.out.println("Total sum of cumulative sums = " + total);
    }
}
