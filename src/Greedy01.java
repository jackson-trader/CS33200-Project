import java.util.*;

class Greedy01 {
    static double knapsack(int n, int m, int[] weights, int[] values) {
        Double[] ratios = new Double[n];
        for (int i = 0; i < n; i++) {
            ratios[i] = (double) values[i] / weights[i];
        }

        // Sort ratios in descending
        Arrays.sort(ratios, Collections.reverseOrder());

        int remainingWeight = 0; // Remaining weight in knapsack after gradually filling it
        double totalValue = 0.0; // Value in knapsack after filling it up
        boolean[] takenItems = new boolean[n]; // Track items that are taken

        for (int i = 0; i < n; i++) {
            // If item with the highest ratio can fit in knapsack, then grab it
            if (weights[i] <= remainingWeight) {
                // Take item
                takenItems[i] = true;
                remainingWeight -= weights[i];
                totalValue += values[i];
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Num of items
        int m = in.nextInt(); // Weight capacity
        int[] weights = new int[n]; // Weights of each item
        int[] values = new int[n]; // Values of each item

        // Obtain weight and value of each item x_i
        for (int i = 0; i < n; i++) {
            weights[i] = in.nextInt();
            values[i] = in.nextInt();
        }

        System.out.println(knapsack(n, m, weights, values));
    }
}